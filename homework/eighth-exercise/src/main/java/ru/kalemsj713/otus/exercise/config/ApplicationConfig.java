package ru.kalemsj713.otus.exercise.config;

import com.github.cloudyrock.mongock.Mongock;
import com.github.cloudyrock.mongock.SpringMongockBuilder;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    private static final String CHANGELOGS_PACKAGE = "ru.kalemsj713.otus.exercise.changelog";

    @Bean
    public Mongock mongock(ru.kalemsj713.otus.exercise.config.MongoProps mongoProps, MongoClient mongoClient) {
        return new SpringMongockBuilder(mongoClient, mongoProps.getDatabase(), CHANGELOGS_PACKAGE)
                .build();
    }
}
