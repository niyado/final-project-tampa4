package com.conygre.backendTampa4.controller;

import com.conygre.backendTampa4.entity.BalanceLog;
import com.conygre.backendTampa4.service.BalanceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/portfolio/balanceLog")
public class BalanceLogController {

    @Autowired
    private BalanceLogService balanceLogService;

    @GetMapping("/getAll")
    ResponseEntity<Iterable<BalanceLog>> getAllLoggedChanges() {
        return new ResponseEntity<Iterable<BalanceLog>>(balanceLogService.getAll(), HttpStatus.OK);
    }
}
