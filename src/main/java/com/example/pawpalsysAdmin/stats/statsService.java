package com.example.pawpalsysAdmin.stats;

import com.example.pawpalsysAdmin.review.ReviewRepository;
import com.example.pawpalsysAdmin.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class statsService {

    public List<stats> getstats() {
        List<stats> stats = new ArrayList<>();
        // Populate this from your repositories or logic
        // Example placeholder data:
        stats.add(new stats(1L, 101L, 5, 4.2, 201L, 301L, 401L, 501L));
        return stats;
    }
}
