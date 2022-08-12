package com.conygre.backendTampa4.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tradeId;

    private int shares;
    private double price;
    private int timestampOfTrade;
    private String type;

    public Trade(int shares, double price, int timestampOfTrade, String type) {
        this.shares = shares;
        this.price = price;
        this.timestampOfTrade = timestampOfTrade;
        this.type = type;
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTimestampOfTrade() {
        return timestampOfTrade;
    }

    public void setTimestampOfTrade(int timestampOfTrade) {
        this.timestampOfTrade = timestampOfTrade;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
