package com.example.librarymanagermentservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Information about payment link response DTO.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentLinkResponseDTO {
    private String paymentLinkURL;
    private String paymentLinkId;
}
