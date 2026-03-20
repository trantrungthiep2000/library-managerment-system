package com.example.librarymanagermentservice.dto.request;

import com.example.librarymanagermentservice.common.Constant;
import com.example.librarymanagermentservice.common.message.UserMessageError;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Information about user request DTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = UserMessageError.EMAIL_NOT_BLANK)
    @Email(message = UserMessageError.EMAIL_NOT_FORMAT)
    @Size(max = 255, message = UserMessageError.EMAIL_MAX_LENGTH_255_CHARACTERS)
    private String email;

    @NotBlank(message = UserMessageError.PASSWORD_NOT_BLANK)
    @Size(min = 8, max = 32, message = UserMessageError.PASSWORD_LENGTH_BETWEEN_8_32_CHARACTERS)
    @Pattern(regexp = Constant.PATTERN_PASSWORD, message = UserMessageError.PASSWORD_NOT_FORMAT)
    private String password;

    @NotBlank(message = UserMessageError.PHONE_NUMBER_NOT_BLANK)
    @Size(max = 20, message = UserMessageError.PHONE_NUMBER_MAX_LENGTH_20_CHARACTERS)
    private String phoneNumber;

    @NotBlank(message = UserMessageError.FULL_NAME_NOT_BLANK)
    @Size(max = 255, message = UserMessageError.FULL_NAME_MAX_LENGTH_255_CHARACTERS)
    private String fullName;
}
