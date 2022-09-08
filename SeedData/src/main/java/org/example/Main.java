package org.example;

import org.apache.commons.lang3.ArrayUtils;
import org.example.classes.PassEncTech2;
import org.example.entities.Role;
import org.example.entities.User;
import org.example.interfaces.IDatabaseSeed;
import org.example.repositories.RoleRepository;
import org.example.repositories.UserRepository;
import org.example.storage.IStorageService;
import org.example.storage.StorageProperties;
import org.hibernate.mapping.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Main {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner init(IStorageService storageService, IDatabaseSeed seed) {
        return (args) -> {
            try {
                seed.Seed();
                storageService.init();
            }
            catch(Exception ex) {
                System.out.println(ex);
            }
        };
    }
}