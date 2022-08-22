package com.conygre.backendTampa4.service;


import com.conygre.backendTampa4.config.ApplicationConfig;
import com.conygre.backendTampa4.dao.AccountRepository;
import com.conygre.backendTampa4.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

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

    public Account getAccount()
    {
        Optional<Account> optionalUser = dao.findById(applicationConfig.getAccountName());
        if (optionalUser.isPresent())
            return optionalUser.get();
        else
            throw new IllegalStateException("account does not exist");
    }

    @Transactional
    public void modifyBalance(double value)
    {
        Account myAccount = getAccount();
        double currentBalance = myAccount.getBalance();
        myAccount.setBalance(currentBalance + value);
    }

}
