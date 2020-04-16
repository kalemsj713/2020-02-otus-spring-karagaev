package ru.kalemsj713.otus.exercise.dao;

import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

	Optional<Genre> getGenre(Long id);

	Genre saveGenre(Genre genre);

	void deleteGenre(Long id);


}
