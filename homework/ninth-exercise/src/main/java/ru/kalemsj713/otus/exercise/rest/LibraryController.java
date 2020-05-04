package ru.kalemsj713.otus.exercise.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.service.AuthorService;
import ru.kalemsj713.otus.exercise.service.BookService;
import ru.kalemsj713.otus.exercise.service.CommentService;
import ru.kalemsj713.otus.exercise.service.GenreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LibraryController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;


    @GetMapping("/")
    public String mainPage(Model model) {
        List<Book> books = bookService.findAll();
        List<Genre> genres = genreService.findAll();
        List<Author> authors = authorService.findAll();
        model.addAttribute("books", books);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "list";
    }

//    @GetMapping("/editBook")
//    public String editBook(@RequestParam("id") long id, Model model) {
//        Book book = bookService.getBookById(id).orElseThrow(ru.kalemsj713.otus.exercise.rest.NotFoundException::new);
//        model.addAttribute("book", book);
//        model.addAttribute("entityType", "book");
//        return "edit";
//    }

//    @PostMapping("/edit")
//    public String savePerson(
//            Person person,
//            Model model
//                            ) {
//        Person saved = repository.save(person);
//        model.addAttribute(saved);
//        return "edit";
//    }
}
