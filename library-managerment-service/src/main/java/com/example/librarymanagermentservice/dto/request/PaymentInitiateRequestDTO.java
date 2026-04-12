package com.example.librarymanagermentservice.dto.request;

import com.example.librarymanagermentservice.common.enums.PaymentGatewayEnum;
import com.example.librarymanagermentservice.common.enums.PaymentTypeEnum;
import com.example.librarymanagermentservice.common.message.PaymentMessageError;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

/**
 * Information about payment initiate request DTO.
 */
@Data
@Builder
public class PaymentInitiateRequestDTO {
    @NotNull(message = PaymentMessageError.USER_ID_IS_REQUIRED)
    private Long userId;

    @NotNull(message = PaymentMessageError.SUBSCRIPTION_ID_IS_REQUIRED)
    private Long subscriptionId;

    @NotNull(message = PaymentMessageError.PAYMENT_TYPE_IS_REQUIRED)
    private PaymentTypeEnum paymentType;

    @NotNull(message = PaymentMessageError.PAYMENT_GATEWAY_IS_REQUIRED)
    private PaymentGatewayEnum paymentGateway;

    @NotNull(message = PaymentMessageError.AMOUNT_IS_REQUIRED)
    @Positive(message = PaymentMessageError.AMOUNT_IS_POSITIVE)
    private Long amount;

    private String gatewayPaymentId;

    private String gatewayOrderId;

    private String gatewaySignature;

    @Size(max= 500, message = PaymentMessageError.DESCRIPTION_MAX_LENGTH_500_CHARACTERS)
    private String description;

    private String failureReason;
}
