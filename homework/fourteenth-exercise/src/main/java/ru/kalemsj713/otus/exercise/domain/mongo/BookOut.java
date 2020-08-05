package ru.kalemsj713.otus.exercise.domain.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document(collection = "books")
@AllArgsConstructor
@NoArgsConstructor
public class BookOut {
    @Id
    private String id;
    private String title;
    @JsonManagedReference("genres")
    @DBRef
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AuthorOut> authors = new HashSet<>();
    @JsonManagedReference("authors")
    @DBRef
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<GenreOut> genreOuts = new HashSet<>();

}
