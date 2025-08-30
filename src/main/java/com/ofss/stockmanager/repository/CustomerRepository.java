package com.ofss.stockmanager.repository;

import com.ofss.stockmanager.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
