package com.example.librarymanagermentservice.dto.response;

import com.example.librarymanagermentservice.common.enums.PaymentGatewayEnum;
import com.example.librarymanagermentservice.common.enums.PaymentStatusEnum;
import com.example.librarymanagermentservice.common.enums.PaymentTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Information about payment initiate response DTO.
 */
@Data
@Builder
public class PaymentInitiateResponseDTO {
    private Long id;
    private PaymentTypeEnum paymentType;
    private PaymentStatusEnum paymentStatus;
    private PaymentGatewayEnum paymentGateway;
    private Long amount;
    private String transactionId;
    private String gatewayPaymentId;
    private String gatewayOrderId;
    private String gatewaySignature;
    private String description;
    private String failureReason;
    private LocalDateTime initiatedAt;
    private LocalDateTime completedAt;
    private String checkoutURL;
    private Boolean success;
}
