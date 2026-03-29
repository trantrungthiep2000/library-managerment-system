package com.example.librarymanagermentservice.mapper;

import com.example.librarymanagermentservice.dto.SubscriptionDTO;
import com.example.librarymanagermentservice.dto.request.SubscriptionRequestDTO;
import com.example.librarymanagermentservice.model.Subscription;
import com.example.librarymanagermentservice.model.SubscriptionPlan;
import com.example.librarymanagermentservice.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Information about subscription mapper.
 */
@Component
public class SubscriptionMapper {
    /**
     * Mapper to DTO.
     * @param subscription Subscription.
     * @return SubscriptionDTO.
     */
    public SubscriptionDTO toDTO(Subscription subscription) {
        if (subscription == null) return null;

        SubscriptionDTO subscriptionDTO = SubscriptionDTO.builder()
                .id(subscription.getId())
                .planCode(subscription.getPlanCode())
                .planName(subscription.getPlanName())
                .price(subscription.getPrice())
                .maxBooksAllowed(subscription.getMaxBooksAllowed())
                .maxDaysPerBook(subscription.getMaxDaysPerBook())
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .autoRenew(subscription.getAutoRenew())
                .cancelledAt(subscription.getCancelledAt())
                .cancellationReason(subscription.getCancellationReason())
                .notes(subscription.getNotes())
                .active(subscription.getActive())
                .build();
        subscriptionDTO.setDaysRemaining(subscription.getDaysRemaining());
        subscriptionDTO.setIsValid(subscription.isValid());
        subscriptionDTO.setIsExpired(subscription.isExpired());

        if (subscription.getUser() != null) {
            subscriptionDTO.setUserId(subscription.getUser().getId());
            subscriptionDTO.setUserName(subscription.getUser().getFullName());
            subscriptionDTO.setUserEmail(subscription.getUser().getEmail());
        }

        if (subscription.getPlan() != null) {
            subscriptionDTO.setPlanId(subscription.getPlan().getId());
            subscriptionDTO.setCurrency(subscription.getPlan().getCurrency());
        }

        return subscriptionDTO;
    }

    /**
     * Mapper to entity.
     * @param requestDTO SubscriptionRequestDTO.
     * @param user User.
     * @param plan SubscriptionPlan.
     * @return Subscription.
     */
    public Subscription toEntity(SubscriptionRequestDTO requestDTO, User user, SubscriptionPlan plan) {
        if (requestDTO == null) return null;

        Subscription subscription = Subscription.builder()
                .user(user)
                .plan(plan)
                .autoRenew(requestDTO.getAutoRenew())
                .cancelledAt(requestDTO.getCancelledAt())
                .cancellationReason(requestDTO.getCancellationReason())
                .notes(requestDTO.getNotes())
                .build();
        subscription.initializeFromPlan();

        return subscription;
    }

    /**
     * Mapper to DTO list.
     * @param subscriptions List<Subscription> .
     * @return List<SubscriptionDTO>.
     */
    public List<SubscriptionDTO> toDTOList(List<Subscription> subscriptions) {
        return subscriptions.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
