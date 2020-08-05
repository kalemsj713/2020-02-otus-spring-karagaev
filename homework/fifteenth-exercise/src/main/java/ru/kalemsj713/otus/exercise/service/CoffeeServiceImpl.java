package ru.kalemsj713.otus.exercise.service;

import org.jline.reader.LineReader;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.kalemsj713.otus.exercise.domain.Coffee;
import ru.kalemsj713.otus.exercise.domain.MenuItem;
import ru.kalemsj713.otus.exercise.integration.CafeGateway;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CoffeeServiceImpl implements CoffeeService {
    private final MenuService menuService;
    private final CafeGateway cafeGateway;
    private final LineReader reader;

    public CoffeeServiceImpl(MenuService menuService, CafeGateway cafeGateway, @Lazy LineReader reader) {
        this.menuService = menuService;
        this.cafeGateway = cafeGateway;
        this.reader = reader;
    }


    @Override
    public List<Coffee> createCoffeeOrder() {
        List<Coffee> dishes = new ArrayList<>();
        System.out.println("Choose coffee from our menu: ");
        menuService.getMenu()
                .forEach(item -> System.out.println("id = " +
                        item.getId() +
                        " name = " +
                        item.getName()));
        String orderedDishes = reader
                .readLine("Please pick numbers of position from menu: ");

        List<MenuItem> items = Arrays.stream(orderedDishes.split(" "))
                .map(s -> menuService.findById(Integer.valueOf(s)))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (!items.isEmpty()) {
            dishes = cafeGateway.processOrder(items);
            System.out.println("Your coffee(s) is ready:");
            dishes.forEach(System.out::println);
        }
        return dishes;
    }
}
