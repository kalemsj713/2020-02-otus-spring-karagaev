package ru.kalemsj713.otus.exercise.repository;

import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Genre;

public interface BookCustomRepository {
    void removeAuthorArrayElementsById(String id);

    void removeCommentArrayElementsById(String id);

    void removeGenreArrayElementsById(String id);

    void setAuthorArrayElementsById(Author source);

    void setGenreArrayElementsById(Genre source);

}
