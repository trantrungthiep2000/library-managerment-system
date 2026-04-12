package com.example.librarymanagermentservice.repository;

import com.example.librarymanagermentservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Information about interface payment repository.
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
