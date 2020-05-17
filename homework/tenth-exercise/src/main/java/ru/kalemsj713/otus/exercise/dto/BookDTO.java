package ru.kalemsj713.otus.exercise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kalemsj713.otus.exercise.domain.Book;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDTO {
    private long id;

    @NotEmpty(message = "Please provide a title")
    private String title;

    private List<CommentDTO> comments;
    private List<AuthorDTO> authors;
    private List<GenreDTO> genres;


    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.comments = book.getComments().stream().map(CommentDTO::new).collect(Collectors.toList());
    }

    public BookDTO(long id, String title) {
        this.id = id;
        this.title = title;
    }
}
