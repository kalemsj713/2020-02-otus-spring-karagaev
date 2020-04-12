package ru.kalemsj713.otus.exercise.dao;

import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Genre;

import java.util.List;

public interface GenreDao {

	Genre getGenre(Long id);

	void deleteGenre(Genre genre);

	void createGenre(Genre genre);


}
