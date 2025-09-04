package com.ofss.stockmanager.controller;

import com.ofss.stockmanager.entity.Customer;
import com.ofss.stockmanager.entity.User;
import com.ofss.stockmanager.service.CustomerService;
import com.ofss.stockmanager.service.UserService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:8000")
public class AuthController {

    private final UserService userService;
    private final CustomerService customerService;

    public AuthController(UserService userService, CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
    }

    // Sign up new USER (also creates Customer)
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User userRequest) {
        if ("ADMIN".equalsIgnoreCase(userRequest.getRole())) {
            // Admin creation is allowed directly
            return ResponseEntity.ok(userService.register(userRequest));
        } else {
            // Check if the User object contains the customer information
            if (userRequest.getCustomer() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                         .body("Customer information is required for signup.");
            }

            // Create a new Customer using the information from the userRequest's customer field
            Customer newCustomer = new Customer();
            newCustomer.setFirstName(userRequest.getCustomer().getFirstName());
            newCustomer.setLastName(userRequest.getCustomer().getLastName());
            newCustomer.setEmail(userRequest.getCustomer().getEmail());
            newCustomer.setPhoneNo(userRequest.getCustomer().getPhoneNo());

            // Save the customer
            Customer savedCustomer = customerService.create(newCustomer);

            // Attach the saved customer to the userRequest object
            userRequest.setRole("USER");  // Ensure role is set as "USER"
            userRequest.setCustomer(savedCustomer);  // Attach the saved customer to the user

            // Save the user with the attached customer
            return ResponseEntity.ok(userService.register(userRequest));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        User user = userService.login(loginRequest.getUsername(), loginRequest.getPassword(), loginRequest.getRole());
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials or role mismatch");
        }
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    
}
