package ru.kalemsj713.otus.exercise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kalemsj713.otus.exercise.domain.Author;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorDTO {
    private long id;

    @NotEmpty(message = "Please provide a name")
    private String name;

    private List<BookDTO> books = new ArrayList<>();

    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.name = author.getName();

        this.books = author.getBooks().stream().map(BookDTO::new).collect(Collectors.toList());
    }

}
