package com.example.librarymanagermentservice.model;

import com.example.librarymanagermentservice.common.enums.AuthProviderEnum;
import com.example.librarymanagermentservice.common.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Information about user.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private UserRoleEnum role;

    @Column(nullable = false)
    private String phoneNumber;

    private AuthProviderEnum authProvider = AuthProviderEnum.LOCAL;

    private String googleId;

    private String profileImage;

    @Column(nullable = false)
    private String password;

    private LocalDateTime lastLogin;
}
