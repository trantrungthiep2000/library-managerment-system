package com.example.librarymanagermentservice.dto.request;

import com.example.librarymanagermentservice.common.message.BookMessageError;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Information about book request DTO.
 */
@Getter
@Setter
public class BookRequestDTO {
    @NotBlank(message = BookMessageError.ISBN_NOT_BLANK)
    private String isbn;

    @NotBlank(message = BookMessageError.TITLE_NOT_BLANK)
    @Size(min = 1, max = 255, message = BookMessageError.TITLE_LENGTH_BETWEEN_1_255_CHARACTERS)
    private String title;

    @NotBlank(message = BookMessageError.AUTHOR_NOT_BLANK)
    @Size(min = 1, max = 255, message = BookMessageError.AUTHOR_LENGTH_BETWEEN_1_255_CHARACTERS)
    private String author;

    @NotNull(message = BookMessageError.GENRE_ID_IS_REQUIRED)
    private Long genreId;

    @Size(max = 100, message = BookMessageError.PUBLISHER_LENGTH_MAX_100_CHARACTERS)
    private String publisher;

    private LocalDateTime publishedDate;

    @Size(max = 20, message = BookMessageError.LANGUAGE_LENGTH_MAX_20_CHARACTERS)
    private String language;

    @Min(value = 1, message = BookMessageError.PAGES_LENGTH_MIN_1_VALUE)
    @Max(value = 50000, message = BookMessageError.PAGES_LENGTH_MAX_50000_VALUE)
    private Integer pages;

    @Size(max = 2000, message = BookMessageError.DESCRIPTION_LENGTH_MAX_2000_CHARACTERS)
    private String description;

    @Min(value = 0, message = BookMessageError.TOTAL_COPIES_LENGTH_MIN_0_VALUE)
    @NotNull(message = BookMessageError.TOTAL_COPIES_IS_REQUIRED)
    private Integer totalCopies;

    @Min(value = 0, message = BookMessageError.AVAILABLE_COPIES_LENGTH_MIN_0_VALUE)
    @NotNull(message = BookMessageError.AVAILABLE_COPIES_IS_REQUIRED)
    private Integer availableCopies;

    @DecimalMin(value = "0.0", message = BookMessageError.PRICE_NOT_NEGATIVE)
    @Digits(integer = 8, fraction = 2, message = BookMessageError.PRICE_LENGTH_MAX_8_DIGITS)
    private BigDecimal price;

    @Size(max = 500, message = BookMessageError.COVER_IMAGE_URL_LENGTH_MAX_500_CHARACTERS)
    private String coverImageUrl;

    private Boolean alreadyHaveLoan;

    private Boolean alreadyHaveReservation;
}
