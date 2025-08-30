package com.ofss.stockmanager.service;

import com.ofss.stockmanager.entity.Stock;
import com.ofss.stockmanager.exception.ResourceNotFoundException;
import com.ofss.stockmanager.repository.StockRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StockService {

    private final StockRepository repo;

    public StockService(StockRepository repo) {
        this.repo = repo;
    }

    public List<Stock> getAll() {
        return repo.findAll();
    }

    public Stock getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found with id: " + id));
    }

    public Stock create(Stock stock) {
        return repo.save(stock);
    }

    public Stock update(Long id, Stock stock) {
        Stock existing = getById(id);
        existing.setStockName(stock.getStockName());
        existing.setStockPrice(stock.getStockPrice());
        existing.setStockVolume(stock.getStockVolume());
        existing.setListingPrice(stock.getListingPrice());
        existing.setListedDate(stock.getListedDate());
        existing.setListedExchange(stock.getListedExchange());
        return repo.save(existing);
    }

    public Stock patch(Long id, Stock stock) {
        Stock existing = getById(id);
        if (stock.getStockName() != null) existing.setStockName(stock.getStockName());
        if (stock.getStockPrice() != null) existing.setStockPrice(stock.getStockPrice());
        if (stock.getStockVolume() != null) existing.setStockVolume(stock.getStockVolume());
        if (stock.getListingPrice() != null) existing.setListingPrice(stock.getListingPrice());
        if (stock.getListedDate() != null) existing.setListedDate(stock.getListedDate());
        if (stock.getListedExchange() != null) existing.setListedExchange(stock.getListedExchange());
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.delete(getById(id));
    }
}
