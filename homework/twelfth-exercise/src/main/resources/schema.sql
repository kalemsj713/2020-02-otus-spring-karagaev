drop table authors if exists;
drop table genres if exists;
drop table books if exists;
drop table books_authors if exists;
drop table books_genres if exists;

create table authors
(
    id   bigint       not null auto_increment,
    name varchar(255) not null,
    primary key (id)
);

create table genres
(
    id    bigint       not null auto_increment,
    title varchar(255) not null,
    primary key (id)
);

create table books
(
    id    bigint       not null auto_increment,
    title varchar(255) not null,
    primary key (id)
);

create table comments
(
    id      bigint        not null auto_increment,
    book_id bigint        not null auto_increment references books (id) on delete cascade,
    text    varchar(1000) not null,
    primary key (id)
);

create table books_authors
(
    book_id   BIGINT,
    author_id BIGINT,
    foreign key (book_id) references books (id) on delete cascade,
    foreign key (author_id) references authors (id) on delete cascade
);


create table books_genres
(
    book_id  BIGINT,
    genre_id BIGINT,
    foreign key (book_id) references books (id) on delete cascade,
    foreign key (genre_id) references genres (id) on delete cascade
);
