package com.example.pawpalsysAdmin.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class statsService {
    @Autowired
    private statsRepository statsRepository;

    // Method to get current stats
    public stats getCurrentStats() {
        // Fetch the most recent stats based on the ID
        return statsRepository.findTopByOrderByIdDesc(); // Fetches the latest stats
    }
}
