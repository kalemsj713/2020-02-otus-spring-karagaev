package ru.kalemsj713.otus.exercise.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RequiredArgsConstructor
@Component
public class DataBaseHealthCheck implements HealthIndicator {
    private final DataSource dataSource;

    @Override
    public Health health() {
        try (Connection ignored = dataSource.getConnection()) {
            return Health.up().withDetail("DB STATUS", "OK").build();
        } catch (SQLException e) {
            return Health.down().withDetail("DB STATUS", "DEAD").build();
        }
    }
}
