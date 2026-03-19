package com.example.librarymanagermentservice.dto.request;

import com.example.librarymanagermentservice.common.message.UserMessageError;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Forgot password request DTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordRequestDTO {
    @NotBlank(message = UserMessageError.EMAIL_NOT_BLANK)
    @Email(message = UserMessageError.EMAIL_NOT_FORMAT)
    @Size(max = 255, message = UserMessageError.EMAIL_MAX_LENGTH_255_CHARACTERS)
    private String email;
}
