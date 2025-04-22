package com.example.pawpalsysAdmin.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository  extends JpaRepository<Review, Long> {
    List<Review> findByFlagged(boolean flagged);

    @Query("SELECT AVG(r.rating) FROM Review r")
    double getAverageRating();
}