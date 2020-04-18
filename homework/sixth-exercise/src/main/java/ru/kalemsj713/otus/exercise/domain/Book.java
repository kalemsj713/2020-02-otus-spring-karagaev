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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "title", nullable = false)
	private String title;


	@OneToMany(targetEntity = Comment.class, orphanRemoval = true, fetch = FetchType.LAZY )
	@JoinColumn(name = "book_id")
	private List<Comment> comments;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Book book = (Book) o;
		return getId() == book.getId() &&
				Objects.equals(getTitle(), book.getTitle());
	}



	@Override
	public String toString() {
		return "Book{" +
				"title='" + title + '\'' +
				'}';
	}

	public String toStringInformative() {
		return "Book{" +
				"title='" + title + '\'' +
				", comments='" + comments + '\'' +
				'}';
	}
}
