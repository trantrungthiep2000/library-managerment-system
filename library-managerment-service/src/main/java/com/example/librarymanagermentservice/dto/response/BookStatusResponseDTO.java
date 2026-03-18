package com.example.librarymanagermentservice.dto.response;

/**
 * Information about book status response DTO.
 */
public class BookStatusResponseDTO {
    private Long totalActiveBooks;
    private Long totalAvailableBooks;

    public BookStatusResponseDTO(Long totalActiveBooks, Long totalAvailableBooks) {
        this.totalActiveBooks = totalActiveBooks;
        this.totalAvailableBooks = totalAvailableBooks;
    }

}
