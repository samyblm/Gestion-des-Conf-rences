package com.example.demo.conference;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class ConferenceConfig {
    @Bean
    CommandLineRunner commandLineRunner(ConferenceRepository repository){
        return args -> {
            Conference conf1 = new Conference(
                    "tech conference",
                    LocalDate.of(2018, Month.JANUARY,12),
                    "tech company",
                    "what u should know about tech ",
                    "many conditions",
                    true
            );
            Conference conf2 = new Conference(
                    "covid_19",
                    LocalDate.of(2019, Month.SEPTEMBER,28),
                    "healthcompany",
                    "featuring the latest in covide-19 ",
                    "many conditions",
                    true
            );
            repository.saveAll(
                    List.of(conf1,conf2)
            );


        };
    }

}
