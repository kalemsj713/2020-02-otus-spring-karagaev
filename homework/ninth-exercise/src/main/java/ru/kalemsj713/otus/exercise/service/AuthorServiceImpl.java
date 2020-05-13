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
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }


    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);

    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> getAuthorById(long id) {
        return authorRepository.findById(id);
    }
}
