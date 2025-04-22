package com.example.pawpalsysAdmin.SysAdmin;

import com.example.pawpalsysAdmin.user.User;
import com.example.pawpalsysAdmin.user.UserRepository;
import com.example.pawpalsysAdmin.petService.PetService;
import com.example.pawpalsysAdmin.petService.PetServiceRepository;
import com.example.pawpalsysAdmin.review.Review;
import com.example.pawpalsysAdmin.review.ReviewRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/admin")
public class sysAdminController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetServiceRepository petServiceRepository;

    @Autowired
    private ReviewRepository reviewRepository;


    @Autowired
    public sysAdminController(UserRepository userRepository, PetServiceRepository petServiceRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.petServiceRepository = petServiceRepository;
        this.reviewRepository = reviewRepository;
    }
    // Dashboard
    /*
    http://localhost:8081/admin/dashboard
     */

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        return "dashboard";
    }

    // List unapproved services
    @GetMapping("/services/pending")
    public String pendingServices(Model model) {
        List<PetService> pending = petServiceRepository.findByApproved(false);
        model.addAttribute("services", pending);
        model.addAttribute("totalUsers", userRepository.count());
        model.addAttribute("totalServices", petServiceRepository.count());
        model.addAttribute("totalReviews", reviewRepository.count());
        return "moderate-services";
    }

    // Approve service
    @PostMapping("/services/approve/{id}")
    public String approveService(@PathVariable Long id) {
        PetService service = petServiceRepository.findById(id).orElse(null);
        if (service != null) {
            service.setApproved(true);
            petServiceRepository.save(service);
        }
        return "moderate-services";
    }

    // Delete service
    @PostMapping("/services/delete/{id}")
    public String deleteService(@PathVariable Long id) {
        petServiceRepository.deleteById(id);
        return "redirect:/admin/services/pending";
    }

    // List flagged reviews
    @GetMapping("/reviews/flagged")
    public String flaggedReviews(Model model) {
        List<Review> flagged = reviewRepository.findByFlagged(true);
        model.addAttribute("reviews", flagged);
        return "moderate-services";
    }

    // Delete review
    @PostMapping("/reviews/delete/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewRepository.deleteById(id);
        return "redirect:/admin/reviews/flagged";
    }

    // Manage users
    @GetMapping("/users")
    public String manageUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());

        return "manage-users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/stats")
    public String viewStats(Model model) {
        // Query for active users per month (adjust this to your actual data retrieval method)
        int activeUsersJan = userRepository.countByActiveAndMonth(1); // For January
        int activeUsersFeb = userRepository.countByActiveAndMonth(2); // For February
        int activeUsersMar = userRepository.countByActiveAndMonth(3); // For March
        int activeUsersApr = userRepository.countByActiveAndMonth(4); // For April

        // Add the data to the model to be used in the frontend
        model.addAttribute("activeUsersJan", activeUsersJan);
        model.addAttribute("activeUsersFeb", activeUsersFeb);
        model.addAttribute("activeUsersMar", activeUsersMar);
        model.addAttribute("activeUsersApr", activeUsersApr);

        // You can also add overall average rating if applicable
        double overallAvg = reviewRepository.getAverageRating(); // Assuming you have such a method
        model.addAttribute("overallAvg", overallAvg);

        return "moderate-stats"; // This should correspond to your `stats.ftlh` template
    }

}

