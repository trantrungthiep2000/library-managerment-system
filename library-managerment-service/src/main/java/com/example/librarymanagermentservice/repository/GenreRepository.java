package com.example.librarymanagermentservice.repository;

import com.example.librarymanagermentservice.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Information about interface genre repository.
 */
public interface GenreRepository extends JpaRepository<Genre, Long> {

    /**
     * Get total active genres.
     * @return Long.
     */
    Long countByActiveIsTrue();

    /**
     * Find by parent genre is null and active is true order by display order asc.
     * @return List<Genre>
     */
    List<Genre> findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();
}
