package com.example.librarymanagermentservice.service.Impl;

import com.example.librarymanagermentservice.common.enums.UserRoleEnum;
import com.example.librarymanagermentservice.model.User;
import com.example.librarymanagermentservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Information about data initialization component.
 */
@Slf4j
@Component
@AllArgsConstructor
public class DataInitializationComponent implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Run.
     * @param args String.
     */
    @Override
    public void run(String... args) {
        try {
            initializationAdminUser();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    /**
     * Initialization admin user.
     */
    private void initializationAdminUser() {
        String adminEmail = "thiepdepzai2k@gmail.com";
        String adminPassword = "Thiep@2000";
        String adminFullName = "admin";
        String adminPhoneNumber = "0987654321";

        User user = userRepository.findByEmail(adminEmail).orElse(null);
        if (user == null) {
            User admin = User.builder()
                    .password(passwordEncoder.encode(adminPassword))
                    .email(adminEmail)
                    .role(UserRoleEnum.ADMIN)
                    .phoneNumber(adminPhoneNumber)
                    .lastLogin(LocalDateTime.now())
                    .fullName(adminFullName)
                    .build();

            userRepository.save(admin);
        }
    }
}
