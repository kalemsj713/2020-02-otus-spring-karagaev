package ru.kalemsj713.otus.exercise.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.repository.GenreRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = GenreServiceImpl.class)
class GenreServiceTest {
    @MockBean
    private GenreRepository genreRepository;
    @Autowired
    private GenreService genreService;

    @BeforeEach
    void setUp() {
        given(genreRepository.save(new Genre("123"))).willReturn(new Genre(1L, "123"));
        given(genreRepository.save(new Genre(2L, "123"))).willReturn(new Genre(2L, "123"));
        given(genreRepository.findById(2L)).willReturn(Optional.of(new Genre(2L, "123")));
    }

    @Test
    void saveGenre() {

        Genre actualGenre = genreService.saveGenre(new Genre("123"));
        assertThat(actualGenre).isEqualToComparingFieldByField(new Genre(1L, "123"));
        verify(genreRepository, times(1)).save(new Genre("123"));
    }

    @Test
    void deleteGenre() {
        genreService.deleteGenre(1L);
        verify(genreRepository, times(1)).deleteById(1L);

    }

    @Test
    void findAll() {
        genreService.findAll();
        verify(genreRepository, times(1)).findAll();
    }

    @Test
    void getGenreById() {
        Optional<Genre> genreGood = genreService.getGenreById(2L);
        Optional<Genre> genreBad = genreService.getGenreById(1L);
        assertThat(genreBad.isEmpty());
        assertThat(genreGood.isPresent());
        assertThat(genreGood.get()).isEqualToComparingFieldByField(new Genre(2L, "123"));


    }
}