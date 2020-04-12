package com.trash.collection.trash;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.trash.collection.trash.*.dao")
public class TrashApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrashApplication.class, args);
    }
}
