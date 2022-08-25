package com.conygre.backendTampa4.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "assets")
public class Asset implements Serializable {
    @Id
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

    public Asset(String symbol, String name, String assetType) {
        this.symbol = symbol;
        this.name = name;
        this.assetType = assetType;
        this.quantity = 0;
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

    public int getQuantity() {
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

    @Override
    public String toString() {
        return "Asset{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", assetType='" + assetType + '\'' +
                '}';
    }
}
