package com.example.librarymanagermentservice.common.message;

/**
 * Information about user message error.
 */
public final class UserMessageError {
    private UserMessageError() {}

    public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    public static final String EMAIL_USED = "EMAIL_USED";
    public static final String EMAIL_NOT_BLANK = "EMAIL_NOT_BLANK";
    public static final String EMAIL_NOT_FORMAT = "EMAIL_NOT_FORMAT";
    public static final String EMAIL_MAX_LENGTH_255_CHARACTERS = "EMAIL_MAX_LENGTH_255_CHARACTERS";
    public static final String PASSWORD_NOT_BLANK = "PASSWORD_NOT_BLANK";
    public static final String PASSWORD_NOT_FORMAT = "PASSWORD_NOT_FORMAT";
    public static final String PASSWORD_LENGTH_BETWEEN_8_32_CHARACTERS = "PASSWORD_LENGTH_BETWEEN_8_32_CHARACTERS";
    public static final String PHONE_NUMBER_NOT_BLANK = "PHONE_NUMBER_NOT_BLANK";
    public static final String PHONE_NUMBER_MAX_LENGTH_20_CHARACTERS = "PHONE_NUMBER_MAX_LENGTH_20_CHARACTERS";
    public static final String FULL_NAME_NOT_BLANK = "FULL_NAME_NOT_BLANK";
    public static final String FULL_NAME_MAX_LENGTH_255_CHARACTERS = "FULL_NAME_MAX_LENGTH_255_CHARACTERS";
    public static final String ACCOUNT_INVALID_CREDENTIALS = "ACCOUNT_INVALID_CREDENTIALS";
    public static final String TOKEN_NOT_BLANK = "TOKEN_NOT_BLANK";
    public static final String NEW_PASSWORD_NOT_BLANK = "NEW_PASSWORD_NOT_BLANK";
    public static final String NEW_PASSWORD_NOT_FORMAT = "NEW_PASSWORD_NOT_FORMAT";
    public static final String NEW_PASSWORD_LENGTH_BETWEEN_8_32_CHARACTERS = "NEW_PASSWORD_LENGTH_BETWEEN_8_32_CHARACTERS";
}
