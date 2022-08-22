package com.conygre.backendTampa4.service;

import com.conygre.backendTampa4.dao.BalanceLogRepository;
import com.conygre.backendTampa4.entity.BalanceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class BalanceLogService {

    @Autowired
    private BalanceLogRepository dao;

    public Collection<BalanceLog> getAll() {
        return dao.findAll();
    }

    @Transactional
    public void logBalanceChange(BalanceLog balanceLog) {
        dao.save(balanceLog);
    }
}
