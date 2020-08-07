package ru.kalemsj713.otus.exercise.view;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.service.BookService;
import ru.kalemsj713.otus.exercise.service.GenreService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("genre")

@RequiredArgsConstructor
public class GenreController {

    private final BookService bookService;
    private final GenreService genreService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("genre", new Genre());
        List<Book> books = bookService.findAll();
        model.addAttribute("allBooks", books);
        return "genre/new";
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/new")
    public String create(@Valid Genre genre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genre", genre);
            List<Book> books = bookService.findAll();
            model.addAttribute("allBooks", books);
            return "genre/new";
        }
        genreService.saveGenre(genre);
        return "redirect:/";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id, Model model) {
        Genre genre = genreService.getGenreById(id).orElseThrow(NotFoundException::new);
        List<Book> books = bookService.findAll();
        model.addAttribute("allBooks", books);
        model.addAttribute("genre", genre);
        return "genre/edit";
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/edit")
    public String saveGenre(@Valid Genre genre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genre", genre);
            List<Book> books = bookService.findAll();
            model.addAttribute("allBooks", books);
            return "genre/edit";
        }
        genreService.saveGenre(genre);
        return "redirect:/genre?id=" + genre.getId();
    }


    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/delete")
    public RedirectView delete(@RequestParam("id") long id) {
        genreService.deleteGenre(id);
        return new RedirectView("/");
    }

    @GetMapping
    public String show(@RequestParam("id") long id, Model model) {
        Genre genre = genreService.getGenreById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("genre", genre);
        return "genre/show";
    }

}
