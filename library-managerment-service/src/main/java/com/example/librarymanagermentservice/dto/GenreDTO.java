package com.example.librarymanagermentservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Information about genre DTO.
 */
@Getter
@Setter
@Builder
public class GenreDTO {
    private Long id;
    private String code;
    private String name;
    private String description;
    private Integer displayOrder;
    private Boolean active;
    private Long parentGenreId;
    private String parentGenreName;
    private List<GenreDTO> subGenres;
    private Long bookCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
