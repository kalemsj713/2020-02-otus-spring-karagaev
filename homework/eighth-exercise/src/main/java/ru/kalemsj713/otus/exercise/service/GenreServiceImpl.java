package ru.kalemsj713.otus.exercise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public String addNewGenre(String title) {
        Genre genre = genreRepository.save(new Genre(title));
        return String.format("new Genre created:%s", genre);
    }
    @Transactional
    @Override
    public String saveGenre(String id, String title) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isPresent()) {
            genre.get().setTitle(title);
            genreRepository.save(genre.get());
            return String.format("Genre saved: %s", genre.get());
        } else {
            return String.format("Genre with id:%s not found", id);
        }
    }

    @Transactional
    @Override
    public String deleteGenre(String id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isPresent()) {
            genreRepository.delete(genre.get());
            return String.format("Genre with id:%s deleted", id);
        } else {
            return String.format("Genre with id:%s not found", id);
        }

    }

    @Override
    public String getGenre(String title) {
        List<Genre> genres = genreRepository.findGenresByTitle(title);
        if (genres.isEmpty()) {
            return String.format("Genre(s) with title:%s not found", title);
        } else {
            return String.format("Founded genres:%s", genres);
        }
    }
}
