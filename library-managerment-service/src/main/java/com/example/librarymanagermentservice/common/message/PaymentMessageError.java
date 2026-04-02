package com.example.librarymanagermentservice.common.message;

/**
 * Information about payment message error.
 */
public final class PaymentMessageError {
    private PaymentMessageError() {}

    public static final String USER_ID_IS_REQUIRED = "USER_ID_IS_REQUIRED";
    public static final String SUBSCRIPTION_ID_IS_REQUIRED = "SUBSCRIPTION_ID_IS_REQUIRED";
    public static final String PAYMENT_TYPE_IS_REQUIRED = "PAYMENT_TYPE_IS_REQUIRED";
    public static final String PAYMENT_GATEWAY_IS_REQUIRED = "PAYMENT_GATEWAY_IS_REQUIRED";
    public static final String AMOUNT_IS_REQUIRED = "AMOUNT_IS_REQUIRED";
    public static final String AMOUNT_IS_POSITIVE = "AMOUNT_IS_POSITIVE";
    public static final String DESCRIPTION_MAX_LENGTH_500_CHARACTERS = "DESCRIPTION_MAX_LENGTH_500_CHARACTERS";
    public static final String PAYMENT_DETAILS_NOT_FOUND = "PAYMENT_DETAILS_NOT_FOUND";
    public static final String PAYMENT_DETAILS_NOTES_NOT_FOUND = "PAYMENT_DETAILS_NOTES_NOT_FOUND";
    public static final String PAYMENT_ID_NOT_FORMAT = "PAYMENT_ID_NOT_FORMAT";
    public static final String PAYMENT_NOT_FOUND = "PAYMENT_NOT_FOUND";
}
