package com.example.librarymanagermentservice.common.message;

/**
 * Information about subscription message error.
 */
public final class SubscriptionMessageError {
    private SubscriptionMessageError() {}

    public static final String SUBSCRIPTION_NOT_FOUND = "SUBSCRIPTION_NOT_FOUND";
    public static final String SUBSCRIPTION_IS_INACTIVE = "SUBSCRIPTION_IS_INACTIVE";
    public static final String SUBSCRIPTION_IS_ACTIVE = "SUBSCRIPTION_IS_ACTIVE";
    public static final String PLAN_ID_IS_REQUIRED = "PLAN_ID_IS_REQUIRED";
    public static final String START_DATE_IS_REQUIRED = "START_DATE_IS_REQUIRED";
    public static final String END_DATE_IS_REQUIRED = "END_DATE_IS_REQUIRED";
    public static final String REASON_NOT_BLANK = "REASON_NOT_BLANK";
    public static final String SUBSCRIPTION_ID_IS_REQUIRED = "SUBSCRIPTION_ID_IS_REQUIRED";
    public static final String PAYMENT_ID_IS_REQUIRED = "PAYMENT_ID_IS_REQUIRED";
}
