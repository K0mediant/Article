package com.example.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.Map;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication//(scanBasePackages={"com.example.article.ArticleRepo", "com.example.article.application"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}