package com.example.librarymanagermentservice.mapper;

import com.example.librarymanagermentservice.dto.GenreDTO;
import com.example.librarymanagermentservice.dto.request.GenreRequestDTO;
import com.example.librarymanagermentservice.model.Genre;
import com.example.librarymanagermentservice.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Information about genre mapper.
 */
@Component
@AllArgsConstructor
public class GenreMapper {

    private final GenreRepository genreRepository;

    /**
     * Mapper to DTO.
     * @param genre Genre.
     * @return GenreDTO.
     */
    public GenreDTO toDTO(Genre genre) {
        if (genre == null) return null;

        GenreDTO genreDTO = GenreDTO.builder()
                .id(genre.getId())
                .code(genre.getCode())
                .name(genre.getName())
                .description(genre.getDescription())
                .displayOrder(genre.getDisplayOrder())
                .active(genre.getActive())
                .createdAt(genre.getCreatedAt())
                .updatedAt(genre.getUpdatedAt())
                .build();

        if (genre.getParentGenre() != null) {
            genreDTO.setParentGenreId(genre.getParentGenre().getId());
            genreDTO.setParentGenreName(genre.getParentGenre().getName());
        }

        if (genreDTO.getSubGenres() != null && !genreDTO.getSubGenres().isEmpty()){
            genreDTO.setSubGenres(genre.getSubGenres().stream()
                    .filter(Genre::getActive)
                    .map(this::toDTO)
                    .collect(Collectors.toList()));
        }

        return genreDTO;
    }

    /**
     * Mapper to entity.
     * @param requestDTO GenreRequestDTO.
     * @return Genre.
     */
    public Genre toEntity(GenreRequestDTO requestDTO) {
        if (requestDTO == null) return null;

        Genre genre = Genre.builder()
                .code(requestDTO.getCode())
                .name(requestDTO.getName())
                .description(requestDTO.getDescription())
                .displayOrder(requestDTO.getDisplayOrder())
                .build();

        if (requestDTO.getParentGenreId() != null)
        {
            Genre parentGenre = genreRepository.findById(requestDTO.getParentGenreId()).orElse(null);
            genre.setParentGenre(parentGenre);
        }

        return genre;
    }

    /**
     * Update entity to DTO.
     * @param requestDTO GenreRequestDTO.
     * @param genre Genre.
     */
    public void updateEntityToDTO(GenreRequestDTO requestDTO, Genre genre) {
        genre.setCode(requestDTO.getCode());
        genre.setName(requestDTO.getName());
        genre.setDescription(requestDTO.getDescription());
        genre.setDisplayOrder(requestDTO.getDisplayOrder());

        if (requestDTO.getParentGenreId() != null)
        {
            Genre parentGenre = genreRepository.findById(requestDTO.getParentGenreId()).orElse(null);
            genre.setParentGenre(parentGenre);
        }
    }
}
