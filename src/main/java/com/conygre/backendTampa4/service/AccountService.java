package com.conygre.backendTampa4.service;


import com.conygre.backendTampa4.config.ApplicationConfig;
import com.conygre.backendTampa4.dao.AccountRepository;
import com.conygre.backendTampa4.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountService {

    @Autowired
    private AccountRepository dao;
    @Autowired
    private ApplicationConfig applicationConfig;


    private Account getAccount(String accountName) {
        return Objects.requireNonNull(dao.findById(applicationConfig.getAccountName()).orElse(null));
    }

    public Double getBalance() {
        return getAccount(applicationConfig.getAccountName()).getBalance();
    }

    public void withdraw(Double amount) {
        Account account = getAccount(applicationConfig.getAccountName());
        account.setBalance(account.getBalance() - amount);
        dao.save(account);
    }

    public void deposit(Double amount) {
        Account account = getAccount(applicationConfig.getAccountName());
        account.setBalance(account.getBalance() + amount);
        dao.save(account);
    }
}
