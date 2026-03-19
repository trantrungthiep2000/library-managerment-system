package com.example.librarymanagermentservice.common.message;

/**
 * Information about password reset token error.
 */
public final class PasswordResetTokenError {
    private PasswordResetTokenError() {}

    public static final String TOKEN_INVALID = "TOKEN_INVALID";
    public static final String TOKEN_EXPIRED = "TOKEN_EXPIRED";
    public static final String NEW_PASSWORD_SAME_AS_OLD_PASSWORD = "NEW_PASSWORD_SAME_AS_OLD_PASSWORD";

}
