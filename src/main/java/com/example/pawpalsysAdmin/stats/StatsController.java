package com.example.pawpalsysAdmin.stats;


import com.example.pawpalsysAdmin.petService.PetService;
import com.example.pawpalsysAdmin.petService.PetServiceRepository;
import com.example.pawpalsysAdmin.petService.PetServiceService;
import com.example.pawpalsysAdmin.review.ReviewRepository;
import com.example.pawpalsysAdmin.review.ReviewService;
import com.example.pawpalsysAdmin.user.UserRepository;
import com.example.pawpalsysAdmin.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Controller
@RequestMapping("/stats")
public class StatsController {
    private final UserRepository userRepository;
    private final PetServiceRepository petServiceRepository;
    private final ReviewRepository reviewRepository;
    private final StatsService statsService;

    @Autowired
    public StatsController(UserRepository userRepository, PetServiceRepository petServiceRepository,
                           ReviewRepository reviewRepository, StatsService statsService) {
        this.userRepository = userRepository;
        this.petServiceRepository = petServiceRepository;
        this.reviewRepository = reviewRepository;
        this.statsService = statsService;
    }



    @GetMapping
    public List<Stats> getAllStatistics(Model model) {
        return statsService.getAllStatistics();
    }

    @GetMapping("/live")
    public Stats getLiveStats(Model model) {
        return statsService.generateLiveStats();
    }



    @DeleteMapping("/{id}")
    public void deleteStatisticsById(@PathVariable int id) {
        statsService.deleteStatisticsById(id);
    }

    // ====== Admin Dashboard Views ======

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }

    @GetMapping("/moderate-services")
    public String moderateServices(Model model) {
        model.addAttribute("services", petServiceRepository.findAll());
        model.addAttribute("pendingServices", petServiceRepository.findByStatus("PENDING"));
        model.addAttribute("flaggedServices", petServiceRepository.findByFlaggedTrue());
        model.addAttribute("users", userRepository.findAll());
        return "moderate-services";
    }

    @PostMapping("/services/approve/{id}")
    public String approveService(@PathVariable int id) {
        petServiceRepository.findById(id).ifPresent(service -> {
            service.setApproved(true);
            service.setStatus("APPROVED");
            petServiceRepository.save(service);
        });
        return "redirect:/stats/moderate-services";
    }

    @PostMapping("/services/delete/{id}")
    public String deleteService(@PathVariable int id) {
        petServiceRepository.deleteById(id);
        return "redirect:/stats/moderate-services";
    }


    @GetMapping("/reviews/flagged")
    public String flaggedReviews(Model model) {
        model.addAttribute("reviews", reviewRepository.findByFlagged(true));
        return "flagged-reviews";
    }

    @PostMapping("/reviews/delete/{id}")
    public String deleteReview(@PathVariable int id) {
        reviewRepository.deleteById(id);
        return "redirect:/stats/reviews/flagged";
    }

    @GetMapping("/users")
    public String manageUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "manage-users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return "redirect:/stats/users";
    }

    @GetMapping("/view")
    public String viewStats(Model model) {
        model.addAttribute("activeUsersJan", userRepository.countByActiveAndMonth(1));
        model.addAttribute("activeUsersFeb", userRepository.countByActiveAndMonth(2));
        model.addAttribute("activeUsersMar", userRepository.countByActiveAndMonth(3));
        model.addAttribute("activeUsersApr", userRepository.countByActiveAndMonth(4));
        model.addAttribute("overallAvg", reviewRepository.getAverageRating());
        model.addAttribute("statsList", statsService.getAllStatistics());
        model.addAttribute("title", "Usage Statistics");
        return "moderate-stats";
    }
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PetServiceRepository petServiceRepository;
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @Autowired
//    private StatsService StatsService;
//
//    private final UserService userService;
//    private final PetServiceService serviceService;
//    private final ReviewService reviewService;
//    private final StatsService statsService;
//
//    @Autowired
//    public StatsController(UserService userService, PetServiceService serviceService,
//                           ReviewService reviewService, StatsService statsService) {
//        this.userService = userService;
//        this.serviceService = serviceService;
//        this.reviewService = reviewService;
//        this.statsService = statsService;
//    }
//
//    // Get all historical stats
//    @GetMapping
//    public List<Stats> getAllStatistics() {
//        return statsService.getAllStatistics();
//    }
//
//    // View live stats (calculated on demand)
//    @GetMapping("/live")
//    public Stats getLiveStats() {
//        return statsService.generateLiveStats();
//    }
//
//    // Save live snapshot to DB
//    @PostMapping("/snapshot")
//    public void saveStatsSnapshot() {
//        statsService.saveCurrentStatsSnapshot();
//    }
//
//    // Delete by ID
//    @DeleteMapping("/{id}")
//    public void deleteStatisticsById(@PathVariable int id) {
//        statsService.deleteStatisticsById(id);
//    }
//
//
//
//    // Dashboard View
//    @GetMapping("/dashboard")
//    public String dashboard(Model model) {
//        return "dashboard";
//    }
//
//    // Moderate Services View
//    @GetMapping("/moderate-services")
//    public String moderateServices(Model model) {
//        List<PetService> services = petServiceRepository.findAll();
//        model.addAttribute("services", petServiceRepository.findAll());
//        model.addAttribute("pendingServices", petServiceRepository.findByStatus("PENDING"));
//        model.addAttribute("flaggedServices", petServiceRepository.findByFlaggedTrue());
//        return "moderate-services";
//    }
//
//    // Pending Services List
//    @GetMapping("/services/pending")
//    public String pendingServices(Model model) {
//        List<PetService> pending = petServiceRepository.findByApproved(false);
//
//        model.addAttribute("totalUsers", userRepository.count());
//        model.addAttribute("totalServices", petServiceRepository.count());
//        model.addAttribute("totalReviews", reviewRepository.count());
//        return "moderate-services";
//    }
//
//    // Approve Service
//    @PostMapping("/services/approve/{id}")
//    public String approveService(@PathVariable int id) {
//        petServiceRepository.findById(id).ifPresent(service -> {
//            service.setApproved(true);
//            service.setStatus("APPROVED");
//            petServiceRepository.save(service);
//        });
//        return "redirect:/stats/moderate-services";
//    }
//
//    // Delete Service
//    @PostMapping("/services/delete/{id}")
//    public String deleteService(@PathVariable int id) {
//        petServiceRepository.deleteById(id);
//        return "redirect:/admin/moderate-services";
//    }
//
//    // Flagged Reviews View
//    @GetMapping("/reviews/flagged")
//    public String flaggedReviews(Model model) {
//        model.addAttribute("reviews", reviewRepository.findByFlagged(true));
//        return "flagged-reviews";
//    }
//
//    // Delete Review
//    @PostMapping("/reviews/delete/{id}")
//    public String deleteReview(@PathVariable int id) {
//        reviewRepository.deleteById(id);
//        return "redirect:/admin/reviews/flagged";
//    }
//
//    // Manage Users View
//    @GetMapping("/users")
//    public String manageUsers(Model model) {
//        model.addAttribute("users", userRepository.findAll());
//        return "manage-users";
//    }
//
//    // Delete User
//    @PostMapping("/users/delete/{id}")
//    public String deleteUser(@PathVariable int id) {
//        userRepository.deleteById(id);
//        return "redirect:/admin/users";
//    }
//
//    // View Stats Page (Historical + Live)
//    @GetMapping("/stats")
//    public String viewStats(Model model) {
//        // Monthly active users (custom logic assumed)
//        model.addAttribute("activeUsersJan", userRepository.countByActiveAndMonth(1));
//        model.addAttribute("activeUsersFeb", userRepository.countByActiveAndMonth(2));
//        model.addAttribute("activeUsersMar", userRepository.countByActiveAndMonth(3));
//        model.addAttribute("activeUsersApr", userRepository.countByActiveAndMonth(4));
//
//        model.addAttribute("overallAvg", reviewRepository.getAverageRating());
//
//
//        model.addAttribute("statsList", statsService.getAllStatistics());
//        model.addAttribute("title", "Usage Statistics");
//
//        return "moderate-stats";
    }

/*
    @GetMapping("/all")
    public String getAllStatistics(Model model) {
        model.addAttribute("statisticsList", statsService.getAllStatistics());
        model.addAttribute("title", "All Statistics");
        return "statistics/statistics-list";
    }

    @GetMapping("/{id}")
    public String getStatisticsById(@PathVariable int id, Model model) {
        model.addAttribute("statistics", statsService.getStatisticsById(id));
        model.addAttribute("title", "Statistics ID: " + id);
        return "statistics/statistics-details";
    }

    @PostMapping("/new")
    public String addNewStatistics(@ModelAttribute Stats statistics) {
        statsService.addNewStatistics(statistics);
        return "redirect:/statistics/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteStatisticsById(@PathVariable int id) {
        statsService.deleteStatisticsById(id);
        return "redirect:/statistics/all";
    }

    @GetMapping("/provider/{providerId}")
    public String getStatisticsByProvider(@PathVariable int providerId, Model model) {
        model.addAttribute("statistics", statsService.getStatisticsByProvider(providerId));
        model.addAttribute("title", "Provider ID: " + providerId);
        return "statistics/statistics-provider";
    }

 */


