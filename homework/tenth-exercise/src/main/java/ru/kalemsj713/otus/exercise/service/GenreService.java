package ru.kalemsj713.otus.exercise.service;

import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.dto.GenreDTO;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    void deleteGenre(Long id);

    List<GenreDTO> findAll();

    Genre saveGenre(Genre genre);

    Optional<Genre> getGenreById(long id);
}
