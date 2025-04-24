package com.sncf.subscriptionservice.subscriptionmicroservice.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    @Enumerated(EnumType.STRING)
    private Plan plan;

    private boolean active;

    private LocalDate startDate;
    private LocalDate endDate;

    public enum Plan {
        FREE,
        STANDARD,
        PREMIUM
    }
}
