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
    private AccountRepository accountRepository;
    @Autowired
    private ApplicationConfig applicationConfig;

    public Double getBalance() {
        Account account = Objects.requireNonNull(accountRepository.findById(applicationConfig.getAccountName())
                .orElse(null));
        return account.getBalance();
    }

    public Account getAccount()
    {
        Optional<Account> optionalUser = accountRepository.findById(applicationConfig.getAccountName());
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
