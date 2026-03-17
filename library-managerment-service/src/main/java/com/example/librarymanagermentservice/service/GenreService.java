package com.example.librarymanagermentservice.service;

import com.example.librarymanagermentservice.dto.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.dto.GenreDTO;
import com.example.librarymanagermentservice.dto.request.GenreRequestDTO;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Information about interface genre service.
 */
public interface GenreService {

    /**
     * Create a genre.
     * @param requestDTO GenreRequestDTO.
     * @return GenreDTO.
     */
    ApiSuccessResponseDTO<GenreDTO> createGenre(GenreRequestDTO requestDTO);

    /**
     * Get all genres.
     * @return List<GenreDTO>.
     */
    ApiSuccessResponseDTO<List<GenreDTO>> getAllGenres();

    /**
     * Get genre by id.
     * @param genreId Long.
     * @return GenreDTO.
     */
    ApiSuccessResponseDTO<GenreDTO> getGenreById(Long genreId);

    /**
     * Update a genre.
     * @param genreId Long.
     * @param requestDTO GenreRequestDTO.
     * @return GenreDTO.
     */
    ApiSuccessResponseDTO<GenreDTO> updateGenre(Long genreId, GenreRequestDTO requestDTO);

    /**
     * Delete soft a genre.
     * @param genreId Long.
     * @return GenreDTO.
     */
    ApiSuccessResponseDTO<GenreDTO> deleteGenre(Long genreId);

    /**
     * Delete hard a genre.
     * @param genreId Long.
     * @return Long.
     */
    ApiSuccessResponseDTO<GenreDTO> hardDeleteGenre(Long genreId);

    /**
     * Get all active genres with subgenres.
     * @return List<GenreDTO>.
     */
    ApiSuccessResponseDTO<List<GenreDTO>> getAllActiveGenresWithSubGenres();

    /**
     * Get top level genres.
     * @return List<GenreDTO>
     */
    ApiSuccessResponseDTO<List<GenreDTO>> getTopLevelGenres();

    /**
     * Search genres.
     * @param searchTerm String.
     * @param pageable Pageable.
     * @return Page<GenreDTO>.
     */
    ApiSuccessResponseDTO<Page<GenreDTO>> searchGenres(String searchTerm, Pageable pageable);

    /**
     * Get total active genres.
     * @return Long.
     */
    ApiSuccessResponseDTO<Long> getTotalActiveGenres();

    /**
     * Get book count by genre.
     * @param genreId Long
     * @return Long
     */
    ApiSuccessResponseDTO<Long> getBookCountByGenre(Long genreId);
}
