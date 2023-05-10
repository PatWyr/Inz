package com.aws.inzynierka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class InzynierkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(InzynierkaApplication.class, args);
    }

}
