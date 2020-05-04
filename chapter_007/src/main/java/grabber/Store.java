package grabber;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

    @Override
    public void close() throws Exception {
        connection.close();
    }

    /**
     * Сохраняет объявление в БД.
     * Вакансии с одинаковым именем считаются дубликатами и не записываются в БД.
     * @param post
     */
    @Override
    public boolean save(Post post) {
        try {
            try (PreparedStatement ps = connection.prepareStatement("SELECT count(*) FROM joboffers WHERE name = ?")) {
                ps.setString(1, post.getName());
                ResultSet resultSet = ps.executeQuery();
                resultSet.next();
                if (resultSet.getInt(1) > 0) {
                    return false;
                }
            }
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO joboffers (date, name, author, link, text) VALUES (?, ?, ?, ?, ?);")) {
                int i = 1;
                ps.setTimestamp(i++, new Timestamp(post.getDate().getTime()));
                ps.setString(i++, post.getName());
                ps.setString(i++, post.getAuthor());
                ps.setString(i++, post.getTextLink());
                ps.setString(i++, post.getText());
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Извлекает объявления из БД.
     * @param filter
     * @return
     */
    @Override
    public List<Post> get(Predicate<Post> filter) {
        return get().stream().filter(filter).collect(Collectors.toList());
    }

    public List<Post> get() {
        List<Post> joboffers = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM joboffers ORDER BY date DESC");
             ResultSet resultSet = ps.executeQuery()
        ) {
            while (resultSet.next()) {
                joboffers.add(
                        new Post(
                                resultSet.getTimestamp("date"),
                                resultSet.getString("name"),
                                resultSet.getString("author"),
                                resultSet.getString("link"),
                                resultSet.getString("text")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return joboffers;
    }

}