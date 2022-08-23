package com.conygre.backendTampa4.entity;

import javax.persistence.*;

@Entity
@Table(name = "balance_log")
public class BalanceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logId;
    private int timestamp;
    private double newBalance;

    public BalanceLog() {
    }

    public BalanceLog(int timestamp, double newBalance) {
        this.timestamp = timestamp;
        this.newBalance = newBalance;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public double getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(double newBalance) {
        this.newBalance = newBalance;
    }
}
