package com.example.librarymanagermentservice.common;

/**
 * Information about jwt constant.
 */
public final class JwtConstant {
    private JwtConstant() {}

    public static final Integer BEGIN_INDEX = 7;
    public static final Long EXPIRATION_TIME = 1440 * 60 * 1000L;
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORITIES = "authorities";
    public static final String EMAIL = "email";
    public static final String SECRET_KEY = "zP7vR9yB&E)H@McQfTjWnZr4u7x!A%C*F-JaNdRgUkXp2s5v8y/B?E(G+KbPeShV";
}
