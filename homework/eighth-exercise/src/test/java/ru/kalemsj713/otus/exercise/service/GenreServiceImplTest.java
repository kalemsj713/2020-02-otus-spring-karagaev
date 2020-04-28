package ru.kalemsj713.otus.exercise.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = GenreServiceImpl.class)
class GenreServiceImplTest {
    @MockBean
    private GenreRepository genreRepository;
    @Autowired
    private GenreService genreService;

    @BeforeEach
    void setUp() {
        given(genreRepository.save(new Genre("123"))).willReturn(new Genre("1", "123"));
        given(genreRepository.save(new Genre("2", "123"))).willReturn(new Genre("2", "123"));
        given(genreRepository.findById("2")).willReturn(Optional.of(new Genre("2", "123")));
        given(genreRepository.findGenresByTitle("2")).willReturn(List.of(new Genre("1", "2")));
    }

    @Test
    void addNewGenre() {
        String expected = genreService.addNewGenre("123");
        verify(genreRepository, times(1)).save(new Genre("123"));
        assertEquals(expected, "new Genre created:Genre(id=1, title=123)");
    }

    @Test
    void saveGenre() {
        String badExpected = genreService.saveGenre("1", "321");
        String expected = genreService.saveGenre("2", "321");
        verify(genreRepository, times(2)).findById(any());
        verify(genreRepository, times(1)).save(new Genre("2", "321"));
        assertEquals(expected, "Genre saved: Genre(id=2, title=321)");
        assertEquals(badExpected, "Genre with id:1 not found");

    }

    @Test
    void deleteGenre() {
        String badExpected = genreService.deleteGenre("1");
        String expected = genreService.deleteGenre("2");
        verify(genreRepository, times(2)).findById(any());
        verify(genreRepository, times(1)).delete(new Genre("2", "123"));
        assertEquals(expected, "Genre with id:2 deleted");
        assertEquals(badExpected, "Genre with id:1 not found");
    }

    @Test
    void getGenre() {
        String badExpected = genreService.getGenre("1");
        String expected = genreService.getGenre("2");
        verify(genreRepository, times(2)).findGenresByTitle(any());
        assertEquals(expected, "Founded genres:[Genre(id=1, title=2)]");
        assertEquals(badExpected, "Genre(s) with title:1 not found");
    }
}