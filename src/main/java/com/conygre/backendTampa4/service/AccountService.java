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
    private AccountRepository accountRepository;
    @Autowired
    private ApplicationConfig applicationConfig;

    public Double getBalance() {
        Account account = Objects.requireNonNull(accountRepository.findById(applicationConfig.getAccountName())
                .orElse(null));
        return account.getBalance();
    }
}
