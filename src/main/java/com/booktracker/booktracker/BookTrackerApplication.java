package com.booktracker.booktracker;

import com.booktracker.booktracker.model.Author;
import com.booktracker.booktracker.repositories.AuthorRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@SpringBootApplication
@EnableConfigurationProperties(DataAstraxProperties.class)
@AllArgsConstructor
public class BookTrackerApplication {





    public static void main(String[] args) {
        SpringApplication.run(BookTrackerApplication.class, args);
    }

    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataAstraxProperties astraxProperties){
        Path bundle=astraxProperties.getSecureConnectBundle().toPath();
        return builder->builder.withCloudSecureConnectBundle(bundle);
    }






}
