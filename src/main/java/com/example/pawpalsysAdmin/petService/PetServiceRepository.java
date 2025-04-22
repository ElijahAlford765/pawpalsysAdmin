package com.example.pawpalsysAdmin.petService;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetServiceRepository extends JpaRepository<PetService, Long> {
    List<PetService> findByApproved(boolean approved);
}