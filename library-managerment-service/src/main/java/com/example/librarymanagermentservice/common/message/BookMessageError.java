package com.example.librarymanagermentservice.common.message;

/**
 * Information book message error.
 */
public final class BookMessageError {
    private BookMessageError() {}

    public static final String AVAILABLE_COPY_NOT_EXCEED_TOTAL_COPY = "AVAILABLE_COPY_NOT_EXCEED_TOTAL_COPY";
    public static final String BOOK_NOT_FOUND = "BOOK_NOT_FOUND";
    public static final String ISBN_USED = "ISBN_USED";
    public static final String ISBN_NOT_BLANK = "ISBN_NOT_BLANK";
    public static final String TITLE_NOT_BLANK = "TITLE_NOT_BLANK";
    public static final String TITLE_LENGTH_BETWEEN_1_255_CHARACTERS = "TITLE_LENGTH_BETWEEN_1_255_CHARACTERS";
    public static final String AUTHOR_NOT_BLANK = "AUTHOR_NOT_BLANK";
    public static final String AUTHOR_LENGTH_BETWEEN_1_255_CHARACTERS = "AUTHOR_LENGTH_BETWEEN_1_255_CHARACTERS";
    public static final String GENRE_ID_IS_REQUIRED = "GENRE_ID_IS_REQUIRED";
    public static final String PUBLISHER_LENGTH_MAX_100_CHARACTERS = "PUBLISHER_LENGTH_MAX_100_CHARACTERS";
    public static final String LANGUAGE_LENGTH_MAX_20_CHARACTERS = "LANGUAGE_LENGTH_MAX_20_CHARACTERS";
    public static final String PAGES_LENGTH_MIN_1_VALUE = "PAGES_LENGTH_MIN_1_VALUE";
    public static final String PAGES_LENGTH_MAX_50000_VALUE = "PAGES_LENGTH_MAX_50000_VALUE";
    public static final String DESCRIPTION_LENGTH_MAX_2000_CHARACTERS = "DESCRIPTION_LENGTH_MAX_2000_CHARACTERS";
    public static final String TOTAL_COPIES_IS_REQUIRED = "TOTAL_COPIES_IS_REQUIRED";
    public static final String TOTAL_COPIES_LENGTH_MIN_0_VALUE = "TOTAL_COPIES_LENGTH_MIN_0_VALUE";
    public static final String AVAILABLE_COPIES_IS_REQUIRED = "AVAILABLE_COPIES_IS_REQUIRED";
    public static final String AVAILABLE_COPIES_LENGTH_MIN_0_VALUE = "AVAILABLE_COPIES_LENGTH_MIN_0_VALUE";
    public static final String PRICE_NOT_NEGATIVE = "PRICE_NOT_NEGATIVE";
    public static final String PRICE_LENGTH_MAX_8_DIGITS = "PRICE_LENGTH_MAX_8_DIGITS";
    public static final String COVER_IMAGE_URL_LENGTH_MAX_500_CHARACTERS = "COVER_IMAGE_URL_LENGTH_MAX_500_CHARACTERS";
}
