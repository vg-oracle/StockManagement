package com.ofss.stockmanager.repository;

import com.ofss.stockmanager.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    
    List<Transaction> findByCustomer_CustomerId(Long customerId);

}
