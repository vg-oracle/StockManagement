package com.ofss.stockmanager.repository;

import com.ofss.stockmanager.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {}
