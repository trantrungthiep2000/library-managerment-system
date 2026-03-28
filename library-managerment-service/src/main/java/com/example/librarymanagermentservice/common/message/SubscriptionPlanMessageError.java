package com.example.librarymanagermentservice.common.message;

/**
 * Information about subscription plan message error.
 */
public final class SubscriptionPlanMessageError {
    private SubscriptionPlanMessageError() {}

    public static final String SUBSCRIPTION_PLAN_NOT_FOUND = "SUBSCRIPTION_PLAN_NOT_FOUND";
    public static final String PLAN_CODE_USED = "PLAN_CODE_USED";
    public static final String PLAN_CODE_NOT_BLANK = "PLAN_CODE_NOT_BLANK";
    public static final String PLAN_NAME_NOT_BLANK = "PLAN_NAME_NOT_BLANK";
    public static final String PLAN_NAME_MAX_LENGTH_100_CHARACTERS = "PLAN_NAME_MAX_LENGTH_100_CHARACTERS";
    public static final String DURATION_DAYS_NOT_BLANK = "DURATION_DAYS_NOT_BLANK";
    public static final String DURATION_DAYS_MUST_POSITIVE = "DURATION_DAYS_MUST_POSITIVE";
    public static final String PRICE_NOT_BLANK = "PRICE_NOT_BLANK";
    public static final String PRICE_MUST_POSITIVE = "PRICE_MUST_POSITIVE";
    public static final String MAX_BOOKS_ALLOWED_NOT_BLANK = "MAX_BOOKS_ALLOWED_NOT_BLANK";
    public static final String MAX_BOOKS_ALLOWED_MUST_POSITIVE = "MAX_BOOKS_ALLOWED_MUST_POSITIVE";
    public static final String MAX_DAYS_PER_BOOK_NOT_BLANK = "MAX_DAYS_PER_BOOK_NOT_BLANK";
    public static final String MAX_DAYS_PER_BOOK_MUST_POSITIVE = "MAX_DAYS_PER_BOOK_MUST_POSITIVE";
}
