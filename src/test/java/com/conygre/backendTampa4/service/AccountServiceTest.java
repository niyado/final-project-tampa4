package com.conygre.backendTampa4.service;

import com.conygre.backendTampa4.config.ApplicationConfig;
import com.conygre.backendTampa4.dao.AccountRepository;
import com.conygre.backendTampa4.entity.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ApplicationConfig applicationConfig;

    @Mock
    private BalanceLogService balanceLogService;

    @Mock Account account;


    @Test
    void getBalanceTest() {
        // arrange
        Account account = new Account("user", 0d);
        when(applicationConfig.getAccountName()).thenReturn("user");
        when(accountRepository.findById(applicationConfig.getAccountName())).thenReturn(Optional.of(account));

        // act
        Double balance = accountService.getBalance();

        // assert
        assertEquals(account.getBalance(), balance);
    }

    @Test
    void withdrawTest() {
        // arrange
        Account account = new Account("user", 100d);

        // act

        // assert

    }

    @Test
    void depositTest() {
    }

    @Test
    void getAccountTest() {
        when(accountRepository.findById(applicationConfig.getAccountName())).thenReturn(Optional.empty());

        Account account = accountService.getAccount();


    }

    @Test
    void modifyBalanceTest() {
    }
}