package com.example.librarymanagermentservice.dto;

import com.example.librarymanagermentservice.common.enums.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Information about user DTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String email;
    private String phoneNumber;
    private String fullName;
    private UserRoleEnum role;
    private String userName;
    private LocalDateTime lastLogin;
}
