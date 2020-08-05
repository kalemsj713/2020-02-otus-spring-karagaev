package ru.kalemsj713.otus.exercise.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.kalemsj713.otus.exercise.service.CoffeeService;

@RequiredArgsConstructor
@ShellComponent
public class ShellManipulator {

    private final CoffeeService coffeeService;

    @ShellMethod(value = "Coffee order creation", key = {"create", "c"})
    public void createOrder() {
        coffeeService.createCoffeeOrder();
    }
}
