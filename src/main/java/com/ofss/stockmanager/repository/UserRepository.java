package com.ofss.stockmanager.repository;

import com.ofss.stockmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by username + password (for login)
    User findByUsernameAndPassword(String username, String password);

    // Find user only by username (useful for checking duplicates)
    User findByUsername(String username);
}
