package com.example.librarymanagermentservice.service.gateway;

import com.example.librarymanagermentservice.common.Constant;
import com.example.librarymanagermentservice.common.RazorpayConstant;
import com.example.librarymanagermentservice.common.enums.PaymentTypeEnum;
import com.example.librarymanagermentservice.dto.response.PaymentLinkResponseDTO;
import com.example.librarymanagermentservice.model.Payment;
import com.example.librarymanagermentservice.model.Subscription;
import com.example.librarymanagermentservice.model.User;
import com.example.librarymanagermentservice.repository.SubscriptionRepository;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Information about razor pay service.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RazorpayService {
    @Value("${razorpay.key.id}")
    private String razorpayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    private final SubscriptionRepository subscriptionRepository;

    /**
     * ICreate payment link.
     * @param user User.
     * @param payment Payment.
     * @return PaymentLinkResponseDTO.
     */
    public PaymentLinkResponseDTO createPaymentLink(User user, Payment payment) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);
            Long amountInPaisa = payment.getAmount() * (new BigDecimal(RazorpayConstant.BIG_DECIMAL.toString())).intValue();

            JSONObject customer = new JSONObject();
            customer.put(RazorpayConstant.NAME, user.getFullName());
            customer.put(RazorpayConstant.EMAIL, user.getEmail());

            JSONObject notify = new JSONObject();
            notify.put(RazorpayConstant.EMAIL, true);
            notify.put(RazorpayConstant.SMS, true);

            JSONObject notes = new JSONObject();
            notes.put(RazorpayConstant.USER_ID, user.getId());
            notes.put(RazorpayConstant.PAYMENT_ID, payment.getId());

            if (payment.getPaymentType() == PaymentTypeEnum.MEMBERSHIP) {
                notes.put(RazorpayConstant.SUBSCRIPTION_ID, payment.getSubscription().getId());
                notes.put(RazorpayConstant.PLAN_CODE, payment.getSubscription().getPlanCode());
                notes.put(RazorpayConstant.TYPE, PaymentTypeEnum.MEMBERSHIP);
            }
            else if (payment.getPaymentType() == PaymentTypeEnum.FINE) {
                // notes.put("fine_id", payment.getFine().getId()); // #TODO: get fine id
                notes.put(RazorpayConstant.TYPE, PaymentTypeEnum.FINE);
            }

            if (user.getPhoneNumber() != null && !user.getPhoneNumber().isEmpty()) {
                customer.put(RazorpayConstant.CONTACT, user.getPhoneNumber());
            }

            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put(RazorpayConstant.AMOUNT, amountInPaisa);
            paymentLinkRequest.put(RazorpayConstant.CURRENCY, Constant.CURRENCY);
            paymentLinkRequest.put(RazorpayConstant.DESCRIPTION, payment.getDescription());
            paymentLinkRequest.put(RazorpayConstant.CUSTOMER, customer);
            paymentLinkRequest.put(RazorpayConstant.NOTIFY, notify);
            paymentLinkRequest.put(RazorpayConstant.NOTES, notes);
            paymentLinkRequest.put(RazorpayConstant.REMINDER_ENABLE, true);// Enable reminder
            PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);

            PaymentLinkResponseDTO responseDTO = new PaymentLinkResponseDTO();
            responseDTO.setPaymentLinkURL(paymentLink.get(RazorpayConstant.SHORT_URL));
            responseDTO.setPaymentLinkId(paymentLink.get(RazorpayConstant.ID));
            return responseDTO;
        } catch (RazorpayException ex) {
            log.error("Error create payment link: {}", ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    /**
     * Fetch payment details.
     * @param paymentId String.
     * @return JSONObject.
     */
    public JSONObject fetchPaymentDetails(String paymentId) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);
            com.razorpay.Payment payment = razorpayClient.payments.fetch(paymentId);
            return payment.toJson();
        } catch (RazorpayException ex) {
            log.error("Error fetch payment details: {}", ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public Boolean isValidPayment(String paymentId) {
        try {
            JSONObject paymentDetails = fetchPaymentDetails((paymentId));
            String status = paymentDetails.optString(RazorpayConstant.STATUS);
            String paymentType = paymentDetails.optString(RazorpayConstant.TYPE);
            Long amount = paymentDetails.optLong(RazorpayConstant.AMOUNT);
            Long amountToRupees = amount / RazorpayConstant.BIG_DECIMAL;
            JSONObject notes = paymentDetails.getJSONObject(RazorpayConstant.NOTES);

            // Check status
            if (!RazorpayConstant.CAPTURED.equalsIgnoreCase(status)) {
                log.error("Payment not captured. Current status: {}", status);
                return false;
            }

            // Check expected amount
            if (paymentType.equals(PaymentTypeEnum.MEMBERSHIP.toString())) {
                String subscriptionId = notes.optString(RazorpayConstant.SUBSCRIPTION_ID);
                Subscription subscription = subscriptionRepository
                        .findById(Long.parseLong(subscriptionId)).orElse(null);

                if (subscription == null) {
                    log.error("Can not find subscription with ID: {}", subscriptionId);
                    return false;
                }

                return amountToRupees.equals(subscription.getPrice());
            } else if (paymentType.equals(PaymentTypeEnum.FINE.toString())) {
                // #TODO: get fine id
                String fineId = notes.optString("fine_id");
                return true;
            }

            return false;
        } catch (Exception ex) {
            log.error("Error check payment valid: {}", ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
}
