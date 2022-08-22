package com.conygre.backendTampa4.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "trades")
public class Trade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tradeId;

    private String symbol;
    private int shares;
    private double price;
    private int timestampOfTrade;
    private String type;

    @ManyToOne
    Asset asset;

    public Trade(){}

    public Trade(int shares, String symbol, double price, int timestampOfTrade, String type) {
        this.symbol = symbol;
        this.shares = shares;
        this.price = price;
        this.timestampOfTrade = timestampOfTrade;
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
