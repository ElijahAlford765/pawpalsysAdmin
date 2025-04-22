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

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }

    @GetMapping("/moderate-services")
    public String moderateServices(Model model) {
        model.addAttribute("services", petServiceRepository.findAll());
        return "moderate-services";
    }
    @RequestMapping("/services")
    public String getServices(Model model) {
        // Get pending services and flagged services
        model.addAttribute("services", petServiceRepository.findByStatus("PENDING"));
        model.addAttribute("flaggedServices", petServiceRepository.findByFlaggedTrue());
        return "moderate-services";  // Points to the HTML page you provided
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
        return "redirect:/admin/services";
    }

    // Delete service
    @PostMapping("/services/delete/{id}")
    public String deleteService(@PathVariable Long id) {
        petServiceRepository.deleteById(id);
        return "redirect:/admin/services";
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

    // View statistics
    @GetMapping("/stats")
    public String viewStats(Model model) {
        // Active user counts per month (update according to your actual method of counting)
        int activeUsersJan = userRepository.countByActiveAndMonth(1); // January
        int activeUsersFeb = userRepository.countByActiveAndMonth(2); // February
        int activeUsersMar = userRepository.countByActiveAndMonth(3); // March
        int activeUsersApr = userRepository.countByActiveAndMonth(4); // April

        model.addAttribute("activeUsersJan", activeUsersJan);
        model.addAttribute("activeUsersFeb", activeUsersFeb);
        model.addAttribute("activeUsersMar", activeUsersMar);
        model.addAttribute("activeUsersApr", activeUsersApr);

        // Average rating (assuming the method exists)
        double overallAvg = reviewRepository.getAverageRating();
        model.addAttribute("overallAvg", overallAvg);

        return "moderate-stats";
    }

}

