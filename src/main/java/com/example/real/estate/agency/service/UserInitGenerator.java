package com.example.real.estate.agency.service;

import com.example.real.estate.agency.entity.User;
import com.example.real.estate.agency.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInitGenerator {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void generateUsers() {
        List<User> users = List.of(
            new User(
                null,
                "admin",
                passwordEncoder.encode("admin"),
                "admin@example.org"
            ),
            new User(
                null,
                "user",
                passwordEncoder.encode("user"),
                "user@example.org"
            )
        );
        userRepository.saveAll(users);
    }
}
