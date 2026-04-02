package com.example.librarymanagermentservice.service.Impl;

import com.example.librarymanagermentservice.common.RazorpayConstant;
import com.example.librarymanagermentservice.common.enums.PaymentGatewayEnum;
import com.example.librarymanagermentservice.common.enums.PaymentStatusEnum;
import com.example.librarymanagermentservice.common.message.PaymentMessageError;
import com.example.librarymanagermentservice.common.message.SubscriptionMessageError;
import com.example.librarymanagermentservice.common.message.UserMessageError;
import com.example.librarymanagermentservice.dto.PaymentDTO;
import com.example.librarymanagermentservice.dto.request.PaymentInitiateRequestDTO;
import com.example.librarymanagermentservice.dto.request.PaymentVerifyRequestDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.dto.response.PaymentInitiateResponseDTO;
import com.example.librarymanagermentservice.dto.response.PaymentLinkResponseDTO;
import com.example.librarymanagermentservice.event.publisher.PaymentEventPublisher;
import com.example.librarymanagermentservice.exception.BadRequestException;
import com.example.librarymanagermentservice.exception.NotFoundException;
import com.example.librarymanagermentservice.mapper.PaymentMapper;
import com.example.librarymanagermentservice.model.Payment;
import com.example.librarymanagermentservice.model.Subscription;
import com.example.librarymanagermentservice.model.User;
import com.example.librarymanagermentservice.repository.PaymentRepository;
import com.example.librarymanagermentservice.repository.SubscriptionRepository;
import com.example.librarymanagermentservice.repository.UserRepository;
import com.example.librarymanagermentservice.service.PaymentService;
import com.example.librarymanagermentservice.service.gateway.RazorpayService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Information about payment service implement.
 */
@Slf4j
@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final RazorpayService razorpayService;
    private final PaymentEventPublisher paymentEventPublisher;

    /**
     * Initiate payment.
     * @param requestDTO PaymentInitiateRequestDTO.
     * @return PaymentInitiateResponseDTO.
     */
    @Override
    public ApiSuccessResponseDTO<PaymentInitiateResponseDTO> initiatePayment(PaymentInitiateRequestDTO requestDTO) {
        User user = userRepository.findById(requestDTO.getUserId()).orElse(null);
        if (user == null) {
            throw new NotFoundException(UserMessageError.USER_NOT_FOUND);
        }

        Subscription subscription = subscriptionRepository.findById(requestDTO.getSubscriptionId()).orElse(null);
        if (subscription == null) {
            throw new NotFoundException(SubscriptionMessageError.SUBSCRIPTION_NOT_FOUND);
        }

        Payment payment = paymentMapper.toEntity(requestDTO, user, subscription);
        payment = paymentRepository.save(payment);
        PaymentInitiateResponseDTO responseDTO = paymentMapper.toPaymentInitiateResponseDTO(payment);

        if (requestDTO.getPaymentGateway() == PaymentGatewayEnum.RAZORPAY) {
            PaymentLinkResponseDTO paymentLinkResponseDTO = razorpayService.createPaymentLink(user, payment);
            responseDTO.setCheckoutURL(paymentLinkResponseDTO.getPaymentLinkURL());
            responseDTO.setGatewayOrderId(paymentLinkResponseDTO.getPaymentLinkId());
            responseDTO.setSuccess(true);
            payment.setGatewayOrderId(paymentLinkResponseDTO.getPaymentLinkId());
            payment.setPaymentStatus(PaymentStatusEnum.PROCESSING);
        }

        payment = paymentRepository.save(payment);
        responseDTO.setPaymentStatus(payment.getPaymentStatus());

        return new ApiSuccessResponseDTO<>(responseDTO);
    }

    /**
     * Verify payment.
     * @param requestDTO PaymentVerifyRequestDTO.
     * @return PaymentDTO.
     */
    @Override
    public ApiSuccessResponseDTO<PaymentDTO> verifyPayment(PaymentVerifyRequestDTO requestDTO) {
        JSONObject paymentDetails = razorpayService.fetchPaymentDetails(requestDTO.getRazorpayPaymentId());
        if (paymentDetails == null) {
            throw new NotFoundException(PaymentMessageError.PAYMENT_DETAILS_NOT_FOUND);
        }

        if (!paymentDetails.has(RazorpayConstant.NOTES) || paymentDetails.isNull(RazorpayConstant.NOTES)) {
            throw new NotFoundException(PaymentMessageError.PAYMENT_DETAILS_NOTES_NOT_FOUND);
        }

        JSONObject notes = paymentDetails.getJSONObject(RazorpayConstant.NOTES);

        String paymentIdStr = notes.optString(RazorpayConstant.PAYMENT_ID);
        if (paymentIdStr == null || paymentIdStr.isEmpty()) {
            throw new BadRequestException(PaymentMessageError.PAYMENT_ID_NOT_FORMAT);
        }

        long paymentId;
        try {
            paymentId = Long.parseLong(paymentIdStr);
        } catch (NumberFormatException e) {
            throw new BadRequestException(PaymentMessageError.PAYMENT_ID_NOT_FORMAT);
        }

        Payment payment = paymentRepository.findById(paymentId).orElse(null);
        if (payment == null) {
            throw new NotFoundException(PaymentMessageError.PAYMENT_NOT_FOUND);
        }

        Boolean isValid = razorpayService.isValidPayment(paymentIdStr);

        if (payment.getPaymentGateway() == PaymentGatewayEnum.RAZORPAY && isValid) {
            payment.setGatewayPaymentId(requestDTO.getRazorpayPaymentId());
        }

        if (isValid) {
            payment.setPaymentStatus(PaymentStatusEnum.SUCCESS);
            payment.setCompletedAt(LocalDateTime.now());
            payment = paymentRepository.save(payment);

            Boolean status = paymentEventPublisher.publishPaymentSuccessEvent(payment);
            if (!status) {
                log.warn("Publish payment success event failed: {}", payment.getId());
            }
        }

        PaymentDTO paymentDTO = paymentMapper.toDTO(payment);
        return new ApiSuccessResponseDTO<>(paymentDTO);
    }

    /**
     * Get all payment.
     * @param pageable Pageable.
     * @return Page<PaymentDTO>.
     */
    @Override
    public ApiSuccessResponseDTO<Page<PaymentDTO>> getAllPayments(Pageable pageable) {
        Page<Payment> payments = paymentRepository.findAll(pageable);
        Page<PaymentDTO> paymentDTOS = payments.map(paymentMapper::toDTO);
        return new ApiSuccessResponseDTO<>(paymentDTOS);
    }
}
