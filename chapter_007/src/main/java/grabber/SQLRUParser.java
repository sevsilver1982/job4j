package grabber;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1. Реализовать модуль сборки анализа данных с sql.ru.
 * 2. Система должна использовать Jsoup для парсинга страниц.
 * 3. Система должна запускаться раз в день.
 * 4. Система должна собирать данные, только про вакансии Java. Учесть, что JavaScript не подходит, как и Java Script.
 * 5. Данные должны храниться в БД.
 * 6. Учесть дубликаты. Вакансии с одинаковым именем считаются дубликатами.
 * 7. Учитывать время последнего запуска, если это первый запуск, то нужно собрать все объявления с начала года.
 * 8. В системе не должно быть вывода, либо ввода информации. Все настройки должны быть в файле. app.grabber.properties.
 * 9. Для вывода нужной информации использовать логгер log4j.
 */
public class SQLRUParser implements IParser {
    private IStore store;
    private static final Logger LOG = LogManager.getLogger(JobScheduler.class.getName());
    private static final Map<String, Integer> MONTH = Stream.of(
            new AbstractMap.SimpleImmutableEntry<>("янв", Calendar.JANUARY),
            new AbstractMap.SimpleImmutableEntry<>("фев", Calendar.FEBRUARY),
            new AbstractMap.SimpleImmutableEntry<>("мар", Calendar.MARCH),
            new AbstractMap.SimpleImmutableEntry<>("апр", Calendar.APRIL),
            new AbstractMap.SimpleImmutableEntry<>("май", Calendar.MAY),
            new AbstractMap.SimpleImmutableEntry<>("июн", Calendar.JUNE),
            new AbstractMap.SimpleImmutableEntry<>("июл", Calendar.JULY),
            new AbstractMap.SimpleImmutableEntry<>("авг", Calendar.AUGUST),
            new AbstractMap.SimpleImmutableEntry<>("сен", Calendar.SEPTEMBER),
            new AbstractMap.SimpleImmutableEntry<>("окт", Calendar.OCTOBER),
            new AbstractMap.SimpleImmutableEntry<>("ноя", Calendar.NOVEMBER),
            new AbstractMap.SimpleImmutableEntry<>("дек", Calendar.DECEMBER)
    ).collect(
            Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)
    );

    public SQLRUParser(IStore store) {
        this.store = store;
    }

    public IStore getStore() {
        return store;
    }

    /**
     * Загружает список объявлений по ссылке
     * @param link
     * @return
     */
    @Override
    public List<Post> list(String link) {
        List<Post> result = new ArrayList<>();
        // 4. Система должна собирать данные, только про вакансии Java. Учесть, что JavaScript не подходит, как и Java Script.
        Predicate<Post> predicateJava = post -> post.getName().contains("Java")
                && (!post.getName().contains("JavaScript")
                && !post.getName().contains("Java Script"));

        // 7. Учитывать время последнего запуска, если это первый запуск, то нужно собрать все объявления с начала года.
        Date lastDate = getLastOfferDate();
        if (lastDate == null) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.MONTH, Calendar.JANUARY);
            c.set(Calendar.DAY_OF_MONTH, 1);
            lastDate = c.getTime();
        }
        Date finalLastDate = lastDate;
        Predicate<Post> predicateDate = post -> post.getDate().after(finalLastDate);

        for (int i = 1; i <= getPagesCount(link); i++) {
            try {
                for (Post post : collectOffers(String.format("%s%s", link, i), predicateJava)) {
                    if (predicateDate.test(post)) {
                        result.add(post);
                    } else {
                        return result;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        return result;
    }

    private List<Post> collectOffers(String link, Predicate<Post> filter) throws IOException {
        return Jsoup.parseBodyFragment(
                Jsoup.connect(link).get().html()
        )
                .body()
                .select("tr")
                .stream()
                .filter(element ->
                        !element.select(".postslisttopic").isEmpty()
                                && element.select(".closedTopic").isEmpty()
                )
                .map(element -> {
                    String name = "";
                    String author = "";
                    String date = "";
                    String textLink = "";
                    name = element.select(".postslisttopic > a").get(0).text();
                    textLink = element.select(".postslisttopic > a").get(0).attr("href");
                    author = element.select("td.altCol > a").text();
                    date = element.select("td[style='text-align:center'].altCol").text();
                    return new Post(stringToDate(date), name, author, textLink, "");
                })
                .filter(filter)
                .peek(post -> {
                    String text = "";
                    try {
                        text = Jsoup.parseBodyFragment(
                                Jsoup.connect(post.getTextLink()).get().html()
                        )
                                .getElementsByClass("msgBody")
                                .get(1)
                                .text();
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException();
                    }
                    post.setText(text.trim());
                })
                .collect(Collectors.toList());
    }

    private Timestamp getLastOfferDate() {
        try {
            try (PreparedStatement ps = store.getConnection().prepareStatement("SELECT max(date) AS date FROM joboffers")) {
                ResultSet resultSet = ps.executeQuery();
                resultSet.next();
                return resultSet.getTimestamp("date");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private static Timestamp stringToDate(String str) {
        Calendar calendar = Calendar.getInstance();
        if (str.startsWith("вчера")) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        } else {
            if (!str.startsWith("сегодня")) {
                int day = Integer.parseInt(str.replaceFirst(" \\D.+", ""));
                int month = MONTH.get(str.replaceFirst("\\d+ ", "").replaceFirst(" .+", ""));
                String year = str.replaceFirst("\\d+\\D+ ", "").replaceFirst(",.+", "");
                Calendar tmp = Calendar.getInstance();
                try {
                    tmp.setTime(new SimpleDateFormat("yy").parse(year));
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
                calendar.set(tmp.get(Calendar.YEAR), month, day);
            }
        }
        calendar.set(
                Calendar.HOUR_OF_DAY,
                Integer.parseInt(str.replaceFirst(".+, ", "").replaceFirst(":\\d+", ""))
        );
        calendar.set(
                Calendar.MINUTE,
                Integer.parseInt(str.replaceFirst(".+:", ""))
        );
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    private static int getPagesCount(String url) {
        int result = 0;
        try {
            result = Jsoup.parseBodyFragment(
                    Jsoup.connect(url).get().html()
            )
                    .body()
                    .getElementsByClass("sort_options")
                    .select("a")
                    .stream()
                    .map(element -> element.attr("href"))
                    .map(element -> Integer.parseInt(
                            element.substring(element.lastIndexOf("/")).replace("/", ""))
                    )
                    .max(Comparator.naturalOrder())
                    .orElse(0);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return result;
    }

}