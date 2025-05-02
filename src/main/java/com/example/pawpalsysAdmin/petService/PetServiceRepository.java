package com.example.pawpalsysAdmin.petService;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetServiceRepository extends JpaRepository<PetService, Integer> {
    List<PetService> findByApproved(boolean approved);
    List<PetService> findByStatus(String status);
    List<PetService> findByFlaggedTrue();
    List<PetService> findAll();
    long countByStatus(String status);  // Make sure this matches the case of your field

    Optional<PetService> findTopByOrderByPopularityDesc();
}