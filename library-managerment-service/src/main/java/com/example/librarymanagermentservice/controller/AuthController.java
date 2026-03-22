package com.example.librarymanagermentservice.controller;

import com.example.librarymanagermentservice.common.ApiRoutesConstant;
import com.example.librarymanagermentservice.dto.request.ForgotPasswordRequestDTO;
import com.example.librarymanagermentservice.dto.request.LoginRequestDTO;
import com.example.librarymanagermentservice.dto.request.ResetPasswordRequestDTO;
import com.example.librarymanagermentservice.dto.request.UserRequestDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.dto.response.AuthResponseDTO;
import com.example.librarymanagermentservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Information about auth controller.
 */
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutesConstant.Auth.Auth)
public class AuthController {
    private final AuthService authService;

    /**
     * Login.
     * @param requestDTO LoginRequestDTO.
     * @return AuthResponseDTO.
     */
    @PostMapping(ApiRoutesConstant.Auth.LOGIN)
    public ResponseEntity<ApiSuccessResponseDTO<AuthResponseDTO>> login(@Valid @RequestBody LoginRequestDTO requestDTO) {
        var result = authService.login(requestDTO);
        return ResponseEntity.ok(result);
    }

    /**
     * Signup.
     * @param requestDTO UserRequestDTO.
     * @return AuthResponseDTO.
     */
    @PostMapping(ApiRoutesConstant.Auth.SIGNUP)
    public ResponseEntity<ApiSuccessResponseDTO<AuthResponseDTO>> signup(@Valid @RequestBody UserRequestDTO requestDTO) {
        var result = authService.signup(requestDTO);
        return ResponseEntity.ok(result);
    }

    /**
     * Forgot password.
     * @param requestDTO ForgotPasswordRequestDTO.
     * @return Boolean.
     */
    @PostMapping(ApiRoutesConstant.Auth.FORGOT_PASSWORD)
    public ResponseEntity<ApiSuccessResponseDTO<Boolean>> forgotPassword(@Valid @RequestBody ForgotPasswordRequestDTO requestDTO) {
        var result = authService.createPasswordResetToken(requestDTO.getEmail());
        return ResponseEntity.ok(result);
    }

    /**
     * Reset password.
     * @param requestDTO ResetPasswordRequestDTO.
     * @return Boolean.
     */
    @PostMapping(ApiRoutesConstant.Auth.RESET_PASSWORD)
    public ResponseEntity<ApiSuccessResponseDTO<Boolean>> resetPassword(@Valid @RequestBody ResetPasswordRequestDTO requestDTO) {
        var result = authService.resetPassword(requestDTO.getToken(), requestDTO.getNewPassword());
        return ResponseEntity.ok(result);
    }
}