package com.example.librarymanagermentservice.service;

import com.example.librarymanagermentservice.dto.request.LoginRequestDTO;
import com.example.librarymanagermentservice.dto.request.UserRequestDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.dto.response.AuthResponseDTO;

/**
 * Information about interface auth service.
 */
public interface AuthService {

    /**
     * Login.
     * @param requestDTO LoginRequestDTO.
     * @return AuthResponseDTO.
     */
    ApiSuccessResponseDTO<AuthResponseDTO> login(LoginRequestDTO requestDTO);

    /**
     * signup.
     * @param requestDTO UserRequestDTO.
     * @return AuthResponseDTO.
     */
    ApiSuccessResponseDTO<AuthResponseDTO> signup(UserRequestDTO requestDTO);

    /**
     * Create password reset token.
     * @param email String.
     * @return Boolean.
     */
    ApiSuccessResponseDTO<Boolean> createPasswordResetToken(String email);

    /**
     * Reset password.
     * @param token String.
     * @param newPassword String.
     * @return Boolean.
     */
    ApiSuccessResponseDTO<Boolean> resetPassword(String token, String newPassword);
}