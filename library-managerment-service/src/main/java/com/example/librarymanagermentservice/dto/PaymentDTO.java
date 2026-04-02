package com.example.librarymanagermentservice.dto;

import com.example.librarymanagermentservice.common.enums.PaymentGatewayEnum;
import com.example.librarymanagermentservice.common.enums.PaymentStatusEnum;
import com.example.librarymanagermentservice.common.enums.PaymentTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Information about payment DTO.
 */
@Data
@Builder
public class PaymentDTO {
    private Long id;
    private Long userid;
    private String userName;
    private String userEmail;
    private Long bookLoanId; // TODO: not table
    private Long subscriptionId;
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
}
