package ru.kalemsj713.otus.exercise.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Please provide a name")
    private String name;

    @BatchSize(size = 100)
    @ManyToMany(targetEntity = Book.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private List<Book> books = new ArrayList<>();

    public Author(String name) {
        this.name = name;
    }


    public Author(long id, String name) {
        this.name = name;
        this.id = id;
    }
    public Author(  String name, List<Book>books) {
        this.name = name;
        this.books = books;
    }
}
