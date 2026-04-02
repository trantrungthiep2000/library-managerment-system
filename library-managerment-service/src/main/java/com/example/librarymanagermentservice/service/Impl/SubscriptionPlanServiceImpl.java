package com.example.librarymanagermentservice.service.Impl;

import com.example.librarymanagermentservice.common.message.SubscriptionPlanMessageError;
import com.example.librarymanagermentservice.common.message.UserMessageError;
import com.example.librarymanagermentservice.dto.SubscriptionPlanDTO;
import com.example.librarymanagermentservice.dto.request.SubscriptionPlanRequestDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.exception.BadRequestException;
import com.example.librarymanagermentservice.exception.NotFoundException;
import com.example.librarymanagermentservice.mapper.SubscriptionPlanMapper;
import com.example.librarymanagermentservice.model.SubscriptionPlan;
import com.example.librarymanagermentservice.model.User;
import com.example.librarymanagermentservice.repository.SubscriptionPlanRepository;
import com.example.librarymanagermentservice.service.SubscriptionPlanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Information about subscription plan service implement.
 */
@Service
@AllArgsConstructor
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {
    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final SubscriptionPlanMapper subscriptionPlanMapper;
    private final SecurityContextService securityContextService;

    /**
     * Get all subscription plan.
     * @return List<SubscriptionPlanDTO>.
     */
    @Override
    public ApiSuccessResponseDTO<List<SubscriptionPlanDTO>> getSubscriptionPlans() {
        List<SubscriptionPlan> subscriptionPlans = subscriptionPlanRepository.findAll();
        List<SubscriptionPlanDTO> subscriptionPlanDTOS = subscriptionPlanMapper.toDTOList(subscriptionPlans);
        return new ApiSuccessResponseDTO<>(subscriptionPlanDTOS);
    }

    /**
     * Get subscription plan by id.
     * @param id Long.
     * @return SubscriptionPlanDTO.
     */
    @Override
    public ApiSuccessResponseDTO<SubscriptionPlanDTO> getSubscriptionPlanById(Long id) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(id).orElse(null);
        if (subscriptionPlan == null) {
            throw new NotFoundException(SubscriptionPlanMessageError.SUBSCRIPTION_PLAN_NOT_FOUND);
        }

        SubscriptionPlanDTO subscriptionPlanDTO = subscriptionPlanMapper.toDTO(subscriptionPlan);
        return new ApiSuccessResponseDTO<>(subscriptionPlanDTO);
    }

    /**
     * Create a subscription plan.
     * @param requestDTO SubscriptionPlanRequestDTO.
     * @return SubscriptionPlanDTO.
     */
    @Override
    public ApiSuccessResponseDTO<SubscriptionPlanDTO> createSubscriptionPlan(SubscriptionPlanRequestDTO requestDTO) {
        Boolean isCheckSubscriptionPlanByCode = subscriptionPlanRepository.existsByPlanCode(requestDTO.getPlanCode());
        if (isCheckSubscriptionPlanByCode) {
            throw new BadRequestException(SubscriptionPlanMessageError.PLAN_CODE_USED);
        }

        User user = securityContextService.getCurrentUser().orElse(null);
        if (user == null) {
            throw new NotFoundException(UserMessageError.USER_NOT_FOUND);
        }

        SubscriptionPlan subscriptionPlan = subscriptionPlanMapper.toEntity(requestDTO);
        subscriptionPlan.setCreatedBy(user.getFullName());
        SubscriptionPlan subscriptionPlanCreate = subscriptionPlanRepository.save(subscriptionPlan);
        SubscriptionPlanDTO subscriptionPlanDTO = subscriptionPlanMapper.toDTO(subscriptionPlanCreate);
        return new ApiSuccessResponseDTO<>(subscriptionPlanDTO);
    }

    /**
     * Update a subscription plan.
     * @param id Long.
     * @param requestDTO SubscriptionPlanRequestDTO.
     * @return SubscriptionPlanDTO.
     */
    @Override
    public ApiSuccessResponseDTO<SubscriptionPlanDTO> updateSubscriptionPlan(Long id, SubscriptionPlanRequestDTO requestDTO) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(id).orElse(null);
        if (subscriptionPlan == null) {
            throw new NotFoundException(SubscriptionPlanMessageError.SUBSCRIPTION_PLAN_NOT_FOUND);
        }

        SubscriptionPlan subscriptionPlanByPlanCode = subscriptionPlanRepository.findByPlanCode(requestDTO.getPlanCode()).orElse(null);
        if (subscriptionPlanByPlanCode != null && !subscriptionPlanByPlanCode.getId().equals(subscriptionPlan.getId())) {
            throw new BadRequestException(SubscriptionPlanMessageError.PLAN_CODE_USED);
        }

        User user = securityContextService.getCurrentUser().orElse(null);
        if (user == null) {
            throw new NotFoundException(UserMessageError.USER_NOT_FOUND);
        }

        subscriptionPlanMapper.updateEntityToDTO(requestDTO, subscriptionPlan);
        subscriptionPlan.setUpdatedBy(user.getFullName());
        subscriptionPlan = subscriptionPlanRepository.save(subscriptionPlan);
        SubscriptionPlanDTO subscriptionPlanDTO = subscriptionPlanMapper.toDTO(subscriptionPlan);
        return new ApiSuccessResponseDTO<>(subscriptionPlanDTO);
    }

    /**
     * Delete a subscription plan.
     * @param id Long.
     * @return SubscriptionPlanDTO.
     */
    @Override
    public ApiSuccessResponseDTO<SubscriptionPlanDTO> deleteSubscriptionPlan(Long id) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(id).orElse(null);
        if (subscriptionPlan == null || !subscriptionPlan.getActive()) {
            throw new NotFoundException(SubscriptionPlanMessageError.SUBSCRIPTION_PLAN_NOT_FOUND);
        }

        User user = securityContextService.getCurrentUser().orElse(null);
        if (user == null) {
            throw new NotFoundException(UserMessageError.USER_NOT_FOUND);
        }

        subscriptionPlan.setUpdatedBy(user.getFullName());
        subscriptionPlan.setActive(false);
        subscriptionPlan = subscriptionPlanRepository.save(subscriptionPlan);
        SubscriptionPlanDTO subscriptionPlanDTO = subscriptionPlanMapper.toDTO(subscriptionPlan);
        return new ApiSuccessResponseDTO<>(subscriptionPlanDTO);
    }

    /**
     * Hard delete a subscription plan.
     * @param id Long.
     * @return SubscriptionPlanDTO.
     */
    @Override
    public ApiSuccessResponseDTO<SubscriptionPlanDTO> hardDeleteSubscriptionPlan(Long id) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(id).orElse(null);
        if (subscriptionPlan == null ) {
            throw new NotFoundException(SubscriptionPlanMessageError.SUBSCRIPTION_PLAN_NOT_FOUND);
        }

        subscriptionPlanRepository.delete(subscriptionPlan);
        SubscriptionPlanDTO subscriptionPlanDTO = subscriptionPlanMapper.toDTO(subscriptionPlan);
        return new ApiSuccessResponseDTO<>(subscriptionPlanDTO);
    }
}
