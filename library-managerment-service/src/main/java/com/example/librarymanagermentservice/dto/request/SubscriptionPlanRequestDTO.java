package com.example.librarymanagermentservice.dto.request;

import com.example.librarymanagermentservice.common.Constant;
import com.example.librarymanagermentservice.common.message.SubscriptionPlanMessageError;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Information about subscription plan request DTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionPlanRequestDTO {
    @NotBlank(message = SubscriptionPlanMessageError.PLAN_CODE_NOT_BLANK)
    private String planCode;

    @NotBlank(message = SubscriptionPlanMessageError.PLAN_NAME_NOT_BLANK)
    @Size(max = 100, message = SubscriptionPlanMessageError.PLAN_NAME_MAX_LENGTH_100_CHARACTERS)
    private String name;

    private String description;

    @NotNull(message = SubscriptionPlanMessageError.DURATION_DAYS_NOT_BLANK)
    @Positive(message = SubscriptionPlanMessageError.DURATION_DAYS_MUST_POSITIVE)
    private Integer durationDays;

    @NotNull(message = SubscriptionPlanMessageError.PRICE_NOT_BLANK)
    @Positive(message = SubscriptionPlanMessageError.PRICE_MUST_POSITIVE)
    private Long price;

    private String currency;

    @NotNull(message = SubscriptionPlanMessageError.MAX_BOOKS_ALLOWED_NOT_BLANK)
    @Positive(message = SubscriptionPlanMessageError.MAX_BOOKS_ALLOWED_MUST_POSITIVE)
    private Integer maxBooksAllowed;

    @NotNull(message = SubscriptionPlanMessageError.MAX_DAYS_PER_BOOK_NOT_BLANK)
    @Positive(message = SubscriptionPlanMessageError.MAX_DAYS_PER_BOOK_MUST_POSITIVE)
    private Integer maxDaysPerBook;

    private Integer displayOrder = Constant.DEFAULT_DISPLAY_ORDER;
    private Boolean isFeatured;
    private String badgeText;
    private String adminNotes;
    private Double priceInMajorUnits;
    private Double monthlyEquivalentPrice;
    private String createdBy;
    private String updatedBy;
}
