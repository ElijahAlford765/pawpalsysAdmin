package com.example.pawpalsysAdmin.stats;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
public class stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Long providerId;
    public int totalCustomers;
    public double avgRating;
    public Long reviewId;
    public Long serviceId;
    public Long petId;
    public Long bookingId;


    public stats(Long id, Long providerId, int totalCustomers, double avgRating, Long reviewId, Long serviceId, Long petId, Long bookingId) {
        this.id = id;
        this.providerId = providerId;
        this.totalCustomers = totalCustomers;
        this.avgRating = avgRating;
        this.reviewId = reviewId;
        this.serviceId = serviceId;
        this.petId = petId;
        this.bookingId = bookingId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public int getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "Stat [id=" + id + ", providerId=" + providerId + ", totalCustomers=" + totalCustomers + ", avgRating=" + avgRating + ", reviewId=" + reviewId + ", serviceId=" + serviceId + ", petId=" + petId + ", bookingId=" + bookingId + "]";
    }

}
