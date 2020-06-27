package com.wednesday.assignment.relaxicab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.wednesday.assignment.relaxicab")
@EntityScan("com.wednesday.assignment.relaxicab.data.entity")
public class RelaxiCabApplication {

    public static void main(String[] args) {
        SpringApplication.run(RelaxiCabApplication.class, args);
    }

}
