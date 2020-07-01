drop table authors if exists;
drop table genres if exists;
drop table books if exists;
drop table books_authors if exists;
drop table books_genres if exists;
drop table users if exists;
drop table roles if exists;
drop table users_roles if exists;

create table users
(
    id        bigint       not null auto_increment,
    fio       varchar(255) not null,
    login     varchar(255) not null,
    password  varchar(255) not null,
    is_active varchar(2)   not null,
    primary key (id)
);
create table roles
(
    id   bigint       not null auto_increment,
    name varchar(255) not null,
    primary key (id)
);
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

create table users_roles
(
    user_id BIGINT,
    role_id BIGINT,
    foreign key (user_id) references users (id) on delete cascade,
    foreign key (role_id) references roles (id) on delete cascade
);
create table books_genres
(
    book_id  BIGINT,
    genre_id BIGINT,
    foreign key (book_id) references books (id) on delete cascade,
    foreign key (genre_id) references genres (id) on delete cascade
);
