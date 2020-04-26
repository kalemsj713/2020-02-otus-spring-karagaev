package ru.kalemsj713.otus.exercise.service;

public interface AuthorService {
    String addNewAuthor(String name);

    String saveAuthor(String id, String name);

    String deleteAuthor(String id);

    String getAuthor(String name);
}
