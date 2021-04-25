package ru.kalemsj713.otus.exercise.service;

import ru.kalemsj713.otus.exercise.domain.Coffee;
import ru.kalemsj713.otus.exercise.domain.MenuItem;

public interface BaristaService {
    Coffee process(MenuItem menuItem);

    Coffee errors(String message);
}
