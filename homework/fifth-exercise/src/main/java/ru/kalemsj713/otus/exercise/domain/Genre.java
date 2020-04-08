package ru.kalemsj713.otus.exercise.domain;

import java.util.Objects;

public class Genre {
	private final Long id;
	private final String title;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Genre genre = (Genre) o;
		return Objects.equals(getId(), genre.getId()) &&
				Objects.equals(getTitle(), genre.getTitle());
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

	@Override
	public String toString() {
		return "Genre{" +
				"id=" + id +
				", title='" + title + '\'' +
				'}';
	}

	public Genre(Long id, String title) {
		this.id = id;
		this.title = title;
	}
}
