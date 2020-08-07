package ru.kalemsj713.otus.exercise.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.repository.GenreRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    @HystrixCommand(fallbackMethod = "defaultDeleteGenre", commandKey = "genre")
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultFindAll", commandKey = "genre")
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultSaveGenre", commandKey = "genre")
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultGetGenreById", commandKey = "genre")
    public Optional<Genre> getGenreById(long id) {
        return genreRepository.findById(id);
    }

    public void defaultDeleteGenre(Long id) {
    }

    public List<Genre> defaultFindAll() {
        return Arrays.asList(new Genre(-1L, "Horror"));
    }

    public Genre defaultSaveGenre(Genre genre) {
        return new Genre(-1L, "Horror");
    }

    public Optional<Genre> defaultGetGenreById(long id) {
        return Optional.of(new Genre(-1L, "Horror"));
    }
}
