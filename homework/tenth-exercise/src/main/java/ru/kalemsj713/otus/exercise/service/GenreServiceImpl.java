package ru.kalemsj713.otus.exercise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;


    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }


    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Optional<Genre> getGenreById(long id) {
        return genreRepository.findById(id);
    }
}
