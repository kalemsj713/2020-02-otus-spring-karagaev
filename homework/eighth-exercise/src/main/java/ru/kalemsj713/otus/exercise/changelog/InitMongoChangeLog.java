package ru.kalemsj713.otus.exercise.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Comment;
import ru.kalemsj713.otus.exercise.domain.Genre;

import java.util.Set;

@ChangeLog(order = "001")
public class InitMongoChangeLog {
    private Book book1;
    private Book book2;
    private Book book3;


    @ChangeSet(order = "000", id = "dropDB", author = "kalemsj", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initBooks", author = "kalemsj", runAlways = true)
    public void initBooks(MongoTemplate template) {
        book1 = template.save(new Book("b1"));
        book2 = template.save(new Book("b2"));
        book3 = template.save(new Book("b3"));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "kalemsj", runAlways = true)
    public void initGenres(MongoTemplate template) {
        Genre genre1 = new Genre("g1");
        Genre genre2 = new Genre("g2");
        Genre genre3 = new Genre("g3");
        template.save(genre1);
        template.save(genre2);
        template.save(genre3);
        book1.getGenres().addAll(Set.of(genre1, genre2));
        book2.getGenres().addAll(Set.of(genre1, genre2));
        book3.getGenres().addAll(Set.of(genre1));
        template.save(book1);
        template.save(book2);
        template.save(book3);
    }

    @ChangeSet(order = "003", id = "initAuthors", author = "kalemsj", runAlways = true)
    public void initAuthors(MongoTemplate template) {
        Author author1 = new Author("a1");
        Author author2 = new Author("a2");
        Author author3 = new Author("a3");
        template.save(author1);
        template.save(author2);
        template.save(author3);
        book1.getAuthors().addAll(Set.of(author1, author2));
        book2.getAuthors().addAll(Set.of(author1, author2));
        book3.getAuthors().addAll(Set.of(author1));
        template.save(book1);
        template.save(book2);
        template.save(book3);
    }

    @ChangeSet(order = "004", id = "initComments", author = "kalemsj", runAlways = true)
    public void initComments(MongoTemplate template) {
        Comment comment1 = new Comment("c1");
        Comment comment2 = new Comment("c2");
        Comment comment3 = new Comment("c3");

        template.save(comment1);
        template.save(comment2);
        template.save(comment3);
        book1.getComments().addAll(Set.of(comment1, comment2));
        book2.getComments().add(comment3);
        template.save(book1);
        template.save(book2);

    }
}
