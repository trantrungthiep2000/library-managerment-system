package com.example.librarymanagermentservice.mapper;

import com.example.librarymanagermentservice.dto.BookDTO;
import com.example.librarymanagermentservice.dto.request.BookRequestDTO;
import com.example.librarymanagermentservice.model.Book;
import com.example.librarymanagermentservice.model.Genre;
import com.example.librarymanagermentservice.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Information about book mapper.
 */
@Component
@AllArgsConstructor
public class BookMapper {

    private final GenreRepository genreRepository;

    /**
     * Mapper to book DTO.
     * @param book Book.
     * @return BookDTO.
     */
    public BookDTO toDTO(Book book) {
        if (book == null) return null;

        BookDTO bookDTO = BookDTO.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .publishedDate(book.getPublishedDate())
                .language(book.getLanguage())
                .pages(book.getPages())
                .description(book.getDescription())
                .totalCopies(book.getTotalCopies())
                .availableCopies(book.getAvailableCopies())
                .price(book.getPrice())
                .coverImageUrl(book.getCoverImageUrl())
                .active(book.getActive())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();

        if (book.getGenre() != null) {
            bookDTO.setGenreId(book.getGenre().getId());
            bookDTO.setGenreName(book.getGenre().getName());
            bookDTO.setGenreCode(book.getGenre().getCode());
        }

        return bookDTO;
    }

    /**
     * Mapper to book.
     * @param requestDTO BookRequestDTO.
     * @return Book.
     */
    public Book toEntity(BookRequestDTO requestDTO) {
        if (requestDTO == null) return null;

        Book book = Book.builder()
                .isbn(requestDTO.getIsbn())
                .title(requestDTO.getTitle())
                .author(requestDTO.getAuthor())
                .publisher(requestDTO.getPublisher())
                .publishedDate(requestDTO.getPublishedDate())
                .language(requestDTO.getLanguage())
                .pages(requestDTO.getPages())
                .description(requestDTO.getDescription())
                .totalCopies(requestDTO.getTotalCopies())
                .availableCopies(requestDTO.getAvailableCopies())
                .price(requestDTO.getPrice())
                .coverImageUrl(requestDTO.getCoverImageUrl())
                .build();

        if (requestDTO.getGenreId() != null) {
            Genre genre = genreRepository.findById(requestDTO.getGenreId()).orElse(null);
            book.setGenre(genre);
        }

        return book;
    }

    /**
     * Update entity to DTO.
     * @param requestDTO BookRequestDTO.
     * @param book Book.
     */
    public void updateEntityToDTO(BookRequestDTO requestDTO, Book book) {
        book.setIsbn(requestDTO.getIsbn());
        book.setTitle(requestDTO.getTitle());
        book.setAuthor(requestDTO.getAuthor());
        book.setPublisher(requestDTO.getPublisher());
        book.setPublishedDate(requestDTO.getPublishedDate());
        book.setLanguage(requestDTO.getLanguage());
        book.setPages(requestDTO.getPages());
        book.setDescription(requestDTO.getDescription());
        book.setTotalCopies(requestDTO.getTotalCopies());
        book.setAvailableCopies(requestDTO.getAvailableCopies());
        book.setPrice(requestDTO.getPrice());
        book.setCoverImageUrl(requestDTO.getCoverImageUrl());

        if (requestDTO.getGenreId() != null) {
            Genre genre = genreRepository.findById(requestDTO.getGenreId()).orElse(null);
            book.setGenre(genre);
        }
    }
}
