package ru.kalemsj713.otus.exercise.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Set;

@Data

@Document(collection = "books")

public class Book {
    @Id
    private String id;
    private String title;
    @DBRef
    private Set<Comment> comments = new HashSet<>();
    @Field("authors")
    private Set<Author> authors = new HashSet<>();
    private Set<Genre> genres = new HashSet<>();

    public Book(String title) {
        this.title = title;
    }


}
