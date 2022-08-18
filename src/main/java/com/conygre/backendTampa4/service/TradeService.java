package com.conygre.backendTampa4.service;

import com.conygre.backendTampa4.dao.TradeRepository;
import com.conygre.backendTampa4.entity.Asset;
import com.conygre.backendTampa4.entity.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    private TradeRepository dao;

    public Trade getTradeInfo(Integer id) {
        Optional<Trade> optionalTrade = dao.findById(id);
        if (optionalTrade.isPresent())
            return optionalTrade.get();
        return null;
    }

    public Iterable<Trade> getAllTrades() {
        return dao.findAll();
    }
}
