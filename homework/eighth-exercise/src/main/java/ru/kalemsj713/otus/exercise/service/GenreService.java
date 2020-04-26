package ru.kalemsj713.otus.exercise.service;

public interface GenreService {

    String addNewGenre(String title);

    String saveGenre(String id, String title);

    String deleteGenre(String id);

    String getGenre(String title);

}
