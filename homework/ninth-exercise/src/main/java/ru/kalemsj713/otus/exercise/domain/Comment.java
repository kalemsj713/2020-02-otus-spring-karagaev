package ru.kalemsj713.otus.exercise.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text", nullable = false)
    @NotEmpty(message = "Please provide a text")
    private String text;

    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id", nullable = false, referencedColumnName = "id")
    private Book book;

    public Comment(String text) {
        this.text = text;
    }


    public Comment(long id, String text) {
        this.text = text;
        this.id = id;
    }

    public Comment(String text, Book book) {
        this.text = text;
        this.book = book;
    }
}
