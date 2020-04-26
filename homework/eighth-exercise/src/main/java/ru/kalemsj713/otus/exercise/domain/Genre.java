package ru.kalemsj713.otus.exercise.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "genres")

public class Genre {
    private String id;
    private String title;

    public Genre(String title) {
        this.title = title;
    }

}
