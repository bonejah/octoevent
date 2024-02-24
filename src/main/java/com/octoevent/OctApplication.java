package com.octoevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class OctApplication {

    public static void main(String[] args) {
        SpringApplication.run(OctApplication.class, args);
    }
}
