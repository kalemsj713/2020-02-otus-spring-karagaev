package ru.kalemsj713.otus.exercise.domain;

import java.util.Objects;

public class Author {
	private final Long id;
	private final String name;

	@Override
	public String toString() {
		return "Author{" +
				"id=" + id +
				", name='" + name + '\'' +

				'}';
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Author(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Author author = (Author) o;
		return Objects.equals(getId(), author.getId()) &&
				Objects.equals(getName(), author.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName());
	}
}
