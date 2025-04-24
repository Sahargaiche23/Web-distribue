package com.sncf.subscriptionservice.subscriptionmicroservice.Service;

import com.sncf.subscriptionservice.subscriptionmicroservice.Entity.Subscription;
import com.sncf.subscriptionservice.subscriptionmicroservice.Repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repository;

    @Override
    public List<Subscription> getAllSubscriptions() {
        return repository.findAll();
    }

    @Override
    public Page<Subscription> getAllSubscriptions(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Subscription> getSubscriptionById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Subscription> getSubscriptionsByUserEmail(String email) {
        return repository.findByUserEmail(email);
    }

    @Override
    public Page<Subscription> getSubscriptionsByUserEmail(String email, Pageable pageable) {
        return repository.findByUserEmail(email, pageable);
    }

    @Override
    public Subscription createSubscription(Subscription subscription) {
        return repository.save(subscription);
    }

    @Override
    public Subscription updateSubscription(Long id, Subscription subscription) {
        return repository.findById(id).map(existing -> {
            existing.setUserEmail(subscription.getUserEmail());
            existing.setPlan(subscription.getPlan());
            existing.setStartDate(subscription.getStartDate());
            existing.setEndDate(subscription.getEndDate());
            existing.setActive(subscription.isActive());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Subscription not found"));
    }

    @Override
    public void deleteSubscription(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Subscription> getSubscriptionsByStatus(boolean active) {
        return repository.findByActive(active);
    }

    @Override
    public Page<Subscription> getSubscriptionsByStatus(boolean active, Pageable pageable) {
        return repository.findByActive(active, pageable);
    }

    @Override
    public List<Subscription> findExpiringSubscriptions(int daysUntilExpiration) {
        LocalDate expirationDate = LocalDate.now().plusDays(daysUntilExpiration);
        return repository.findByEndDateBetweenAndActiveTrue(LocalDate.now(), expirationDate);
    }
}