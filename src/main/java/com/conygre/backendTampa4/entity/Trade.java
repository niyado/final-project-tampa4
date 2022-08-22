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
    private String name;
    private int shares;
    private double price;
    private int timestampOfTrade;
    private String type;
    private String securityType;

    public Trade(){}

    public Trade(String symbol, String name, int shares, double price, int timestampOfTrade, String type, String securityType) {
        this.symbol = symbol;
        this.name = name;
        this.shares = shares;
        this.price = price;
        this.timestampOfTrade = timestampOfTrade;
        this.type = type;
        this.securityType = securityType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
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
