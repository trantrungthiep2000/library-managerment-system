package com.example.librarymanagermentservice.repository;

import com.example.librarymanagermentservice.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Information about interface subscription repository.
 */
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    /**
     * Find all active subscription by user id.
     * @param userId Long.
     * @param today LocalDateTime.
     * @return List<Subscription>.
     */
    @Query("select s from Subscription s where s.user.id = :userId AND " +
            "s.active = true AND " +
            "s.startDate <= :today AND s.endDate >= :today")
    List<Subscription> findActiveSubscriptionByUserId(
            @Param("userId") Long userId,
            @Param("today") LocalDateTime today);

    /**
     * Find all subscription active expired.
     * @param today LocalDateTime.
     * @return List<Subscription>.
     */
    @Query("select s from Subscription s where s.active = true AND " +
            "s.endDate < :today")
    List<Subscription> findExpiredActiveSubscriptions(
            @Param("today") LocalDateTime today
    );
}
