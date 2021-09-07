package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class DemoApplication implements WebMvcConfigurer {


    String myExternalFilePath = "file:C:\\Users\\hp\\Desktop\\Gestion-des-Conf-rences-main\\Springboot_backend\\src\\main\\resources\\static\\";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations(myExternalFilePath);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }


}
