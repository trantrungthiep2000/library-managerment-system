package com.example.librarymanagermentservice.common;

/**
 * Information about api routes constant.
 */
public final class ApiRoutesConstant {

    private ApiRoutesConstant() {}

    public static final String API = "/api";
    public static final String V1 = "/v1";

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
}
