package com.example.librarymanagermentservice.dto.request;

import com.example.librarymanagermentservice.common.Constant;
import com.example.librarymanagermentservice.common.message.GenreMessageError;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Information about genre request DTO.
 */
@Getter
@Setter
public class GenreRequestDTO {

    @NotBlank(message = GenreMessageError.CODE_NOT_BLANK)
    private String code;

    @NotBlank(message = GenreMessageError.NAME_NOT_BLANK)
    private String name;

    @Size(max = 500, message = GenreMessageError.DESCRIPTION_MAX_500_CHARACTERS)
    private String description;

    @Min(value = 0, message = GenreMessageError.DISPLAY_ORDER_NOT_NEGATIVE)
    private Integer displayOrder = Constant.DEFAULT_DISPLAY_ORDER;

    private Long parentGenreId;
}
