package ru.kalemsj713.otus.exercise.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "genres")
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    private String id;
    private String title;

    public Genre(String title) {
        this.title = title;
    }

}
