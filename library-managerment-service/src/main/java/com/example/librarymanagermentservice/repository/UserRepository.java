package com.example.librarymanagermentservice.repository;

import com.example.librarymanagermentservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Information about interface user repository.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by email.
     * @param email String.
     * @return User.
     */
    Optional<User> findByEmail(String email);
}
