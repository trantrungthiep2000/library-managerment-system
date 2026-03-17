package com.example.librarymanagermentservice.controller;

import com.example.librarymanagermentservice.common.ApiRoutesConstant;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.dto.GenreDTO;
import com.example.librarymanagermentservice.dto.request.GenreRequestDTO;
import com.example.librarymanagermentservice.service.GenreService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Information about genre controller.
 */
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutesConstant.Genre.GENRES)
public class GenreController {

    private final GenreService genreService;

    /**
     * Create a genre.
     * @param requestDTO GenreRequestDTO.
     * @return GenreDTO.
     */
    @PostMapping()
    public ResponseEntity<ApiSuccessResponseDTO<GenreDTO>> createGenre(@Valid @RequestBody GenreRequestDTO requestDTO) {
        var result = genreService.createGenre(requestDTO);
        return ResponseEntity.ok(result);
    }

    /**
     * Get all genres.
     * @return List<GenreDTO>.
     */
    @GetMapping()
    public ResponseEntity<ApiSuccessResponseDTO<List<GenreDTO>>> getAllGenres() {
        var result = genreService.getAllGenres();
        return ResponseEntity.ok(result);
    }

    /**
     * Get genre by id.
     * @param id Long.
     * @return GenreDTO.
     */
    @GetMapping(ApiRoutesConstant.Genre.ID)
    public ResponseEntity<ApiSuccessResponseDTO<GenreDTO>> getGenreById(@PathVariable Long id) {
        var result = genreService.getGenreById(id);
        return ResponseEntity.ok(result);
    }

    /**
     * Update a genre.
     * @param id Long.
     * @param requestDTO GenreRequestDTO.
     * @return GenreDTO.
     */
    @PutMapping(ApiRoutesConstant.Genre.ID)
    public ResponseEntity<ApiSuccessResponseDTO<GenreDTO>> updateGenre(@PathVariable Long id, @Valid @RequestBody GenreRequestDTO requestDTO) {
        var result = genreService.updateGenre(id, requestDTO);
        return ResponseEntity.ok(result);
    }

    /**
     * Delete a genre.
     * @param id Long.
     * @return GenreDTO.
     */
    @DeleteMapping(ApiRoutesConstant.Genre.ID)
    public ResponseEntity<ApiSuccessResponseDTO<GenreDTO>> deleteGenre(@PathVariable Long id) {
        var result = genreService.deleteGenre(id);
        return ResponseEntity.ok(result);
    }

    /**
     * Hard delete a genre.
     * @param id Long.
     * @return GenreDTO.
     */
    @DeleteMapping(ApiRoutesConstant.Genre.HARD_DELETE)
    public ResponseEntity<ApiSuccessResponseDTO<GenreDTO>> hardDeleteGenre(@PathVariable Long id) {
        var result = genreService.hardDeleteGenre(id);
        return ResponseEntity.ok(result);
    }

    /**
     * Get all active genre with subgenres.
     * @return List<GenreDTO>.
     */
    @GetMapping(ApiRoutesConstant.Genre.ALL_ACTIVE_SUBGENRES)
    public ResponseEntity<ApiSuccessResponseDTO<List<GenreDTO>>> getAllActiveGenresWithSubGenres() {
        var result = genreService.getAllActiveGenresWithSubGenres();
        return ResponseEntity.ok(result);
    }

    /**
     * Get top level genres.
     * @return List<GenreDTO>.
     */
    @GetMapping(ApiRoutesConstant.Genre.TOP_LEVEL_GENRES)
    public ResponseEntity<ApiSuccessResponseDTO<List<GenreDTO>>> getTopLevelGenres() {
        var result = genreService.getTopLevelGenres();
        return ResponseEntity.ok(result);
    }

    /**
     * Search term.
     * @param searchTerm String.
     * @param pageable Pageable.
     * @return Page<GenreDTO>.
     */
    @GetMapping(ApiRoutesConstant.Genre.SEARCH)
    public ResponseEntity<ApiSuccessResponseDTO<Page<GenreDTO>>> searchGenres(String searchTerm, Pageable pageable) {
        var result = genreService.searchGenres(searchTerm, pageable);
        return ResponseEntity.ok(result);
    }

    /**
     * Get total active genres.
     * @return Long.
     */
    @GetMapping(ApiRoutesConstant.Genre.TOTAL_ACTIVE_GENRES)
    public ResponseEntity<ApiSuccessResponseDTO<Long>> getTotalActiveGenres() {
        var result = genreService.getTotalActiveGenres();
        return ResponseEntity.ok(result);
    }

    /**
     * Get book count by genre.
     * @param id Long.
     * @return Long.
     */
    @GetMapping(ApiRoutesConstant.Genre.TOTAL_BOOK_GENRE)
    public ResponseEntity<ApiSuccessResponseDTO<Long>> getBookCountByGenre(@PathVariable Long id) {
        var result = genreService.getBookCountByGenre(id);
        return ResponseEntity.ok(result);
    }
}
