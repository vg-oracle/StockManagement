package com.ofss.stockmanager.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "stock_name", nullable = false, length = 30)
    private String stockName;

    @Column(name = "stock_price")
    private Double stockPrice;

    @Column(name = "stock_volume")
    private Integer stockVolume;

    @Column(name = "listing_price")
    private Double listingPrice;

    @Temporal(TemporalType.DATE)
    @Column(name = "listed_date")
    private Date listedDate;

    @Column(name = "listed_exchange", length = 3)
    private String listedExchange;

    public Stock() {}

    // Getters and Setters
    public Long getStockId() { return stockId; }
    public void setStockId(Long stockId) { this.stockId = stockId; }
    public String getStockName() { return stockName; }
    public void setStockName(String stockName) { this.stockName = stockName; }
    public Double getStockPrice() { return stockPrice; }
    public void setStockPrice(Double stockPrice) { this.stockPrice = stockPrice; }
    public Integer getStockVolume() { return stockVolume; }
    public void setStockVolume(Integer stockVolume) { this.stockVolume = stockVolume; }
    public Double getListingPrice() { return listingPrice; }
    public void setListingPrice(Double listingPrice) { this.listingPrice = listingPrice; }
    public Date getListedDate() { return listedDate; }
    public void setListedDate(Date listedDate) { this.listedDate = listedDate; }
    public String getListedExchange() { return listedExchange; }
    public void setListedExchange(String listedExchange) { this.listedExchange = listedExchange; }
}
