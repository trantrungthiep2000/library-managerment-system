package com.example.librarymanagermentservice.mapper;

import com.example.librarymanagermentservice.common.Constant;
import com.example.librarymanagermentservice.common.enums.PaymentStatusEnum;
import com.example.librarymanagermentservice.dto.PaymentDTO;
import com.example.librarymanagermentservice.dto.request.PaymentInitiateRequestDTO;
import com.example.librarymanagermentservice.dto.response.PaymentInitiateResponseDTO;
import com.example.librarymanagermentservice.model.Payment;
import com.example.librarymanagermentservice.model.Subscription;
import com.example.librarymanagermentservice.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Information about payment mapper.
 */
@Component
public class PaymentMapper {
    /**
     * Mapper to entity.
     * @param requestDTO PaymentInitiateRequestDTO.
     * @param user User.
     * @param subscription Subscription.
     * @return Payment.
     */
    public Payment toEntity(PaymentInitiateRequestDTO requestDTO, User user, Subscription subscription) {
        if (requestDTO == null) return null;

        return Payment.builder()
                .user(user)
                .subscription(subscription)
                .paymentType(requestDTO.getPaymentType())
                .paymentStatus(PaymentStatusEnum.PENDING)
                .paymentGateway(requestDTO.getPaymentGateway())
                .amount(requestDTO.getAmount())
                .transactionId(Constant.TXN + UUID.randomUUID())
                .description(requestDTO.getDescription())
                .gatewayPaymentId(requestDTO.getGatewayPaymentId())
                .gatewaySignature(requestDTO.getGatewaySignature())
                .initiatedAt(LocalDateTime.now())
                .failureReason(requestDTO.getFailureReason())
                .build();
    }

    /**
     * Mapper to DTO.
     * @param payment Payment.
     * @return PaymentDTO
     */
    public PaymentDTO toDTO(Payment payment) {
        if (payment == null) return null;

        PaymentDTO paymentDTO = PaymentDTO.builder()
                .id(payment.getId())
                .paymentType(payment.getPaymentType())
                .paymentStatus(payment.getPaymentStatus())
                .paymentGateway(payment.getPaymentGateway())
                .amount(payment.getAmount())
                .transactionId(payment.getTransactionId())
                .gatewayPaymentId(payment.getGatewayPaymentId())
                .gatewayOrderId(payment.getGatewayOrderId())
                .gatewaySignature(payment.getGatewaySignature())
                .description(payment.getDescription())
                .failureReason(payment.getFailureReason())
                .initiatedAt(payment.getInitiatedAt())
                .completedAt(payment.getCompletedAt())
                .build();

        if (payment.getUser() != null) {
            paymentDTO.setUserid(payment.getUser().getId());
            paymentDTO.setUserName(payment.getUser().getFullName());
            paymentDTO.setUserEmail(payment.getUser().getEmail());
        }

        if (payment.getSubscription() != null) {
            paymentDTO.setSubscriptionId(payment.getSubscription().getId());
        }

        return paymentDTO;
    }

    /**
     * Mapper to payment initiate response DTO.
     * @param payment Payment.
     * @return PaymentInitiateResponseDTO.
     */
    public PaymentInitiateResponseDTO toPaymentInitiateResponseDTO(Payment payment) {
        if (payment == null) return null;

        return PaymentInitiateResponseDTO.builder()
                .id(payment.getId())
                .paymentType(payment.getPaymentType())
                .paymentStatus(payment.getPaymentStatus())
                .paymentGateway(payment.getPaymentGateway())
                .amount(payment.getAmount())
                .transactionId(payment.getTransactionId())
                .gatewayPaymentId(payment.getGatewayPaymentId())
                .gatewayOrderId(payment.getGatewayOrderId())
                .gatewaySignature(payment.getGatewaySignature())
                .description(payment.getDescription())
                .failureReason(payment.getFailureReason())
                .initiatedAt(payment.getInitiatedAt())
                .completedAt(payment.getCompletedAt())
                .build();
    }

    /**
     * Mapper to DTO list.
     * @param payments List<Payment> .
     * @return List<PaymentDTO>.
     */
    public List<PaymentDTO> toDTOList(List<Payment> payments) {
        return payments.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
