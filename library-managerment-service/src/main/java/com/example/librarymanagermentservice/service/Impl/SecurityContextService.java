package com.example.librarymanagermentservice.service.Impl;

import com.example.librarymanagermentservice.model.User;
import com.example.librarymanagermentservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Information about security context service.
 */
@Component
@AllArgsConstructor
public class SecurityContextService {
    private final UserRepository userRepository;

    /**
     * Get current user.
     * @return Optional<User>.
     */
    public Optional<User> getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email);
    }
}
