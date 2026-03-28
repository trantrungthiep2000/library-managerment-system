package com.example.librarymanagermentservice.service;

import com.example.librarymanagermentservice.dto.SubscriptionPlanDTO;
import com.example.librarymanagermentservice.dto.request.SubscriptionPlanRequestDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;

import java.util.List;

/**
 * Information about interface subscription plan service.
 */
public interface SubscriptionPlanService {

    /**
     * Get all subscription plan.
     * @return List<SubscriptionPlanDTO>.
     */
    ApiSuccessResponseDTO<List<SubscriptionPlanDTO>> getSubscriptionPlans();

    /**
     * Get subscription plan by id.
     * @param id Long.
     * @return SubscriptionPlanDTO.
     */
    ApiSuccessResponseDTO<SubscriptionPlanDTO> getSubscriptionPlanById(Long id);

    /**
     * Create a subscription plan.
     * @param requestDTO SubscriptionPlanRequestDTO.
     * @return SubscriptionPlanDTO.
     */
    ApiSuccessResponseDTO<SubscriptionPlanDTO> createSubscriptionPlan(SubscriptionPlanRequestDTO requestDTO);

    /**
     * Update a subscription plan.
     * @param id Long.
     * @param requestDTO SubscriptionPlanRequestDTO.
     * @return SubscriptionPlanDTO.
     */
    ApiSuccessResponseDTO<SubscriptionPlanDTO> updateSubscriptionPlan(Long id, SubscriptionPlanRequestDTO requestDTO);

    /**
     * Delete a subscription plan.
     * @param id Long.
     * @return SubscriptionPlanDTO.
     */
    ApiSuccessResponseDTO<SubscriptionPlanDTO> deleteSubscriptionPlan(Long id);

    /**
     * Hard delete a subscription plan.
     * @param id Long.
     * @return SubscriptionPlanDTO.
     */
    ApiSuccessResponseDTO<SubscriptionPlanDTO> hardDeleteSubscriptionPlan(Long id);
}
