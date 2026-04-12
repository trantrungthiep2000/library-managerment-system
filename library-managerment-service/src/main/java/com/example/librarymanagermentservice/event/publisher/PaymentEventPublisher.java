package com.example.librarymanagermentservice.event.publisher;

import com.example.librarymanagermentservice.model.Payment;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Information about payment event publisher.
 */
@Component
@AllArgsConstructor
public class PaymentEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * Publish payment success event.
     * @param payment Payment.
     * @return Boolean.
     */
    public Boolean publishPaymentSuccessEvent(Payment payment) {
        applicationEventPublisher.publishEvent(payment);
        return true;
    }

}
