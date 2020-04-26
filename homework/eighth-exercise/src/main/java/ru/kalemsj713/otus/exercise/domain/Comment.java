package ru.kalemsj713.otus.exercise.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "comments")
public class Comment {
    @Id
    private String id;
    private String text;

    public Comment(String text) {
        this.text = text;
    }


}
