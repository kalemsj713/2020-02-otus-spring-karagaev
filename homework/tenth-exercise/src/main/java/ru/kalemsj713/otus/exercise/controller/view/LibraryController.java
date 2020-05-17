package ru.kalemsj713.otus.exercise.controller.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kalemsj713.otus.exercise.dto.AuthorDTO;
import ru.kalemsj713.otus.exercise.dto.BookDTO;
import ru.kalemsj713.otus.exercise.dto.GenreDTO;
import ru.kalemsj713.otus.exercise.service.AuthorService;
import ru.kalemsj713.otus.exercise.service.BookService;
import ru.kalemsj713.otus.exercise.service.GenreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LibraryController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("/")
    public String mainPage(Model model) {
        List<BookDTO> books = bookService.findAll();
        List<GenreDTO> genres = genreService.findAll();
        List<AuthorDTO> authors = authorService.findAll();
        model.addAttribute("books", books);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "list";
    }

}
