insert into genres (title)
values ('Genre1'),
       ('Genre2'),
       ('Genre3'),
       ('Genre4'),
       ('Genre5');

insert into authors(name)
values ('author1'),
       ('author2'),
       ('author3'),
       ('author4');

insert into books(title)
values ('книга1'),
       ('книга2'),
       ('книга3'),
       ('книга4'),
       ('книга5');

insert into comments(book_id, text)
values (1, 'комментарий1'),
       (2, 'комментарий2'),
       (3, 'комментарий3'),
       (4, 'комментарий4');

insert into books_authors(book_id, author_id)
values (1, 1),
       (1, 2),
       (2, 2),
       (2, 3),
       (2, 1);

insert into books_genres(book_id, genre_id)
values (1, 1),
       (1, 2),
       (2, 2),
       (2, 3),
       (2, 1);