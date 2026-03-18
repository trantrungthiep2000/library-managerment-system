package com.example.librarymanagermentservice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Information about book status response DTO.
 */
@NoArgsConstructor
@Data
public class BookStatusResponseDTO {
    private Long totalActiveBooks;
    private Long totalAvailableBooks;

    public BookStatusResponseDTO(Long totalActiveBooks, Long totalAvailableBooks) {
        this.totalActiveBooks = totalActiveBooks;
        this.totalAvailableBooks = totalAvailableBooks;
    }
}
