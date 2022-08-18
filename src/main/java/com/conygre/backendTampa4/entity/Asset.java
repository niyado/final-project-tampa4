package com.conygre.backendTampa4.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "assets")
public class Asset implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String symbol;
    private String name;
    private int quantity;
    private String assetType;

    public Asset() {}

    public Asset(String symbol, String name, int quantity, String assetType) {
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.assetType = assetType;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }
}
