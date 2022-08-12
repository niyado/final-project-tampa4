package com.conygre.backendTampa4.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Asset {
    @Id
    private String symbol;

    private String name;
    private double quantity;

    public Asset() {}

    public Asset(String symbol, String name, double quantity) {
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
