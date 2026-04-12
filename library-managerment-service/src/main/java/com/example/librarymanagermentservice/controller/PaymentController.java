package com.example.librarymanagermentservice.controller;

import com.example.librarymanagermentservice.common.ApiRoutesConstant;
import com.example.librarymanagermentservice.dto.PaymentDTO;
import com.example.librarymanagermentservice.dto.request.PaymentVerifyRequestDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.service.PaymentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Information about payment controller.
 */
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutesConstant.Payment.PAYMENTS)
public class PaymentController {
    private final PaymentService paymentService;

    /**
     * Verify payment.
     * @param requestDTO PaymentVerifyRequestDTO.
     * @return PaymentDTO.
     */
    @PostMapping(ApiRoutesConstant.Payment.VERIFY)
    public ResponseEntity<ApiSuccessResponseDTO<PaymentDTO>> verifyPayment(@Valid @RequestBody PaymentVerifyRequestDTO requestDTO) {
        var result = paymentService.verifyPayment(requestDTO);
        return ResponseEntity.ok(result);
    }

    /**
     * Get all payments.
     * @param pageable Pageable.
     * @return Page<PaymentDTO>.
     */
    @GetMapping()
    public ResponseEntity<ApiSuccessResponseDTO<Page<PaymentDTO>>> getAllPayments(@Valid @RequestBody Pageable pageable) {
        var result = paymentService.getAllPayments(pageable);
        return ResponseEntity.ok(result);
    }
}
