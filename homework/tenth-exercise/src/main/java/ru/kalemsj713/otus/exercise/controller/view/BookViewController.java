package ru.kalemsj713.otus.exercise.controller.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.service.BookService;

import javax.validation.Valid;

@Controller
@RequestMapping("book")
@RequiredArgsConstructor
public class BookViewController {

    private final BookService bookService;

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("book", new Book());
        return "book/new";
    }

    @PostMapping("/new")
    public String create(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            return "book/new";
        }
        book = bookService.saveBook(book);
        return "redirect:/book?id=" + book.getId();
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id, Model model) {
        Book book = bookService.getBookById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "book/edit";
    }

    @PostMapping("/edit")
    public String saveBook(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            return "book/edit";
        }
        bookService.saveBook(book);
        return "redirect:/book?id=" + book.getId();
    }


    @GetMapping("/delete")
    public RedirectView delete(@RequestParam("id") long id) {
        bookService.deleteBook(id);
        return new RedirectView("/");
    }

    @GetMapping
    public String show() {
        return "book/show";
    }
}
