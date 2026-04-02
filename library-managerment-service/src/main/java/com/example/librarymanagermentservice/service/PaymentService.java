package com.example.librarymanagermentservice.service;

import com.example.librarymanagermentservice.dto.PaymentDTO;
import com.example.librarymanagermentservice.dto.request.PaymentInitiateRequestDTO;
import com.example.librarymanagermentservice.dto.request.PaymentVerifyRequestDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.dto.response.PaymentInitiateResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Information about interface payment service.
 */
public interface PaymentService {

    /**
     * Initiate payment.
     * @param requestDTO PaymentInitiateRequestDTO.
     * @return PaymentInitiateResponseDTO.
     */
    ApiSuccessResponseDTO<PaymentInitiateResponseDTO> initiatePayment(PaymentInitiateRequestDTO requestDTO);

    /**
     * Verify payment.
     * @param requestDTO PaymentVerifyRequestDTO.
     * @return PaymentDTO.
     */
    ApiSuccessResponseDTO<PaymentDTO> verifyPayment(PaymentVerifyRequestDTO requestDTO);

    /**
     * Get all payments.
     * @param pageable Pageable.
     * @return Page<PaymentDTO>.
     */
    ApiSuccessResponseDTO<Page<PaymentDTO>> getAllPayments(Pageable pageable);
}
