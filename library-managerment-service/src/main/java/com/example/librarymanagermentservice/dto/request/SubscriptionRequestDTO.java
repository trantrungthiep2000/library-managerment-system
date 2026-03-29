package com.example.librarymanagermentservice.dto.request;

import com.example.librarymanagermentservice.common.message.SubscriptionMessageError;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Information about subscription request DTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionRequestDTO {
    @NotNull(message = SubscriptionMessageError.PLAN_ID_IS_REQUIRED)
    private Long planId;

    @NotNull(message = SubscriptionMessageError.START_DATE_IS_REQUIRED)
    private LocalDateTime startDate;

    @NotNull(message = SubscriptionMessageError.END_DATE_IS_REQUIRED)
    private LocalDateTime endDate;

    private Boolean autoRenew;

    private LocalDateTime cancelledAt;

    private String cancellationReason;

    private String notes;
}
