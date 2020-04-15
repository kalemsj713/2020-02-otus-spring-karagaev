package ru.kalemsj713.otus.exercise.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;


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
	private String name;

   	@BatchSize(size = 100)
	@ManyToMany
	@JoinTable(name = "books_authors",
			joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
	private Set<Book> books;

	@Override
	public String toString() {
		return "Author{" +
				"name='" + name + '\'' +
				", books=" + books +
				'}';
	}

}
