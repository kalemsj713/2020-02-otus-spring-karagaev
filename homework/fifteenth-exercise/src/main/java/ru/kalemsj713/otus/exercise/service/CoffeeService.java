package ru.kalemsj713.otus.exercise.service;

import org.springframework.stereotype.Service;
import ru.kalemsj713.otus.exercise.domain.Coffee;

import java.util.List;

@Service
public interface CoffeeService {
    List<Coffee> createCoffeeOrder();
}
