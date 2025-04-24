package com.example.pawpalsysAdmin.stats;

import org.springframework.data.jpa.repository.JpaRepository;

public interface statsRepository extends JpaRepository<stats, Long> {
    // You can add custom queries here if needed, for example:
    stats findByProviderId(Long providerId);
}
