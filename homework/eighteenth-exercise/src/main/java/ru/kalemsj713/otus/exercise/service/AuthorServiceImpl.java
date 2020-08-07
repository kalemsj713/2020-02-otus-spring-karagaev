package ru.kalemsj713.otus.exercise.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @HystrixCommand(fallbackMethod = "defaultSaveAuthor", commandKey = "author")
    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @HystrixCommand(fallbackMethod = "defaultDeleteAuthor", commandKey = "author")
    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @HystrixCommand(fallbackMethod = "defaultFindAllAuthor", commandKey = "author")
    @Override
    public List<Author> findAll() {
         return authorRepository.findAll();
    }

    @HystrixCommand(fallbackMethod = "defaultGetAuthorById", commandKey = "author")
    @Override
    public Optional<Author> getAuthorById(long id) {
        return authorRepository.findById(id);
    }

    public Author defaultSaveAuthor(Author author) {
        return new Author(-1L, "Pushkin", new ArrayList<>());
    }

    public void defaultDeleteAuthor(Long id) {
    }

    public List<Author> defaultFindAllAuthor() {
        return Collections.singletonList(new Author(-1L, "Pushkin", new ArrayList<>()));
    }

    public Optional<Author> defaultGetAuthorById(long id) {
        return Optional.of(new Author(-1L, "Pushkin", new ArrayList<>()));
    }
}
