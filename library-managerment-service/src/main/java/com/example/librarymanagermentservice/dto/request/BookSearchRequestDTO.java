package com.example.librarymanagermentservice.dto.request;

import com.example.librarymanagermentservice.common.enums.SortDirectionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Information about book search request DTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSearchRequestDTO {

    private String searchTerm;
    private Long genreId;
    private Boolean availableOnly;
    private Integer page = 0;
    private Integer size = 10;
    private String sortBy = "createdAt";
    private String sortDirection = SortDirectionEnum.DESC.toString();
}
