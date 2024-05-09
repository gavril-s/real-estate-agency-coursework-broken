package com.example.real.estate.agency.repository;

import com.example.real.estate.agency.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByName(String username);
    User getUserByName(String username);
    User getUserById(Long id);
    boolean existsByName(String username);
}
