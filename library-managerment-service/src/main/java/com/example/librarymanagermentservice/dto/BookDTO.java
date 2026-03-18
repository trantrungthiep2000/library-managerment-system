package com.example.librarymanagermentservice.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Information about book DTO.
 */
@Getter
@Setter
@Builder
public class BookDTO {
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private Long genreId;
    private String genreName;
    private String genreCode;
    private String publisher;
    private LocalDateTime publishedDate;
    private String language;
    private Integer pages;
    private String description;
    private Integer totalCopies;
    private Integer availableCopies;
    private BigDecimal price;
    private String coverImageUrl;
    private Boolean alreadyHaveLoan;
    private Boolean alreadyHaveReservation;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
