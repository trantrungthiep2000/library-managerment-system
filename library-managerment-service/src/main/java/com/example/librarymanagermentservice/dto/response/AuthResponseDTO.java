package com.example.librarymanagermentservice.dto.response;

import com.example.librarymanagermentservice.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Information about auth response DTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {
    private String token;
    private UserDTO user;
}