package com.example.librarymanagermentservice.service;

import com.example.librarymanagermentservice.dto.SubscriptionDTO;
import com.example.librarymanagermentservice.dto.request.SubscriptionRequestDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Information about subscription service.
 */
public interface SubscriptionService {
    /**
     * Subscribe.
     * @param requestDTO SubscriptionRequestDTO.
     * @return SubscriptionDTO.
     */
    ApiSuccessResponseDTO<SubscriptionDTO> subscribe(SubscriptionRequestDTO requestDTO);

    /**
     * Get user active subscriptions.
     * @return List<SubscriptionDTO>.
     */
    ApiSuccessResponseDTO<List<SubscriptionDTO>> getUserActiveSubscriptions();

    /**
     * Cancel subscription.
     * @param subscriptionId Long.
     * @param reason String.
     * @return SubscriptionDTO.
     */
    ApiSuccessResponseDTO<SubscriptionDTO> cancelSubscription(Long subscriptionId, String reason);

    /**
     * Active subscription.
     * @param subscriptionId Long.
     * @param paymentId Long.
     * @return SubscriptionDTO.
     */
    ApiSuccessResponseDTO<SubscriptionDTO> activeSubscription(Long subscriptionId, Long paymentId);

    /**
     * Get all subscription.
     * @param pageable Pageable.
     * @return List<SubscriptionDTO>.
     */
    ApiSuccessResponseDTO<List<SubscriptionDTO>> getAllSubscriptions(Pageable pageable);

    /**
     * Deactivate Expired subscriptions.
     * @return Boolean.
     */
    ApiSuccessResponseDTO<Boolean> deactivateExpiredSubscriptions();
}
