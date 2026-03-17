package com.example.librarymanagermentservice.service;

import com.example.librarymanagermentservice.common.message.GenreMessageError;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.dto.GenreDTO;
import com.example.librarymanagermentservice.dto.request.GenreRequestDTO;
import com.example.librarymanagermentservice.exception.NotFoundException;
import com.example.librarymanagermentservice.mapper.GenreMapper;
import com.example.librarymanagermentservice.model.Genre;
import com.example.librarymanagermentservice.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Information about genre service implement.
 */
@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    /**
     * Create a genre.
     *
     * @param requestDTO GenreRequestDTO.
     * @return GenreDTO.
     */
    @Override
    public ApiSuccessResponseDTO<GenreDTO> createGenre(GenreRequestDTO requestDTO) {

        Genre genre = genreMapper.toEntity(requestDTO);
        Genre genreCreate = genreRepository.save(genre);
        GenreDTO genreDTO = genreMapper.toDTO(genreCreate);
        return new ApiSuccessResponseDTO<>(genreDTO);
    }

    /**
     * Get all genres.
     * @return List<GenreDTO>.
     */
    @Override
    public ApiSuccessResponseDTO<List<GenreDTO>> getAllGenres() {

        List<GenreDTO> genreDTOS = genreRepository.findAll().stream()
                .map(genreMapper::toDTO)
                .collect(Collectors.toList());

        return new ApiSuccessResponseDTO<>(genreDTOS);
    }

    /**
     * Get genre by id.
     * @param genreId Long.
     * @return GenreDTO.
     */
    @Override
    public ApiSuccessResponseDTO<GenreDTO> getGenreById(Long genreId) {
        Genre genre = genreRepository.findById(genreId).orElse(null);
        if (genre == null) {
            throw new NotFoundException(GenreMessageError.GENRE_NOT_FOUND);
        }

        GenreDTO genreDTO = genreMapper.toDTO(genre);
        return new ApiSuccessResponseDTO<>(genreDTO);
    }

    /**
     * Update a genre.
     * @param genreId Long.
     * @param requestDTO GenreRequestDTO.
     * @return GenreDTO.
     */
    @Override
    public ApiSuccessResponseDTO<GenreDTO> updateGenre(Long genreId, GenreRequestDTO requestDTO) {
        Genre genre = genreRepository.findById(genreId).orElse(null);
        if (genre == null) {
            throw new NotFoundException(GenreMessageError.GENRE_NOT_FOUND);
        }

        genre = genreMapper.toEntity(requestDTO);
        genre = genreRepository.save(genre);
        GenreDTO genreDTO = genreMapper.toDTO(genre);
        return new ApiSuccessResponseDTO<>(genreDTO);
    }

    /**
     * Delete soft a genre.
     * @param genreId Long.
     * @return GenreDTO.
     */
    @Override
    public ApiSuccessResponseDTO<GenreDTO> deleteGenre(Long genreId) {
        Genre genre = genreRepository.findById(genreId).orElse(null);
        if (genre == null || !genre.getActive()) {
            throw new NotFoundException(GenreMessageError.GENRE_NOT_FOUND);
        }

        genre.setActive(false);
        genre = genreRepository.save(genre);
        GenreDTO genreDTO = genreMapper.toDTO(genre);
        return new ApiSuccessResponseDTO<>(genreDTO);
    }

    /**
     * Delete hard a genre.
     * @param genreId Long.
     * @return GenreDTO
     */
    @Override
    public ApiSuccessResponseDTO<GenreDTO> hardDeleteGenre(Long genreId) {
        Genre genre = genreRepository.findById(genreId).orElse(null);
        if (genre == null) {
            throw new NotFoundException(GenreMessageError.GENRE_NOT_FOUND);
        }

        genreRepository.delete(genre);
        GenreDTO genreDTO = genreMapper.toDTO(genre);
        return new ApiSuccessResponseDTO<>(genreDTO);
    }

    /**
     * Get all active genres with subgenres.
     * @return List<GenreDTO>.
     */
    @Override
    public ApiSuccessResponseDTO<List<GenreDTO>> getAllActiveGenresWithSubGenres() {
        List<Genre> genres = genreRepository
                .findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();

        List<GenreDTO> genreDTOS = genres.stream()
                .map(genreMapper::toDTO)
                .collect(Collectors.toList());

        return new ApiSuccessResponseDTO<>(genreDTOS);
    }

    /**
     * Get top level genres.
     * @return List<GenreDTO>.
     */
    @Override
    public ApiSuccessResponseDTO<List<GenreDTO>> getTopLevelGenres() {
        List<Genre> genres = genreRepository
                .findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();

        List<GenreDTO> genreDTOS = genres.stream()
                .map(genreMapper::toDTO)
                .collect(Collectors.toList());

        return new ApiSuccessResponseDTO<>(genreDTOS);
    }

    @Override
    public ApiSuccessResponseDTO<Page<GenreDTO>> searchGenres(String searchTerm, Pageable pageable) {
        return null;
    }

    /**
     * Get total active genres.
     * @return Long
     */
    @Override
    public ApiSuccessResponseDTO<Long> getTotalActiveGenres() {
        Long totalActive = genreRepository.countByActiveIsTrue();
        return new ApiSuccessResponseDTO<>(totalActive);
    }

    @Override
    public ApiSuccessResponseDTO<Long> getBookCountByGenre(Long genreId) {
        return null;
    }
}
