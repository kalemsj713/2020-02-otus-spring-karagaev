package ru.kalemsj713.otus.exercise.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryController {

    @GetMapping("/")
    public String mainPage() {
        return "list";
    }

    @GetMapping("book")
    public String show() {
        return "book/show";
    }

    @GetMapping("book/new")
    public String create() {
        return "book/new";
    }

}
