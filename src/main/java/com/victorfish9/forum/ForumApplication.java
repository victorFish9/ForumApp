package com.victorfish9.forum;

import com.victorfish9.forum.models.User;
import com.victorfish9.forum.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ForumApplication {

    private static final Logger log = LoggerFactory.getLogger(ForumApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);

    }

/*
    @Bean
    public CommandLineRunner forumUload(UserRepository urepo){
        return (args -> {
            User admin = new User("user2", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "mikko", "USER");

            urepo.save(admin);
        });
    }*/

}
