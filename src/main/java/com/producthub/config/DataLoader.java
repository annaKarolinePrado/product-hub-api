package com.producthub.config;

import com.producthub.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @Bean
    CommandLineRunner init(UserService userService) {
        return args -> {
            userService.createDefaultUser();
        };
    }
}
