package com.example.librarymanagermentservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Information about subscription DTO.
 */
@Data
@Builder
public class SubscriptionDTO {
    private Long id;
    private Long userId;
    private String userName;
    private String userEmail;
    private Long planId;
    private String planName;
    private String planCode;
    private Long price;
    private String currency;
    private Integer maxBooksAllowed;
    private Integer maxDaysPerBook;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean autoRenew;
    private LocalDateTime cancelledAt;
    private String cancellationReason;
    private String notes;
    private Boolean active;
    private Long daysRemaining;
    private Boolean isExpired;
    private Boolean isValid;
}
