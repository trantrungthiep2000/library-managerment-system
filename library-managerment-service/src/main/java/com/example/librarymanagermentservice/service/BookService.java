package com.example.librarymanagermentservice.service;

import com.example.librarymanagermentservice.dto.BookDTO;
import com.example.librarymanagermentservice.dto.request.BookRequestDTO;
import com.example.librarymanagermentservice.dto.request.BookSearchRequestDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.dto.response.PageResponseDTO;

import java.util.List;

/**
 * Information about book service.
 */
public interface BookService {
    /**
     * Create a book.
     * @param requestDTO BookRequestDTO.
     * @return BookDTO.
     */
    ApiSuccessResponseDTO<BookDTO> createBook(BookRequestDTO requestDTO);

    /**
     * Create books bulk.
     * @param requestDTOS List<BookRequestDTO>.
     * @return List<BookDTO>
     */
    ApiSuccessResponseDTO<List<BookDTO>> createBooksBulk(List<BookRequestDTO> requestDTOS);

    /**
     * Get book by id.
     * @param bookId Long.
     * @return BookDTO.
     */
    ApiSuccessResponseDTO<BookDTO> getBookById(Long bookId);

    /**
     * Get book by ISBN.
     * @param ISBN String.
     * @return BookDTO.
     */
    ApiSuccessResponseDTO<BookDTO> getBookByISBN(String ISBN);

    /**
     * Update a book.
     * @param bookId Long.
     * @param requestDTO BookRequestDTO.
     * @return BookDTO.
     */
    ApiSuccessResponseDTO<BookDTO> updateBook(Long bookId, BookRequestDTO requestDTO);

    /**
     * Delete a book.
     * @param bookId Long.
     * @return BookDTO.
     */
    ApiSuccessResponseDTO<BookDTO> deleteBook(Long bookId);

    /**
     * Hard delete a book.
     * @param bookId Long.
     * @return BookDTO.
     */
    ApiSuccessResponseDTO<BookDTO> hardDeleteBook(Long bookId);

    /**
     * Search book with filter.
     * @param bookSearchRequestDTO BookSearchRequestDTO.
     * @return PageResponseDTO<BookDTO>.
     */
    ApiSuccessResponseDTO<PageResponseDTO<BookDTO>> searchBookWithFilters(BookSearchRequestDTO bookSearchRequestDTO);

    /**
     * Get total active books.
     * @return Long.
     */
    ApiSuccessResponseDTO<Long> getTotalActiveBooks();

    /**
     * Get total available books.
     * @return Long.
     */
    ApiSuccessResponseDTO<Long> getTotalAvailableBooks();
}
