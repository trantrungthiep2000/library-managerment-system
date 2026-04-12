package com.example.librarymanagermentservice.common;

/**
 * Information about constant.
 */
public final class Constant {
    private Constant() {}

    public static final Integer MIN_SIZE = 1;
    public static final Integer MAX_SIZE = 10;
    public static final Long MAX_AGE = 3600L;
    public static final Integer DEFAULT_PAGE = 0;
    public static final String DEFAULT_SOFT_BY = "createdAt";
    public static final String PATTERN_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$";
    public static final String FRONTEND_URL = "http://localhost:5000/";
    public static final String BACKEND_URL = "http://localhost:8080/";
    public static final String UTF_8 = "utf-8";
    public static final String BEARER = "Bearer";
    public static final String JWT = "JWT";
    public static final Integer DEFAULT_DISPLAY_ORDER = 0;
    public static final String CURRENCY = "INR";
    public static final int MAX_LENGTH_PLAN_NAME = 100;
    public static final String TXN = "TXN_";

}
