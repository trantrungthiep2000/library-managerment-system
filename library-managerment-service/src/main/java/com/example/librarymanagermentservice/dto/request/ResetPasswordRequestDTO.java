package com.example.librarymanagermentservice.dto.request;

import com.example.librarymanagermentservice.common.Constant;
import com.example.librarymanagermentservice.common.message.UserMessageError;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Information about reset password request DTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequestDTO {
    @NotBlank(message = UserMessageError.TOKEN_NOT_BLANK)
    private String token;

    @NotBlank(message = UserMessageError.NEW_PASSWORD_NOT_BLANK)
    @Size(min = 8, max = 32, message = UserMessageError.NEW_PASSWORD_LENGTH_BETWEEN_8_32_CHARACTERS)
    @Pattern(regexp = Constant.PATTERN_PASSWORD, message = UserMessageError.NEW_PASSWORD_NOT_FORMAT)
    private String newPassword;
}
