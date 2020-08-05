package ru.kalemsj713.otus.exercise.integration;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.kalemsj713.otus.exercise.domain.Coffee;
import ru.kalemsj713.otus.exercise.domain.MenuItem;

import java.util.List;

@MessagingGateway
public interface CafeGateway {
    @Gateway(requestChannel = "ordersChannel", replyChannel = "coffeeChannel")
    List<Coffee> processOrder(List<MenuItem> items);
}

