package com.example.stategrid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.stategrid.mapper")
public class StategridApplication {

    public static void main(String[] args) {
        SpringApplication.run(StategridApplication.class, args);
    }

}
