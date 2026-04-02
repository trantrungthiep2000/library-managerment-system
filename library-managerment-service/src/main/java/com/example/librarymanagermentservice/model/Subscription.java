package com.example.librarymanagermentservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Information about subscription.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscription extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private SubscriptionPlan plan;

    private String planName;

    private String planCode;

    private Long price;

    @Column(nullable = false)
    private Integer maxBooksAllowed;

    @Column(nullable = false)
    private Integer maxDaysPerBook;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    private Boolean autoRenew;

    private LocalDateTime cancelledAt;

    private String cancellationReason;

    private String notes;

    /**
     * Is valid.
     * @return Boolean.
     */
    public Boolean isValid() {
        if (!getActive()) {
            return false;
        }

        LocalDateTime today = LocalDateTime.now();
        return !today.isBefore(startDate) && !today.isAfter(endDate);
    }

    /**
     * Is Expired.
     * @return Boolean
     */
    public Boolean isExpired() {
        return LocalDateTime.now().isAfter(endDate);
    }

    /**
     * Get days remaining.
     * @return Long.
     */
    public Long getDaysRemaining() {
        if (isExpired()) {
            return 0L;
        }

        return ChronoUnit.DAYS.between(LocalDateTime.now(), endDate);
    }

    /**
     * Calculate end date.
     */
    public void calculateEndDate() {
        if (plan != null && startDate != null) {
            this.endDate = startDate.plusDays(plan.getDurationDays());
        }
    }

    /**
     * Initialize from plan.
     */
    public void initializeFromPlan() {
        if (plan != null) {
            this.planCode = plan.getPlanCode();
            this.planName = plan.getName();
            this.price = plan.getPrice();
            this.maxBooksAllowed = plan.getMaxBooksAllowed();
            this.maxDaysPerBook = plan.getMaxDaysPerBook();
            if (startDate == null) {
                this.startDate = LocalDateTime.now();
            }
            calculateEndDate();
        }
    }
}
