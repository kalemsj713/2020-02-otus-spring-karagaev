package ru.kalemsj713.otus.exercise.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kalemsj713.otus.exercise.domain.Comment;
import ru.kalemsj713.otus.exercise.service.BookService;
import ru.kalemsj713.otus.exercise.service.CommentService;

import javax.validation.Valid;

@Controller
@RequestMapping("comment")
@RequiredArgsConstructor

public class CommentController {
    private final CommentService commentService;
    private final BookService bookService;

    @GetMapping("/new")
    public String create(@RequestParam("bookId") long bookId, Model model) {
        bookService.getBookById(bookId).orElseThrow();
        model.addAttribute("bookId", bookId);
        model.addAttribute("comment", new Comment());
        return "comment/new";
    }

    @PostMapping("/new")
    public String create(@Valid Comment comment, BindingResult bindingResult,
                         @RequestParam("bookId") long bookId, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bookId", bookId);
            return "comment/new";
        }
        commentService.addNewComment(comment.getText(), bookId).orElseThrow(NotFoundException::new);
        return "redirect:/book?id=" + bookId;
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("id") long id, @RequestParam("bookId") long bookId) {
        commentService.deleteComment(id);
        return "redirect:/book?id=" + bookId;
    }
}
