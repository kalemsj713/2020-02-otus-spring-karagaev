package ru.kalemsj713.otus.exercise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kalemsj713.otus.exercise.domain.Genre;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GenreDTO {
    private long id;

    @NotEmpty(message = "Please provide a title")
    private String title;

    private List<BookDTO> books = new ArrayList<>();

    public GenreDTO(Genre genre) {
        this.id = genre.getId();
        this.title = genre.getTitle();
        this.books = genre.getBooks().stream().map(BookDTO::new).collect(Collectors.toList());
    }
}
