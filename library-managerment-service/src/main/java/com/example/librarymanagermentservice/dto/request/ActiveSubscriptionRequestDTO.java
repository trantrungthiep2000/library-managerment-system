package com.example.librarymanagermentservice.dto.request;

import com.example.librarymanagermentservice.common.message.SubscriptionMessageError;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

/**
 * Information about active subscription request DTO.
 */
@Data
@Builder
public class ActiveSubscriptionRequestDTO {
    @NotBlank(message = SubscriptionMessageError.SUBSCRIPTION_ID_IS_REQUIRED)
    private Long subscriptionId;

    @NotBlank(message = SubscriptionMessageError.PAYMENT_ID_IS_REQUIRED)
    private Long paymentId;
}
