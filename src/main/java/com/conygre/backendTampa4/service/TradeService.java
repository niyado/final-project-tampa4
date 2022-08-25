package com.conygre.backendTampa4.service;

import com.conygre.backendTampa4.dao.TradeRepository;
import com.conygre.backendTampa4.entity.Asset;
import com.conygre.backendTampa4.entity.Trade;
import com.conygre.backendTampa4.exceptions.InsufficientFundsException;
import com.conygre.backendTampa4.exceptions.NotEnoughQuantityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    private TradeRepository dao;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AssetService assetService;

    public Trade getTradeInfo(Integer id) {
        Optional<Trade> optionalTrade = dao.findById(id);
        if (optionalTrade.isPresent())
            return optionalTrade.get();
        return null;
    }

    public Iterable<Trade> getAllTrades() {
        return dao.findAll();
    }

    @Transactional
    public void addTrade(Trade trade) throws NotEnoughQuantityException, InsufficientFundsException {

        double total = trade.getShares() * trade.getPrice();

        if (trade.getType().equalsIgnoreCase("BUY"))
        {
            if (accountService.getBalance() >= total)
            {
                if (!assetService.assetExists(trade.getSymbol()))
                {
                    assetService.addAsset(new Asset(trade.getSymbol(), trade.getName(), trade.getSecurityType()));
                }

                dao.save(trade);
                assetService.modifyQuantity(trade.getSymbol(), trade.getShares());
                accountService.modifyBalance(-total);
            }
            else
            {
                throw new InsufficientFundsException("insufficient funds");
            }
        }
        else if (trade.getType().equalsIgnoreCase("SELL"))
        {
            Asset asset = assetService.getAsset(trade.getSymbol());
            Integer owned = asset.getQuantity();
            if (owned < trade.getShares() || owned == null)
            {
                throw new NotEnoughQuantityException("user doesn't own enough of this symbol");
            }
            else
            {
                dao.save(trade);
                assetService.modifyQuantity(trade.getSymbol(), -trade.getShares());
                accountService.modifyBalance(total);
            }
        }
    }

    public Double getAverageBuyPrice(String symbol) {
        return dao.getAverageBuyPrice(symbol);
    }
}
