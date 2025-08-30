package com.ofss.stockmanager.service;

import com.ofss.stockmanager.entity.Customer;
import com.ofss.stockmanager.exception.ResourceNotFoundException;
import com.ofss.stockmanager.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public List<Customer> getAll() {
        return repo.findAll();
    }

    public Customer getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    public Customer create(Customer customer) {
        return repo.save(customer);
    }

    public Customer update(Long id, Customer customer) {
        Customer existing = getById(id);
        existing.setFirstName(customer.getFirstName());
        existing.setLastName(customer.getLastName());
        existing.setEmail(customer.getEmail());
        existing.setPhoneNo(customer.getPhoneNo());
        return repo.save(existing);
    }

    public Customer patch(Long id, Customer customer) {
        Customer existing = getById(id);
        if (customer.getFirstName() != null) existing.setFirstName(customer.getFirstName());
        if (customer.getLastName() != null) existing.setLastName(customer.getLastName());
        if (customer.getEmail() != null) existing.setEmail(customer.getEmail());
        if (customer.getPhoneNo() != null) existing.setPhoneNo(customer.getPhoneNo());
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.delete(getById(id));
    }
}
