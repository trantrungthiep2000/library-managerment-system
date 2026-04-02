package com.example.librarymanagermentservice.event.listener;

import com.example.librarymanagermentservice.model.Payment;
import com.example.librarymanagermentservice.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Information about payment event listener.
 */
@Component
@AllArgsConstructor
public class PaymentEventListener {
    private final SubscriptionService subscriptionService;

    /**
     * Handle payment success.
     * @param payment Payment.
     * @return Boolean.
     */
    @Async
    @EventListener
    @Transactional
    public Boolean handlePaymentSuccess(Payment payment) {
        switch (payment.getPaymentType()) {
            case FINE:
            case LOST_BOOK_PENALTY:
            case DAMAGED_BOOK_PENALTY:
                break;

            case MEMBERSHIP:
                if (payment.getSubscription() != null) {
                    subscriptionService.activeSubscription(payment.getSubscription().getId(), payment.getId());
                }
                break;
        }

        return true;
    }
}
