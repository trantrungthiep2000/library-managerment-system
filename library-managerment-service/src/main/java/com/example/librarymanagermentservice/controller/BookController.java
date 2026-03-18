package com.example.librarymanagermentservice.controller;

import com.example.librarymanagermentservice.common.ApiRoutesConstant;
import com.example.librarymanagermentservice.dto.BookDTO;
import com.example.librarymanagermentservice.dto.request.BookRequestDTO;
import com.example.librarymanagermentservice.dto.request.BookSearchRequestDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.dto.response.BookStatusResponseDTO;
import com.example.librarymanagermentservice.dto.response.PageResponseDTO;
import com.example.librarymanagermentservice.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Information about book controller.
 */
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutesConstant.Book.BOOKS)
public class BookController {
    private final BookService bookService;

    /**
     * Create a book.
     * @param requestDTO BookRequestDTO.
     * @return BookDTO.
     */
    @PostMapping()
    public ResponseEntity<ApiSuccessResponseDTO<BookDTO>> createBook(@Valid @RequestBody BookRequestDTO requestDTO) {
        var result = bookService.createBook(requestDTO);
        return ResponseEntity.ok(result);
    }

    /**
     * Create many books bulk.
     * @param requestDTOS List<BookRequestDTO>.
     * @return List<BookDTO>.
     */
    @PostMapping(ApiRoutesConstant.Book.BULK)
    public ResponseEntity<ApiSuccessResponseDTO<List<BookDTO>>> createBooksBulk(@Valid @RequestBody List<BookRequestDTO> requestDTOS) {
        var result = bookService.createBooksBulk(requestDTOS);
        return ResponseEntity.ok(result);
    }

    /**
     * Get book by id.
     * @param id Long.
     * @return BookDTO
     */
    @GetMapping(ApiRoutesConstant.Book.ID)
    public ResponseEntity<ApiSuccessResponseDTO<BookDTO>> getBookById(@PathVariable Long id) {
        var result = bookService.getBookById(id);
        return ResponseEntity.ok(result);
    }

    /**
     * Get book by ISBN.
     * @param ISBN ISBN.
     * @return BookDTO.
     */
    @GetMapping(ApiRoutesConstant.Book.ISBN)
    public ResponseEntity<ApiSuccessResponseDTO<BookDTO>> getBookByISBN(@PathVariable String ISBN) {
        var result = bookService.getBookByISBN(ISBN);
        return ResponseEntity.ok(result);
    }

    /**
     * Update a book.
     * @param id Long.
     * @param requestDTO BookRequestDTO.
     * @return BookDTO.
     */
    @PutMapping(ApiRoutesConstant.Book.ID)
    public ResponseEntity<ApiSuccessResponseDTO<BookDTO>> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequestDTO requestDTO) {
        var result = bookService.updateBook(id, requestDTO);
        return ResponseEntity.ok(result);
    }

    /**
     * Delete a book.
     * @param id Long.
     * @return BookDTO.
     */
    @DeleteMapping(ApiRoutesConstant.Book.ID)
    public ResponseEntity<ApiSuccessResponseDTO<BookDTO>> deleteBook(@PathVariable Long id) {
        var result = bookService.deleteBook(id);
        return ResponseEntity.ok(result);
    }

    /**
     * Hard delete a book.
     * @param id Long.
     * @return BookDTO.
     */
    @DeleteMapping(ApiRoutesConstant.Book.HARD_DELETE)
    public ResponseEntity<ApiSuccessResponseDTO<BookDTO>> hardDeleteBook(@PathVariable Long id) {
        var result = bookService.hardDeleteBook(id);
        return ResponseEntity.ok(result);
    }

    /**
     * Search book with filters.
     * @param bookSearchRequestDTO BookSearchRequestDTO.
     * @return PageResponseDTO<BookDTO>.
     */
    @GetMapping(ApiRoutesConstant.Book.SEARCH)
    public ResponseEntity<ApiSuccessResponseDTO<PageResponseDTO<BookDTO>>> searchBookWithFilters(@Valid @RequestBody BookSearchRequestDTO bookSearchRequestDTO) {
        var result = bookService.searchBookWithFilters(bookSearchRequestDTO);
        return ResponseEntity.ok(result);
    }

    /**
     * Get book status.
     * @return BookStatusResponseDTO.
     */
    @GetMapping(ApiRoutesConstant.Book.STATUS)
    public ResponseEntity<ApiSuccessResponseDTO<BookStatusResponseDTO>> getBookStatus() {
        var totalActiveBooks = bookService.getTotalActiveBooks().getData();
        var totalAvailableBooks = bookService.getTotalAvailableBooks().getData();
        var bookStatusResponseDTO = new BookStatusResponseDTO(totalActiveBooks, totalAvailableBooks);
        var result = new ApiSuccessResponseDTO<>(bookStatusResponseDTO);
        return ResponseEntity.ok(result);
    }
}