package com.example.librarymanagermentservice.repository;

import com.example.librarymanagermentservice.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Information about interface book repository.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Find book by isbn.
     * @param isbn String.
     * @return Book.
     */
    Optional<Book> findByIsbn(String isbn);

    /**
     * Check isbn exists.
     * @param isbn String.
     * @return Boolean.
     */
    Boolean existsByIsbn(String isbn);

    /**
     * Count total by active is true.
     * @return Long.
     */
    Long countByActiveIsTrue();

    /**
     * Search book with filter.
     * @param searchTerm String.
     * @param genreId Long.
     * @param availableOnly Boolean.
     * @param pageable Pageable.
     * @return Page<Book>.
     */
    @Query("select b from Book b where " +
            "(:searchTerm is null OR " +
            "lower(b.title) like lower(concat('%', :searchTerm, '%')) OR " +
            "lower(b.author) like lower(concat('%', :searchTerm, '%')) OR " +
            "lower(b.isbn) like lower(concat('%', :searchTerm, '%'))) AND " +
            "(:genreId is null OR b.genre.id = :genreId) AND " +
            "(:availableOnly = false OR b.availableCopies > 0) AND " +
            "b.active = true"
    )
    Page<Book> searchBookWithFilter(
            @Param("searchTerm") String searchTerm,
            @Param("genreId") Long genreId,
            @Param("availableOnly") Boolean availableOnly,
            Pageable pageable
            );

    /**
     * Count total available books.
     * @return Long.
     */
    @Query("select count(b) from Book b where b.availableCopies > 0 and b.active = true")
    Long countAvailableBooks();
}
