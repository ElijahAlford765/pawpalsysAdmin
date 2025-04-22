package com.example.pawpalsysAdmin.Stats;

import jakarta.persistence.*;

@Entity
@Table(name = "stats")
public class stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "service_id")
    private int serviceId;
    private int totalUsers;
    private int activeUsers;
    private int totalProviders;
    private int activeProviders;
    private int totalBookings;
    private int totalReviews;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public int getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(int activeUsers) {
        this.activeUsers = activeUsers;
    }

    public int getTotalProviders() {
        return totalProviders;
    }

    public void setTotalProviders(int totalProviders) {
        this.totalProviders = totalProviders;
    }

    public int getActiveProviders() {
        return activeProviders;
    }

    public void setActiveProviders(int activeProviders) {
        this.activeProviders = activeProviders;
    }

    public int getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(int totalBookings) {
        this.totalBookings = totalBookings;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "id=" + id +
                ", serviceid=" + serviceId +
                ", totalUsers=" + totalUsers +
                ", activeUsers=" + activeUsers +
                ", totalProviders=" + totalProviders +
                ", activeProviders=" + activeProviders +
                ", totalBookings=" + totalBookings +
                ", totalReviews=" + totalReviews +
                '}';

    }
}
