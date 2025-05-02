package com.example.pawpalsysAdmin.stats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StatsRepository extends JpaRepository<Stats, Integer> {

    Optional<Stats> findByProviderId(int providerId);
    Optional<Stats> findByDate(LocalDate date);
}
