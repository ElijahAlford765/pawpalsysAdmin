package com.example.pawpalsysAdmin.Stats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface statsRepository  extends JpaRepository<stats, Integer> {
    // Find Stats by serviceId
    stats findByServiceId(int serviceId);

    // Find all stats for a given range of active users
    List<stats> findByActiveUsersBetween(int minActiveUsers, int maxActiveUsers);

    // Find stats where the number of active providers is greater than a certain threshold
    List<stats> findByActiveProvidersGreaterThan(int threshold);

    // Find stats by total bookings
    List<stats> findByTotalBookings(int totalBookings);

    // Find all stats where the number of reviews exceeds a given threshold
    List<stats> findByTotalReviewsGreaterThan(int threshold);

    // Find the stats with the maximum total users
    stats findTopByOrderByTotalUsersDesc();

    // Find stats by a range of total users
    List<stats> findByTotalUsersBetween(int minUsers, int maxUsers);

    // Find stats by total providers
    List<stats> findByTotalProviders(int totalProviders);

    // Delete stats by serviceId
    void deleteByServiceId(int serviceId);

    stats findTopByOrderByIdDesc();

}
