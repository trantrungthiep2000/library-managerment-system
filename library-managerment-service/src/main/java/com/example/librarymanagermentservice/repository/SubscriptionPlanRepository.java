package com.example.librarymanagermentservice.repository;

import com.example.librarymanagermentservice.model.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Information about interface subscription plan repository.
 */
public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {

    /**
     * Exists by plan code.
     * @param planCode String.
     * @return Boolean.
     */
    Boolean existsByPlanCode(String planCode);

    /**
     * Find by plan code.
     * @param planCode String.
     * @return SubscriptionPlan.
     */
    Optional<SubscriptionPlan> findByPlanCode(String planCode);
}
