package com.example.librarymanagermentservice.model;

import com.example.librarymanagermentservice.common.Constant;
import jakarta.persistence.*;
import lombok.*;

/**
 * Information about subscription plan.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionPlan extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String planCode;

    @Column(nullable = false, length = Constant.MAX_LENGTH_PLAN_NAME)
    private String name;

    private String description;

    @Column(nullable = false)
    private Integer durationDays;

    @Column(nullable = false)
    private Long price;

    private String currency = Constant.CURRENCY;

    @Column(nullable = false)
    private Integer maxBooksAllowed;

    @Column(nullable = false)
    private Integer maxDaysPerBook;

    private Integer displayOrder = Constant.DEFAULT_DISPLAY_ORDER;

    private Boolean isFeatured = false;

    private String badgeText;

    private String adminNotes;

    private String createdBy;

    private String updatedBy;
}
