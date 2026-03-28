package com.example.librarymanagermentservice.controller;

import com.example.librarymanagermentservice.common.ApiRoutesConstant;
import com.example.librarymanagermentservice.dto.BookDTO;
import com.example.librarymanagermentservice.dto.request.BookRequestDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Information about admin book controller.
 */
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutesConstant.AdminBook.ADMIN_BOOK)
public class AdminBookController {
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
    @PostMapping(ApiRoutesConstant.AdminBook.BULK)
    public ResponseEntity<ApiSuccessResponseDTO<List<BookDTO>>> createBooksBulk(@Valid @RequestBody List<BookRequestDTO> requestDTOS) {
        var result = bookService.createBooksBulk(requestDTOS);
        return ResponseEntity.ok(result);
    }
}
