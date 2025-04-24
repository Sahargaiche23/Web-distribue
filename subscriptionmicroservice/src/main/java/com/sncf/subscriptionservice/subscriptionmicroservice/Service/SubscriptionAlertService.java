package com.sncf.subscriptionservice.subscriptionmicroservice.Service;

import com.sncf.subscriptionservice.subscriptionmicroservice.Entity.Subscription;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionAlertService {

    private final SubscriptionService subscriptionService;

    // Exécuté tous les jours à minuit
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkExpiringSubscriptions() {
        // Vérifier les abonnements qui expirent dans les 7 jours
        List<Subscription> expiringSubscriptions = subscriptionService.findExpiringSubscriptions(7);

        for (Subscription subscription : expiringSubscriptions) {
            sendExpirationAlert(subscription);
        }

        log.info("Checked {} expiring subscriptions", expiringSubscriptions.size());
    }

    private void sendExpirationAlert(Subscription subscription) {
        // lina najem nzid  l'envoi d'emails ou de notifications
        // Pour l'instant, simplement logger l'alerte
        log.info("ALERT: Subscription #{} for user {} will expire on {}",
                subscription.getId(),
                subscription.getUserEmail(),
                subscription.getEndDate());
    }
}