package ru.kalemsj713.otus.exercise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public String addNewAuthor(String name) {
        Author author = authorRepository.save(new Author(name));
        return String.format("new Author created:%s", author);
    }

    @Override
    public String saveAuthor(String id, String name) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            author.get().setName(name);
            authorRepository.save(author.get());
            return String.format("Author saved: %s", author.get());
        } else {
            return String.format("Author with id:%s not found", id);
        }
    }

    @Override
    public String deleteAuthor(String id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            authorRepository.delete(author.get());
            return String.format("Author with id:%s deleted", id);
        } else {
            return String.format("Author with id:%s not found", id);
        }

    }

    @Override
    public String getAuthor(String name) {
        List<Author> authors = authorRepository.findAuthorsByName(name);
        if (authors.isEmpty()) {
            return String.format("Author(s) with name:%s not found", name);
        } else {
            return String.format("Founded authors:%s", authors);
        }
    }
}
