package com.sncf.subscriptionservice.subscriptionmicroservice.Service;

import com.sncf.subscriptionservice.subscriptionmicroservice.Entity.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {
    List<Subscription> getAllSubscriptions();
    Page<Subscription> getAllSubscriptions(Pageable pageable);
    Optional<Subscription> getSubscriptionById(Long id);
    List<Subscription> getSubscriptionsByUserEmail(String email);
    Page<Subscription> getSubscriptionsByUserEmail(String email, Pageable pageable);
    Subscription createSubscription(Subscription subscription);
    Subscription updateSubscription(Long id, Subscription subscription);
    void deleteSubscription(Long id);
    List<Subscription> getSubscriptionsByStatus(boolean active);
    Page<Subscription> getSubscriptionsByStatus(boolean active, Pageable pageable);
    List<Subscription> findExpiringSubscriptions(int daysUntilExpiration);
}