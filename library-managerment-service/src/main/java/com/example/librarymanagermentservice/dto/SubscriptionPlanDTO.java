package com.example.librarymanagermentservice.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Information about subscription plan DTO.
 */
@Data
@Builder
public class SubscriptionPlanDTO {
    private Long id;
    private String planCode;
    private String name;
    private String description;
    private Integer durationDays;
    private Long price;
    private String currency;
    private Integer maxBooksAllowed;
    private Integer maxDaysPerBook;
    private Integer displayOrder;
    private Boolean active;
    private Boolean isFeatured;
    private String badgeText;
    private String adminNotes;
    private Double priceInMajorUnits;
    private Double monthlyEquivalentPrice;
    private String createdBy;
    private String updatedBy;
}
