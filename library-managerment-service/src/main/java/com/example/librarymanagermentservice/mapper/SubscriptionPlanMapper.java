package com.example.librarymanagermentservice.mapper;

import com.example.librarymanagermentservice.dto.SubscriptionPlanDTO;
import com.example.librarymanagermentservice.dto.request.SubscriptionPlanRequestDTO;
import com.example.librarymanagermentservice.model.SubscriptionPlan;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Information about subscription plan mapper.
 */
@Service
@AllArgsConstructor
public class SubscriptionPlanMapper {
    /**
     * Mapper to DTO.
     * @param subscriptionPlan SubscriptionPlan.
     * @return SubscriptionPlanDTO.
     */
    public SubscriptionPlanDTO toDTO(SubscriptionPlan subscriptionPlan) {
        if (subscriptionPlan == null) return null;

        return SubscriptionPlanDTO.builder()
                .id(subscriptionPlan.getId())
                .planCode(subscriptionPlan.getPlanCode())
                .name(subscriptionPlan.getName())
                .description(subscriptionPlan.getDescription())
                .currency(subscriptionPlan.getCurrency())
                .durationDays(subscriptionPlan.getDurationDays())
                .price(subscriptionPlan.getPrice())
                .displayOrder(subscriptionPlan.getDisplayOrder())
                .isFeatured(subscriptionPlan.getIsFeatured())
                .maxBooksAllowed(subscriptionPlan.getMaxBooksAllowed())
                .maxDaysPerBook(subscriptionPlan.getMaxDaysPerBook())
                .badgeText(subscriptionPlan.getBadgeText())
                .adminNotes(subscriptionPlan.getAdminNotes())
                .active(subscriptionPlan.getActive())
                .createdBy(subscriptionPlan.getCreatedBy())
                .updatedBy(subscriptionPlan.getUpdatedBy())
                .build();
    }

    /**
     * Mapper to entity.
     * @param requestDTO SubscriptionPlanRequestDTO.
     * @return SubscriptionPlan.
     */
    public SubscriptionPlan toEntity(SubscriptionPlanRequestDTO requestDTO) {
        if (requestDTO == null) return null;

        return SubscriptionPlan.builder()
                .planCode(requestDTO.getPlanCode())
                .name(requestDTO.getName())
                .description(requestDTO.getDescription())
                .durationDays(requestDTO.getDurationDays())
                .price(requestDTO.getPrice())
                .currency(requestDTO.getCurrency())
                .maxBooksAllowed(requestDTO.getMaxBooksAllowed())
                .maxDaysPerBook(requestDTO.getMaxDaysPerBook())
                .displayOrder(requestDTO.getDisplayOrder())
                .isFeatured(requestDTO.getIsFeatured())
                .badgeText(requestDTO.getBadgeText())
                .adminNotes(requestDTO.getAdminNotes())
                .build();
    }

    /**
     * Update entity to DTO.
     * @param requestDTO SubscriptionPlanRequestDTO.
     * @param subscriptionPlan SubscriptionPlan.
     */
    public void updateEntityToDTO(SubscriptionPlanRequestDTO requestDTO, SubscriptionPlan subscriptionPlan) {
        subscriptionPlan.setPlanCode(requestDTO.getPlanCode());
        subscriptionPlan.setName(requestDTO.getName());
        subscriptionPlan.setDescription(requestDTO.getDescription());
        subscriptionPlan.setDurationDays(requestDTO.getDurationDays());
        subscriptionPlan.setPrice(requestDTO.getPrice());
        subscriptionPlan.setCurrency(requestDTO.getCurrency());
        subscriptionPlan.setMaxBooksAllowed(requestDTO.getMaxBooksAllowed());
        subscriptionPlan.setMaxDaysPerBook(requestDTO.getMaxDaysPerBook());
        subscriptionPlan.setDisplayOrder(requestDTO.getDisplayOrder());
        subscriptionPlan.setIsFeatured(requestDTO.getIsFeatured());
        subscriptionPlan.setBadgeText(requestDTO.getBadgeText());
        subscriptionPlan.setAdminNotes(requestDTO.getAdminNotes());
    }

    /**
     * Mapper to DTO list.
     * @param subscriptionPlans List<SubscriptionPlan> .
     * @return List<SubscriptionPlanDTO>.
     */
    public List<SubscriptionPlanDTO> toDTOList(List<SubscriptionPlan> subscriptionPlans) {
        return subscriptionPlans.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
