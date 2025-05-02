package com.example.pawpalsysAdmin.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Get all users.(GET)
     * http://localhost:8080/users
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Get user by ID.(GET)
     * http://localhost:8080/users/{id}
     */
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Create a new user.(POST)
     * http://localhost:8080/users
     *
     * Request body:
     {
     "username": "john_doe",
     "email": "john.doe@example.com",
     "password": "password123",
     "firstName": "John",
     "lastName": "Doe",
     "role": "customer",
     "status": "active",
     "createdAt": "2025-03-31T12:00:00",
     "updatedAt": "2025-03-31T12:00:00"
     }
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    /**
     * Update an existing user.(PUT)
     * http://localhost:8080/users/{id}
     *
     * Request body:
     * {
     *   "username": "john_doe_updated",
     *   "email": "john.doe.updated@example.com",
     *   "password": "newpassword123",
     *   "firstName": "John",
     *   "lastName": "Doe",
     *   "role": "admin",
     *   "status": "active",
     *   "updatedAt": "2025-04-01T12:00:00"
     * }
     */
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(userId, userDetails);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Delete a user by ID.(DELETE)
     * http://localhost:8080/users/{id}
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
