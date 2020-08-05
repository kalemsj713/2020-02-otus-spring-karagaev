package ru.kalemsj713.otus.exercise.service;

import org.springframework.stereotype.Service;
import ru.kalemsj713.otus.exercise.domain.MenuItem;

import java.util.List;

@Service
public interface MenuService {
    List<MenuItem> getMenu();

    MenuItem findById(Integer id);
}
