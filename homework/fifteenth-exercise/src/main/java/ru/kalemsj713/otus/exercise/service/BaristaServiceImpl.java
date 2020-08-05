package ru.kalemsj713.otus.exercise.service;

import org.springframework.stereotype.Service;
import ru.kalemsj713.otus.exercise.domain.Coffee;
import ru.kalemsj713.otus.exercise.domain.MenuItem;

@Service
public class BaristaServiceImpl implements BaristaService {
    @Override
    public Coffee process(MenuItem menuItem) {
        return Coffee.builder().name(menuItem.getName()).build();
    }
}
