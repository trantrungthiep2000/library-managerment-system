package com.example.librarymanagermentservice.model;

import com.example.librarymanagermentservice.common.message.BookMessageError;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Information about book.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Genre genre;

    private String publisher;

    private LocalDateTime publishedDate;

    private String language;

    private Integer pages;

    private String description;

    @Column(nullable = false)
    private Integer totalCopies;

    @Column(nullable = false)
    private Integer availableCopies;

    private BigDecimal price;

    private String coverImageUrl;

    /**
     * Is check available copies valid.
     * @return Boolean.
     */
    @AssertTrue(message = BookMessageError.AVAILABLE_COPY_NOT_EXCEED_TOTAL_COPY)
    public Boolean isAvailableCopiesValid() {
        if (totalCopies == null || availableCopies == null) {
            return true;
        }

        return availableCopies <= totalCopies;
    }
}
