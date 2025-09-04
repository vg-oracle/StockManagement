package com.ofss.stockmanager.service;

import com.ofss.stockmanager.entity.User;
import com.ofss.stockmanager.repository.UserRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;
    

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User register(User user) {
        return repo.save(user);
    }

    public User login(String username, String password, String role) {
        User user = repo.findByUsernameAndPassword(username, password);
        
        if (user != null) {
            // Check if the role matches
            if (!user.getRole().equalsIgnoreCase(role)) {
                return null; // Role mismatch, return null to indicate invalid login attempt
            }
            return user;
        } else {
            return null; // Invalid credentials
        }
    }

    
    public List<User> getAllUsers() {
        return repo.findAll();
    }

}
