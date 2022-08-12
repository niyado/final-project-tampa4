package com.conygre.backendTampa4.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock extends Asset{
    public Stock(String symbol, String name, double quantity) {
        super(symbol, name, quantity);
    }
}
