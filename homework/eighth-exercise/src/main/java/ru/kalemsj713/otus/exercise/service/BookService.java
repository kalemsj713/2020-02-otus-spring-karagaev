package ru.kalemsj713.otus.exercise.service;

public interface BookService {

    String addNewBook(String title);

    String saveBook(String id, String title);

    String deleteBook(String id);

    String getBook(String title);

    String getBookCount();

    String getComments(String id);

    String addGenreBookRelations(String bid, String gid);

    String addAuthorBookRelations(String bid, String aid);

    String deleteGenreBookRelations(String bid, String gid);

    String deleteAuthorBookRelations(String bid, String aid);
}
