package com.ofss.stockmanager.service;

import com.ofss.stockmanager.entity.Transaction;
import com.ofss.stockmanager.exception.ResourceNotFoundException;
import com.ofss.stockmanager.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    public List<Transaction> getAll() {
        return repo.findAll();
    }

    public Transaction getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
    }

    public Transaction create(Transaction tx) {
        return repo.save(tx);
    }

    public Transaction update(Long id, Transaction tx) {
        Transaction existing = getById(id);
        existing.setCustomer(tx.getCustomer());
        existing.setStock(tx.getStock());
        existing.setType(tx.getType());
        existing.setTransactionPrice(tx.getTransactionPrice());
        existing.setTransactionDate(tx.getTransactionDate());
        return repo.save(existing);
    }

    public Transaction patch(Long id, Transaction tx) {
        Transaction existing = getById(id);
        if (tx.getCustomer() != null) existing.setCustomer(tx.getCustomer());
        if (tx.getStock() != null) existing.setStock(tx.getStock());
        if (tx.getType() != null) existing.setType(tx.getType());
        if (tx.getTransactionPrice() != null) existing.setTransactionPrice(tx.getTransactionPrice());
        if (tx.getTransactionDate() != null) existing.setTransactionDate(tx.getTransactionDate());
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.delete(getById(id));
    }

    // New method to get transactions by customerId
    public List<Transaction> getTransactionsByCustomerId(Long customerId) {
        List<Transaction> transactions = repo.findByCustomer_CustomerId(customerId);
        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("No transactions found for customer with ID: " + customerId);
        }
        return transactions;
    }

}
