package com.example.librarymanagermentservice.common.message;

/**
 * Information of genre message error.
 */
public final class GenreMessageError {
    private GenreMessageError() {}

    public static final String CODE_NOT_BLANK = "CODE_NOT_BLANK";
    public static final String NAME_NOT_BLANK = "NAME_NOT_BLANK";
    public static final String DESCRIPTION_MAX_500_CHARACTERS = "DESCRIPTION_MAX_500_CHARACTERS";
    public static final String DISPLAY_ORDER_NOT_NEGATIVE = "DISPLAY_ORDER_NOT_NEGATIVE";
    public static final String ACTIVE_IS_REQUIRED = "ACTIVE_IS_REQUIRED";
    public static final String GENRE_NOT_FOUND = "GENRE_NOT_FOUND";
}
