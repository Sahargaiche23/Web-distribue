package com.sncf.subscriptionservice.subscriptionmicroservice.Controller;

import com.sncf.subscriptionservice.subscriptionmicroservice.Entity.Subscription;
import com.sncf.subscriptionservice.subscriptionmicroservice.Service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")

@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping
    public Page<Subscription> getAll(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return subscriptionService.getAllSubscriptions(pageable);
    }

    @GetMapping("/list")
    public List<Subscription> getAllAsList() {
        return subscriptionService.getAllSubscriptions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getById(@PathVariable Long id) {
        return subscriptionService.getSubscriptionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user")
    public Page<Subscription> getByUserEmail(
            @RequestParam String email,
            @PageableDefault(size = 10, sort = "startDate") Pageable pageable) {
        return subscriptionService.getSubscriptionsByUserEmail(email, pageable);
    }

    @GetMapping("/user/list")
    public List<Subscription> getByUserEmailAsList(@RequestParam String email) {
        return subscriptionService.getSubscriptionsByUserEmail(email);
    }

    @PostMapping
    public Subscription create(@RequestBody Subscription subscription) {
        return subscriptionService.createSubscription(subscription);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> update(@PathVariable Long id, @RequestBody Subscription subscription) {
        try {
            return ResponseEntity.ok(subscriptionService.updateSubscription(id, subscription));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status")
    public Page<Subscription> getSubscriptionsByStatus(
            @RequestParam boolean active,
            @PageableDefault(size = 10, sort = "endDate") Pageable pageable) {
        return subscriptionService.getSubscriptionsByStatus(active, pageable);
    }

    @GetMapping("/status/list")
    public List<Subscription> getSubscriptionsByStatusAsList(@RequestParam boolean active) {
        return subscriptionService.getSubscriptionsByStatus(active);
    }

    @GetMapping("/expiring")
    public List<Subscription> getExpiringSubscriptions(@RequestParam(defaultValue = "30") int days) {
        return subscriptionService.findExpiringSubscriptions(days);
    }
}