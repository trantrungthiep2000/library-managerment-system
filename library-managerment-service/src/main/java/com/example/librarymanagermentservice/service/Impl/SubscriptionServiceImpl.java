package com.example.librarymanagermentservice.service.Impl;

import com.example.librarymanagermentservice.common.message.SubscriptionMessageError;
import com.example.librarymanagermentservice.common.message.SubscriptionPlanMessageError;
import com.example.librarymanagermentservice.common.message.UserMessageError;
import com.example.librarymanagermentservice.dto.SubscriptionDTO;
import com.example.librarymanagermentservice.dto.request.SubscriptionRequestDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.exception.NotFoundException;
import com.example.librarymanagermentservice.mapper.SubscriptionMapper;
import com.example.librarymanagermentservice.model.Subscription;
import com.example.librarymanagermentservice.model.SubscriptionPlan;
import com.example.librarymanagermentservice.model.User;
import com.example.librarymanagermentservice.repository.SubscriptionPlanRepository;
import com.example.librarymanagermentservice.repository.SubscriptionRepository;
import com.example.librarymanagermentservice.service.SecurityContextService;
import com.example.librarymanagermentservice.service.SubscriptionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Information about subscription service implement.
 */
@Slf4j
@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final SecurityContextService securityContextService;

    /**
     * Subscribe.
     * @param requestDTO SubscriptionRequestDTO.
     * @return SubscriptionDTO.
     */
    @Override
    public ApiSuccessResponseDTO<SubscriptionDTO> subscribe(SubscriptionRequestDTO requestDTO) {
        User user = securityContextService.getCurrentUser().orElse(null);
        if (user == null) {
            throw new NotFoundException(UserMessageError.USER_NOT_FOUND);
        }

        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository
                .findById(requestDTO.getPlanId()).orElse(null);
        if (subscriptionPlan == null) {
            throw new NotFoundException(SubscriptionPlanMessageError.SUBSCRIPTION_PLAN_NOT_FOUND);
        }

        Subscription subscription = subscriptionMapper.toEntity(requestDTO, user, subscriptionPlan);
        Subscription subscriptionCreate = subscriptionRepository.save(subscription);
        SubscriptionDTO subscriptionDTO = subscriptionMapper.toDTO(subscriptionCreate);
        return new ApiSuccessResponseDTO<>(subscriptionDTO);
    }

    /**
     * Get user active subscriptions.
     * @return List<SubscriptionDTO>.
     */
    @Override
    public ApiSuccessResponseDTO<List<SubscriptionDTO>> getUserActiveSubscriptions() {
        User user = securityContextService.getCurrentUser().orElse(null);
        if (user == null) {
            throw new NotFoundException(UserMessageError.USER_NOT_FOUND);
        }

        List<Subscription> subscriptions = subscriptionRepository
                .findActiveSubscriptionByUserId(user.getId(), LocalDateTime.now());
        List<SubscriptionDTO> subscriptionDTOS = subscriptionMapper.toDTOList(subscriptions);
        return new ApiSuccessResponseDTO<>(subscriptionDTOS);
    }

    /**
     * Cancel subscription.
     * @param subscriptionId Long.
     * @param reason String.
     * @return SubscriptionDTO.
     */
    @Override
    public ApiSuccessResponseDTO<SubscriptionDTO> cancelSubscription(Long subscriptionId, String reason) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElse(null);
        if (subscription == null) {
            throw new NotFoundException(SubscriptionMessageError.SUBSCRIPTION_NOT_FOUND);
        }

        if (!subscription.getActive()) {
            throw new NotFoundException(SubscriptionMessageError.SUBSCRIPTION_IS_INACTIVE);
        }

        subscription.setActive(false);
        subscription.setCancelledAt(LocalDateTime.now());
        subscription.setCancellationReason(reason);
        subscription = subscriptionRepository.save(subscription);

        log.info("Subscription cancelled successfully: {}", subscriptionId);
        SubscriptionDTO subscriptionDTO = subscriptionMapper.toDTO(subscription);
        return new ApiSuccessResponseDTO<>(subscriptionDTO);
    }

    /**
     * Active subscription.
     * @param subscriptionId Long.
     * @param paymentId Long.
     * @return SubscriptionDTO.
     */
    @Override
    public ApiSuccessResponseDTO<SubscriptionDTO> activeSubscription(Long subscriptionId, Long paymentId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElse(null);
        if (subscription == null) {
            throw new NotFoundException(SubscriptionMessageError.SUBSCRIPTION_NOT_FOUND);
        }

        if (subscription.getActive()) {
            throw new NotFoundException(SubscriptionMessageError.SUBSCRIPTION_IS_ACTIVE);
        }

        subscription.setActive(true);
        subscription.initializeFromPlan();
        subscription = subscriptionRepository.save(subscription);
        SubscriptionDTO subscriptionDTO = subscriptionMapper.toDTO(subscription);
        return new ApiSuccessResponseDTO<>(subscriptionDTO);
    }

    /**
     * Get al subscriptions.
     * @param pageable Pageable.
     * @return List<SubscriptionDTO>.
     */
    @Override
    public ApiSuccessResponseDTO<List<SubscriptionDTO>> getAllSubscriptions(Pageable pageable) {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        List<SubscriptionDTO> subscriptionDTOS = subscriptionMapper.toDTOList(subscriptions);
        return new ApiSuccessResponseDTO<>(subscriptionDTOS);
    }

    /**
     * Deactivate expired subscriptions.
     * @return Boolean.
     */
    @Override
    @Transactional
    public ApiSuccessResponseDTO<Boolean> deactivateExpiredSubscriptions() {
        List<Subscription> expiredSubscriptions = subscriptionRepository
                .findExpiredActiveSubscriptions(LocalDateTime.now());

        for (Subscription subscription : expiredSubscriptions) {
            subscription.setActive(false);
            subscriptionRepository.save(subscription);
        }

        return new ApiSuccessResponseDTO<>(true);
    }
}
