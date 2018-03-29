package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration 
@SpringBootApplication(scanBasePackages="com.example.demo")
public class SocialMediaManagerApplication {

    @Autowired
    private static UserRepository repository;
    
	public static void main(String[] args) {
		SpringApplication.run(SocialMediaManagerApplication.class, args);
                
	}
}
