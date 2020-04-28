package ru.kalemsj713.otus.exercise.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Genre;

@RequiredArgsConstructor

public class BookCustomRepositoryImpl implements BookCustomRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public void removeAuthorArrayElementsById(String id) {
        val query = Query.query(Criteria.where("_id").is(new ObjectId(id)));
        val update = new Update().pull("authors", query);
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }

    @Override
    public void removeCommentArrayElementsById(String id) {
        val query = Query.query(Criteria.where("$id").is(new ObjectId(id)));
        val update = new Update().pull("comments", query);
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }

    @Override
    public void removeGenreArrayElementsById(String id) {
        val query = Query.query(Criteria.where("_id").is(new ObjectId(id)));
        val update = new Update().pull("genres", query);
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }

    @Override
    public void setAuthorArrayElementsById(Author author) {
        val query = Query.query(Criteria.where("authors._id").is(author.getId()));
        val update = new Update().set("authors.$.name", author.getName());
        mongoTemplate.updateMulti(query, update, Book.class);
    }

    @Override
    public void setGenreArrayElementsById(Genre genre) {
        val query = Query.query(Criteria.where("genres._id").is(genre.getId()));
        val update = new Update().set("genres.$.title", genre.getTitle());
        mongoTemplate.updateMulti(query, update, Book.class);
    }


}
