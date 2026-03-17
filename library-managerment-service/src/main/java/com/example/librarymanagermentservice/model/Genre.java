package com.example.librarymanagermentservice.model;

import com.example.librarymanagermentservice.common.message.GenreMessageError;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Information about genre.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Genre extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = GenreMessageError.CODE_NOT_BLANK)
    private String code;

    @NotBlank(message = GenreMessageError.NAME_NOT_BLANK)
    private String name;

    @Size(max = 500, message = GenreMessageError.DESCRIPTION_MAX_500_CHARACTERS)
    private String description;

    @Min(value = 0, message = GenreMessageError.DISPLAY_ORDER_NOT_NEGATIVE)
    private Integer displayOrder = 0;

    @NotNull(message = GenreMessageError.ACTIVE_IS_REQUIRED)
    @Column(nullable = false)
    private Boolean active = true;

    @ManyToOne
    private Genre parentGenre;

    @OneToMany(mappedBy = "parentGenre")
    private List<Genre> subGenres = new ArrayList<Genre>();

//    @OneToMany(mappedBy = "genre", cascade = CascadeType.PERSIST)
//    private List<Book> books = new ArrayList<Book>();

}
