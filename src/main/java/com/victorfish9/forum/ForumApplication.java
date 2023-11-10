package com.victorfish9.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ForumApplication extends SpringBootServletInitializer {

    //private static final Logger log = LoggerFactory.getLogger(ForumApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(ForumApplication.class);
    }

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
