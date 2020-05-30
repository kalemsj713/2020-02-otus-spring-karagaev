package ru.kalemsj713.otus.exercise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kalemsj713.otus.exercise.domain.Comment;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDTO {
    private long id;

    @NotEmpty(message = "Please provide a text")
    private String text;

    private long bookId;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.bookId = comment.getBook().getId();
        this.text = comment.getText();
    }
}
