package ru.kalemsj713.otus.exercise.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.service.AuthorService;
import ru.kalemsj713.otus.exercise.service.BookService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("author")

@RequiredArgsConstructor
public class AuthorController {

    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("author", new Author());
        List<Book> books = bookService.findAll();
        model.addAttribute("allBooks", books);
        return "author/new";
    }

    @PostMapping("/new")
    public String create(@Valid Author author, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("author", author);
            List<Book> books = bookService.findAll();
            model.addAttribute("allBooks", books);
            return "author/new";
        }
        authorService.saveAuthor(author);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id, Model model) {
        Author author = authorService.getAuthorById(id).orElseThrow(NotFoundException::new);

        model.addAttribute("author", author);
        List<Book> books = bookService.findAll();
        model.addAttribute("allBooks", books);
        return "author/edit";
    }

    @PostMapping("/edit")
    public String saveAuthor(@Valid Author author, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("author", author);
            List<Book> books = bookService.findAll();
            model.addAttribute("allBooks", books);
            return "author/edit";
        }
        authorService.saveAuthor(author);
        return "redirect:/author?id=" + author.getId();
    }


    @GetMapping("/delete")
    public RedirectView delete(@RequestParam("id") long id) {
        authorService.deleteAuthor(id);
        return new RedirectView("/");

    }

    @GetMapping
    public String show(@RequestParam("id") long id, Model model) {
        Author author = authorService.getAuthorById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("author", author);
        return "author/show";
    }

}
