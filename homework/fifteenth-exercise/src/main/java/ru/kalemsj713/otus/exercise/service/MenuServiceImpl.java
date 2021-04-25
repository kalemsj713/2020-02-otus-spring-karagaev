package ru.kalemsj713.otus.exercise.service;

import org.springframework.stereotype.Service;
import ru.kalemsj713.otus.exercise.domain.MenuItem;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private static List<MenuItem> menuItems = new ArrayList<>();

    @Override
    public List<MenuItem> getMenu() {
        if (menuItems.isEmpty()) {
            generateMenu();
        }
        return menuItems;
    }

    @Override
    public MenuItem findById(Integer id) {
        if (menuItems.isEmpty()) {
            generateMenu();
        }
        return menuItems.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }

    private void generateMenu() {
        List<MenuItem> list = new ArrayList<>();
        list.add(new MenuItem(1, "Espresso"));
        list.add(new MenuItem(2, "Americano"));
        list.add(new MenuItem(3, "Black Eye"));
        list.add(new MenuItem(4, "Flat White"));
        list.add(new MenuItem(5, "Latte"));
        menuItems = list;

    }
}
