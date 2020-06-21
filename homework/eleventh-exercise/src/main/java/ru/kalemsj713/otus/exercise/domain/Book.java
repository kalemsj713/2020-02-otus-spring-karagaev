package ru.kalemsj713.otus.exercise.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document(collection = "books")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private String id;
    private String title;
    @DBRef
    private Set<Comment> comments = new HashSet<>();
    private Set<Author> authors = new HashSet<>();
    private Set<Genre> genres = new HashSet<>();

    public Book(String title) {
        this.title = title;
    }

    public Book(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
