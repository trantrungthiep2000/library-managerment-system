package com.example.librarymanagermentservice.dto.request;

import com.example.librarymanagermentservice.common.Constant;
import com.example.librarymanagermentservice.common.message.UserMessageError;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Information about login request DTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @NotBlank(message = UserMessageError.EMAIL_NOT_BLANK)
    @Email(message = UserMessageError.EMAIL_NOT_FORMAT)
    @Size(max = 255, message = UserMessageError.EMAIL_MAX_LENGTH_255_CHARACTERS)
    private String email;

    @NotBlank(message = UserMessageError.PASSWORD_NOT_BLANK)
    @Size(min = 8, max = 32, message = UserMessageError.PASSWORD_LENGTH_BETWEEN_8_32_CHARACTERS)
    @Pattern(regexp = Constant.PATTERN_PASSWORD, message = UserMessageError.PASSWORD_NOT_FORMAT)
    private String password;
}
