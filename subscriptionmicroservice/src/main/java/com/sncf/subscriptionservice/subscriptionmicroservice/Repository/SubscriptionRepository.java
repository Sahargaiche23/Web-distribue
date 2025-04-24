package com.sncf.subscriptionservice.subscriptionmicroservice.Repository;

import com.sncf.subscriptionservice.subscriptionmicroservice.Entity.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserEmail(String email);
    Page<Subscription> findByUserEmail(String email, Pageable pageable);
    List<Subscription> findByActiveTrue();
    List<Subscription> findByActive(boolean active);
    Page<Subscription> findByActive(boolean active, Pageable pageable);
    List<Subscription> findByEndDateBetweenAndActiveTrue(LocalDate startDate, LocalDate endDate);
}