package ru.kalemsj713.otus.exercise.service;

import ru.kalemsj713.otus.exercise.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {


    void deleteGenre(Long id);

    List<Genre> findAll();

    Genre saveGenre(Genre genre);

    Optional<Genre> getGenreById(long id);
}
