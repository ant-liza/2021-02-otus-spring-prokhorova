package ru.otus.books;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/*
В этот файл выделяем EnableMongock и EnableMongoRepositories
это нужно для того, чтобы запустить тесты
 */
@Configuration
@EnableMongock
@EnableMongoRepositories
public class AppConfig {
}
