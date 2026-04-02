package com.example.librarymanagermentservice.controller;

import com.example.librarymanagermentservice.common.ApiRoutesConstant;
import com.example.librarymanagermentservice.dto.SubscriptionDTO;
import com.example.librarymanagermentservice.dto.request.ActiveSubscriptionRequestDTO;
import com.example.librarymanagermentservice.dto.request.CancelSubscriptionRequestDTO;
import com.example.librarymanagermentservice.dto.request.SubscriptionRequestDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.dto.response.PaymentInitiateResponseDTO;
import com.example.librarymanagermentservice.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutesConstant.Subscription.SUBSCRIPTIONS)
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    /**
     * Subscribe.
     * @param requestDTO SubscriptionRequestDTO.
     * @return PaymentInitiateResponseDTO.
     */
    @PostMapping(ApiRoutesConstant.Subscription.SUBSCRIBE)
    public ResponseEntity<ApiSuccessResponseDTO<PaymentInitiateResponseDTO>> subscribe(@Valid @RequestBody SubscriptionRequestDTO requestDTO) {
        var result = subscriptionService.subscribe(requestDTO);
        return ResponseEntity.ok(result);
    }

    /**
     * Get user active subscriptions.
     * @return List<SubscriptionDTO>.
     */
    @GetMapping(ApiRoutesConstant.Subscription.ACTIVE_SUBSCRIPTIONS_USER)
    public ResponseEntity<ApiSuccessResponseDTO<List<SubscriptionDTO>>> getUserActiveSubscriptions() {
        var result = subscriptionService.getUserActiveSubscriptions();
        return ResponseEntity.ok(result);
    }

    /**
     * Cancel subscription.
     * @param id Long.
     * @param requestDTO CancelSubscriptionRequestDTO.
     * @return SubscriptionDTO.
     */
    @PostMapping(ApiRoutesConstant.Subscription.CANCEL_SUBSCRIPTION)
    public ResponseEntity<ApiSuccessResponseDTO<SubscriptionDTO>> cancelSubscription(@PathVariable Long id, @Valid @RequestBody CancelSubscriptionRequestDTO requestDTO) {
        var result = subscriptionService.cancelSubscription(id, requestDTO.getReason());
        return ResponseEntity.ok(result);
    }

    /**
     * Active subscription.
     * @param requestDTO ActiveSubscriptionRequestDTO.
     * @return SubscriptionDTO.
     */
    @PostMapping(ApiRoutesConstant.Subscription.ACTIVE_SUBSCRIPTION)
    public ResponseEntity<ApiSuccessResponseDTO<SubscriptionDTO>> activeSubscription(@Valid @RequestBody ActiveSubscriptionRequestDTO requestDTO) {
        var result = subscriptionService.activeSubscription(requestDTO.getSubscriptionId(), requestDTO.getPaymentId());
        return ResponseEntity.ok(result);
    }

    /**
     * Get all subscriptions.
     * @param pageable Pageable.
     * @return List<SubscriptionDTO>.
     */
    @GetMapping(ApiRoutesConstant.ADMIN)
    public ResponseEntity<ApiSuccessResponseDTO<List<SubscriptionDTO>>> getAllSubscriptions(Pageable pageable) {
        var result = subscriptionService.getAllSubscriptions(pageable);
        return ResponseEntity.ok(result);
    }

    /**
     * Deactivate expired subscriptions.
     * @return Boolean.
     */
    @PostMapping(ApiRoutesConstant.Subscription.DEACTIVATE_EXPIRED)
    public ResponseEntity<ApiSuccessResponseDTO<Boolean>> deactivateExpiredSubscriptions() {
        var result = subscriptionService.deactivateExpiredSubscriptions();
        return ResponseEntity.ok(result);
    }
}
