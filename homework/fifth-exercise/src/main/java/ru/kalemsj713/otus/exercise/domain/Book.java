package ru.kalemsj713.otus.exercise.domain;

import java.util.Objects;

public class Book {
	private final Long id;
	private final String title;

	public Book(Long id, String title) {
		this.id = id;
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", title='" + title + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Book book = (Book) o;
		return Objects.equals(getId(), book.getId()) &&
				Objects.equals(getTitle(), book.getTitle());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getTitle());
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}


}
