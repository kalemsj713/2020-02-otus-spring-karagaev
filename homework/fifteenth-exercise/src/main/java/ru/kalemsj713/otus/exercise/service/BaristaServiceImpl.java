package ru.kalemsj713.otus.exercise.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.kalemsj713.otus.exercise.domain.Coffee;
import ru.kalemsj713.otus.exercise.domain.MenuItem;

@Service
public class BaristaServiceImpl implements BaristaService {
    private static final Logger LOG = LoggerFactory.getLogger(BaristaServiceImpl.class);

    @Override
    public Coffee process(MenuItem menuItem) {
        if (menuItem.getId() == 5) {
            throw new CoffeeException("Milk out");
        }
        return Coffee.builder().name(menuItem.getName()).build();
    }

    @Override
    public Coffee errors(String message) {
        LOG.error(message);
        System.out.println("Barista say: Sorry, please choose another positions.");
        return Coffee.builder().name(null).build();
    }
}
