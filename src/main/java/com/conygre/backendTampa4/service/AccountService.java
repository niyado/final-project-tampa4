package com.conygre.backendTampa4.service;


import com.conygre.backendTampa4.config.ApplicationConfig;
import com.conygre.backendTampa4.dao.AccountRepository;
import com.conygre.backendTampa4.entity.Account;
import com.conygre.backendTampa4.entity.BalanceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository dao;
    @Autowired
    private ApplicationConfig applicationConfig;
    @Autowired
    private BalanceLogService balanceLogService;

    public Double getBalance() {
        return getAccount().getBalance();
    }

    public void withdraw(Double amount) {
        this.modifyBalance(-amount);
    }

    public void deposit(Double amount) {
        this.modifyBalance(amount);
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
        int unixTime = (int) Instant.now().getEpochSecond();
        balanceLogService.logBalanceChange(new BalanceLog(unixTime, this.getBalance()));
    }

}
