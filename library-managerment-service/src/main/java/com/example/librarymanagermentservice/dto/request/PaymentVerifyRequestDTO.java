package com.example.librarymanagermentservice.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * Information about payment request DTO.
 */
@Data
@Builder
public class PaymentVerifyRequestDTO {
    // razor pay
    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignature;

    // stripe
    private String stripePaymentIntentId;
    private String stripePaymentIntentStatus;
}
