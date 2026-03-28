package com.example.librarymanagermentservice.controller;

import com.example.librarymanagermentservice.common.ApiRoutesConstant;
import com.example.librarymanagermentservice.dto.SubscriptionPlanDTO;
import com.example.librarymanagermentservice.dto.request.SubscriptionPlanRequestDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.service.SubscriptionPlanService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Information about subscription plan controller.
 */
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutesConstant.SubscriptionPlan.SubscriptionPlans)
public class SubscriptionPlanController {
    private final SubscriptionPlanService subscriptionPlanService;

    /**
     * Get all subscription plan.
     * @return List<SubscriptionPlanDTO>.
     */
    @GetMapping()
    public ResponseEntity<ApiSuccessResponseDTO<List<SubscriptionPlanDTO>>> getSubscriptionPlans() {
        var result = subscriptionPlanService.getSubscriptionPlans();
        return ResponseEntity.ok(result);
    }

    /**
     * Get subscription plan by id.
     * @param id Long.
     * @return SubscriptionPlanDTO.
     */
    @GetMapping(ApiRoutesConstant.SubscriptionPlan.ID)
    public ResponseEntity<ApiSuccessResponseDTO<SubscriptionPlanDTO>> getSubscriptionPlanById(@PathVariable Long id) {
        var result = subscriptionPlanService.getSubscriptionPlanById(id);
        return ResponseEntity.ok(result);
    }

    /**
     * Create a subscription plan.
     * @param requestDTO SubscriptionPlanRequestDTO.
     * @return SubscriptionPlanDTO.
     */
    @PostMapping()
    public ResponseEntity<ApiSuccessResponseDTO<SubscriptionPlanDTO>> createSubscriptionPlan(@Valid @RequestBody SubscriptionPlanRequestDTO requestDTO) {
        var result = subscriptionPlanService.createSubscriptionPlan(requestDTO);
        return ResponseEntity.ok(result);
    }

    /**
     * Update a subscription plan.
     * @param id Long.
     * @param requestDTO SubscriptionPlanRequestDTO.
     * @return SubscriptionPlanDTO.
     */
    @PutMapping(ApiRoutesConstant.SubscriptionPlan.ID)
    public ResponseEntity<ApiSuccessResponseDTO<SubscriptionPlanDTO>> updateSubscriptionPlan(@PathVariable Long id, @Valid @RequestBody SubscriptionPlanRequestDTO requestDTO) {
        var result = subscriptionPlanService.updateSubscriptionPlan(id, requestDTO);
        return ResponseEntity.ok(result);
    }

    /**
     * Delete a subscription plan.
     * @param id Long.
     * @return SubscriptionPlanDTO.
     */
    @DeleteMapping(ApiRoutesConstant.SubscriptionPlan.ID)
    public ResponseEntity<ApiSuccessResponseDTO<SubscriptionPlanDTO>> deleteSubscriptionPlan(@PathVariable Long id) {
        var result = subscriptionPlanService.deleteSubscriptionPlan(id);
        return ResponseEntity.ok(result);
    }

    /**
     * Hard delete a subscription plan.
     * @param id Long.
     * @return SubscriptionPlanDTO.
     */
    @DeleteMapping(ApiRoutesConstant.SubscriptionPlan.HARD_DELETE)
    public ResponseEntity<ApiSuccessResponseDTO<SubscriptionPlanDTO>> hardDeleteSubscriptionPlan(@PathVariable Long id) {
        var result = subscriptionPlanService.hardDeleteSubscriptionPlan(id);
        return ResponseEntity.ok(result);
    }
}
