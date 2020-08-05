package ru.kalemsj713.otus.exercise.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.kalemsj713.otus.exercise.domain.mongo.AuthorOut;
import ru.kalemsj713.otus.exercise.domain.mongo.BookOut;
import ru.kalemsj713.otus.exercise.domain.mongo.GenreOut;
import ru.kalemsj713.otus.exercise.domain.sql.Author;
import ru.kalemsj713.otus.exercise.domain.sql.Book;
import ru.kalemsj713.otus.exercise.domain.sql.Genre;
import ru.kalemsj713.otus.exercise.repository.AuthorRepository;
import ru.kalemsj713.otus.exercise.repository.BookRepository;
import ru.kalemsj713.otus.exercise.repository.GenreRepository;

import java.util.HashMap;

@RequiredArgsConstructor
@EnableBatchProcessing
@Configuration
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final MongoTemplate mongoTemplate;

    private final GenreRepository genreRepository;

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final ModelMapper mapper;

    @Bean
    public ItemReader<Genre> genreReader() {
        return new RepositoryItemReaderBuilder<Genre>()
                .name("genreReader")
                .pageSize(20)
                .sorts(new HashMap<String, Sort.Direction>())
                .repository(genreRepository)
                .methodName("findAll")
                .build();
    }

    @Bean
    public ItemReader<Author> authorReader() {
        return new RepositoryItemReaderBuilder<Author>()
                .name("authorReader")
                .pageSize(20)
                .sorts(new HashMap<String, Sort.Direction>())
                .repository(authorRepository)
                .methodName("findAll")
                .build();
    }

    @Bean
    public ItemReader<Book> bookReader() {
        return new RepositoryItemReaderBuilder<Book>()
                .name("bookReader")
                .pageSize(20)
                .sorts(new HashMap<String, Sort.Direction>())
                .repository(bookRepository)
                .methodName("findAll")
                .build();
    }

    @Bean
    public ItemProcessor<? super Object, ?> genreProcessor() {
        return genre -> mapper.map(genre, GenreOut.class);
    }

    @Bean
    public ItemProcessor<? super Object, ?> bookProcessor() {
        return book -> mapper.map(book, BookOut.class);
    }

    @Bean
    public ItemProcessor<? super Object, ?> authorProcessor() {
        return author -> mapper.map(author, AuthorOut.class);
    }

    @Bean
    public ItemWriter<Genre> genreWriter() {
        return new MongoItemWriterBuilder<Genre>()
                .collection("genres")
                .template(mongoTemplate)
                .build();
    }

    @Bean
    public ItemWriter<Author> authorWriter() {
        return new MongoItemWriterBuilder<Author>()
                .collection("authors")
                .template(mongoTemplate)
                .build();
    }

    @Bean
    public ItemWriter<Book> bookWriter() {
        return new MongoItemWriterBuilder<Book>()
                .collection("books")
                .template(mongoTemplate)
                .build();
    }

    @Bean
    public Step genresImportStep(ItemReader<Genre> reader, ItemProcessor<? super Object, ?> itemProcessor, @Qualifier("GenreWriter") ItemWriter writer) {
        return stepBuilderFactory.get("genresImportStep")
                .chunk(5)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step authorImportStep(ItemReader<Author> reader, ItemProcessor<? super Object, ?> itemProcessor, @Qualifier("AuthorWriter") ItemWriter writer) {
        return stepBuilderFactory.get("authorsImportStep")
                .chunk(5)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step bookImportStep(ItemReader<Book> reader, ItemProcessor<? super Object, ?> itemProcessor, @Qualifier("BookWriter") ItemWriter writer) {
        return stepBuilderFactory.get("booksImportStep")
                .chunk(5)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job migrateDataJob() {
        return jobBuilderFactory.get("importDataJob")
                .start(genresImportStep(genreReader(), genreProcessor(), genreWriter()))
                .next(authorImportStep(authorReader(), authorProcessor(), authorWriter()))
                .next(bookImportStep(bookReader(), bookProcessor(), bookWriter()))
                .build();
    }
}