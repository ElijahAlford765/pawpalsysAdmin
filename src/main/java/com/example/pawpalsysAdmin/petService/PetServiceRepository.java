package com.example.pawpalsysAdmin.petService;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetServiceRepository extends JpaRepository<PetService, Long> {
    List<PetService> findByApproved(boolean approved);
    List<PetService> findByStatus(String status);  // For example, to get pending services
    List<PetService> findByFlaggedTrue();  // To get flagged services
}