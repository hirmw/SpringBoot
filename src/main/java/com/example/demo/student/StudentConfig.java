package com.example.demo.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

//populates the DB
@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRespository repository){

        return args -> {
            Student Mark = new Student(
                        "mark",
                        "mark@gmail",
                        LocalDate.of(2000, Month.MARCH,31)
            );

            Student Emma = new Student(
                    "Emma",
                    "emma@gmail",
                    LocalDate.of(2002, Month.MARCH,14)
            );

            repository.saveAll(
                    List.of(Mark, Emma)
            );

        };
    }
}
