package ru.kalemsj713.otus.exercise.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "text", nullable = false)
	private String text;

	@ManyToOne(targetEntity = Book.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "book_id", nullable = false, referencedColumnName = "id")
	private Book book;

	@Override
	public String toString() {
		return "Comment{" +
				"text='" + text + '\'' +
				",bookName='" + book.getTitle()  + '\'' +
				'}';
	}
}
