package ru.kalemsj713.otus.exercise.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kalemsj713.otus.exercise.dto.CommentDTO;
import ru.kalemsj713.otus.exercise.service.CommentService;

@RestController
@RequestMapping("api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PutMapping
    public CommentDTO newComment(@RequestBody CommentDTO commentDTO) {
        return commentService.addNewComment(commentDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        commentService.deleteComment(id);
    }
}
