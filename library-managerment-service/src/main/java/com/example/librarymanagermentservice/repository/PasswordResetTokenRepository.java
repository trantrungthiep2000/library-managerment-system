package com.example.librarymanagermentservice.repository;

import com.example.librarymanagermentservice.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Information about interface password reset token repository.
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    /**
     * Find by token.
     * @param token String.
     * @return PasswordResetToken.
     */
    Optional<PasswordResetToken> findByToken(String token);
}
