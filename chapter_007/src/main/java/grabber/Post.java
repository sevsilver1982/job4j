package grabber;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

public class Post {
    private Timestamp date;
    private String name;
    private String author;
    private String textLink;
    private String text;

    public Post(Timestamp date, String name, String author, String textLink, String text) {
        this.date = date;
        this.name = name;
        this.author = author;
        this.textLink = textLink;
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTextLink() {
        return textLink;
    }

    public void setTextLink(String textLink) {
        this.textLink = textLink;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(name, post.name)
                && Objects.equals(text, post.text)
                && Objects.equals(author, post.author)
                && Objects.equals(date, post.date)
                && Objects.equals(textLink, post.textLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, text, author, date, textLink);
    }

    @Override
    public String toString() {
        return new StringJoiner(System.lineSeparator())
                .add(String.format("date=%s", date))
                .add(String.format("name=%s", name))
                .add(String.format("author=%s", author))
                .add(String.format("link=%s", textLink))
                .add(String.format("text=%s", text))
                .add(System.lineSeparator())
                .toString();
    }

}