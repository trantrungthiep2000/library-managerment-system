package com.example.librarymanagermentservice.dto.request;

import com.example.librarymanagermentservice.common.Constant;
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
    private Boolean availableOnly = false;
    private Integer page = Constant.DEFAULT_PAGE;
    private Integer size = Constant.MAX_SIZE;
    private String sortBy = Constant.DEFAULT_SOFT_BY;
    private String sortDirection = SortDirectionEnum.DESC.toString();
}
