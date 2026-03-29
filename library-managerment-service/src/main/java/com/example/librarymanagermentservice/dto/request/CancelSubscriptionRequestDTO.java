package com.example.librarymanagermentservice.dto.request;

import com.example.librarymanagermentservice.common.message.SubscriptionMessageError;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

/**
 * Information about cancel subscription request DTO.
 */
@Data
@Builder
public class CancelSubscriptionRequestDTO {
    @NotBlank(message = SubscriptionMessageError.REASON_NOT_BLANK)
    private String reason;
}
