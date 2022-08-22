package com.conygre.backendTampa4.controller;


import com.conygre.backendTampa4.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("api/portfolio/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("getBalance")
    public ResponseEntity<Double> getBalance() {
        return new ResponseEntity<Double>(accountService.getBalance(), HttpStatus.OK);
    }

}
