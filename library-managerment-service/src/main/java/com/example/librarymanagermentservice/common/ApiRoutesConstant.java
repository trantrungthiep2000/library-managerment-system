package com.example.librarymanagermentservice.common;

/**
 * Information about api routes constant.
 */
public final class ApiRoutesConstant {
    private ApiRoutesConstant() {}

    public static final String API = "/api";
    public static final String V1 = "/v1";

    /**
     * Genre routes api
     */
    public static final class Genre {
        private Genre() {}

        public static final String GENRES = API + V1 + "/genres";
        public static final String ID = "{id}";
        public static final String HARD_DELETE = "{id}/hard";
        public static final String ALL_ACTIVE_SUBGENRES = "actives";
        public static final String TOP_LEVEL_GENRES = "top-level";
        public static final String SEARCH = "search";
        public static final String TOTAL_ACTIVE_GENRES = "total-active";
        public static final String TOTAL_BOOK_GENRE = "{id}/total-book";
    }

    /**
     * Book routes api
     */
    public static final class Book {
        private Book() {}

        public static final String BOOKS = API + V1 + "/books";
        public static final String ID = "{id}";
        public static final String ISBN = "{ISBN}/book";
        public static final String HARD_DELETE = "{id}/hard";
        public static final String SEARCH = "search";
        public static final String STATE = "state";
    }

    /**
     * Auth routes api
     */
    public static final class Auth {
        private Auth() {}

        public static final String Auth = API + V1 + "/auth";
        public static final String LOGIN = "login";
        public static final String SIGNUP = "signup";
        public static final String FORGOT_PASSWORD = "forgot-password";
        public static final String RESET_PASSWORD = "reset-password";
    }

    /**
     * Admin book routes api
     */
    public static final class AdminBook {
        private AdminBook() {}

        public static final String ADMIN_BOOK = API + V1 + "/admin/books";
        public static final String BULK = "bulk";
    }
}
