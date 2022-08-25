package com.conygre.backendTampa4.service;

import com.conygre.backendTampa4.dao.TradeRepository;
import com.conygre.backendTampa4.entity.Account;
import com.conygre.backendTampa4.entity.Asset;
import com.conygre.backendTampa4.entity.Trade;
import com.conygre.backendTampa4.exceptions.NotEnoughQuantityException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TradeServiceTest {
    @InjectMocks
    private TradeService tradeService;

    @Mock
    private TradeRepository dao;

    @Mock
    private AccountService accountService;

    @Mock
    private AssetService assetService;


    @Test
    void getTradeInfoTest() {
        // arrange
        Trade sampleTrade = new Trade(
                "AAPL", "Apple Inc.", 2, 10.00d, 1, "BUY", "STOCK"
        );
        when(dao.findById(1)).thenReturn(Optional.of(sampleTrade));

        // act
        Trade trade = tradeService.getTradeInfo(1);

        // assert
        assertEquals(sampleTrade, trade);
    }

    @Test
    void BuyMoreOfAssetTest() throws Exception{
        // arrange
        Trade sampleTrade = new Trade(
                "AAPL", "Apple Inc.", 2, 10.00d, 1, "BUY", "STOCK"
        );

        Asset asset = new Asset("AAPL", "Apple Inc.", 2, "STOCK");

        Account account = new Account("user", 100.00d);

        double total = sampleTrade.getShares() * sampleTrade.getPrice();
        when(accountService.getBalance()).thenReturn(100.00d);
        when(assetService.assetExists(sampleTrade.getSymbol())).thenReturn(false);
        when(dao.save(sampleTrade)).thenReturn(sampleTrade);

        // act
        tradeService.addTrade(sampleTrade);

        // assert
        verify(assetService, times(1)).modifyQuantity(sampleTrade.getSymbol(), sampleTrade.getShares());
        verify(accountService, times(1)).modifyBalance(-total);
    }

    @Test
    void sellAssetTest() throws Exception{
        // arrange
        Trade sampleTrade = new Trade(
                "AAPL", "Apple Inc.", 1, 10.00d, 1, "SELL", "STOCK"
        );

        Asset asset = new Asset("AAPL", "Apple Inc.", 2, "STOCK");
        double total = sampleTrade.getShares() * sampleTrade.getPrice();

        when(assetService.getAsset(sampleTrade.getSymbol())).thenReturn(asset);
        when(dao.save(sampleTrade)).thenReturn(sampleTrade);

        assertDoesNotThrow(() -> tradeService.addTrade(sampleTrade));
    }

    @Test
    void sellThrowsNotEnoughQuantityExceptionTest() throws NotEnoughQuantityException {
        // arrange
        Trade sampleTrade = new Trade(
                "AAPL", "Apple Inc.", 3, 10.00d, 1, "SELL", "STOCK"
        );

        Asset asset = new Asset("AAPL", "Apple Inc.", 2, "STOCK");

        double total = sampleTrade.getShares() * sampleTrade.getPrice();

        when(assetService.getAsset(sampleTrade.getSymbol())).thenReturn(asset);
        assertThrows(NotEnoughQuantityException.class, () -> tradeService.addTrade(sampleTrade));
    }

    @Test
    void getAllTradesTest() {
        // arrange
        List<Trade> tradesList = new ArrayList<Trade>();

        tradesList.add(new Trade("AAPL", "Apple Inc.", 3, 10.00d, 1, "BUY", "STOCK"));
        tradesList.add(new Trade("MSFT", "Microsoft", 4, 10.00d, 2, "BUY", "STOCK"));

        when(dao.findAll()).thenReturn(tradesList);

        // act
        tradeService.getAllTrades();

        // assert
        assertEquals(2, tradesList.size());
    }

    @Test
    void getAverageBuyPriceTest() {
        // arrange
        Trade sampleTrade = new Trade(
                "AAPL", "Apple Inc.", 3, 10.00d, 1, "SELL", "STOCK"
        );

        Double avgBuyPriceTest = 10d;

        when(dao.getAverageBuyPrice(sampleTrade.getSymbol())).thenReturn(avgBuyPriceTest);
        // act
        Double avgBuyPrice = tradeService.getAverageBuyPrice(sampleTrade.getSymbol());

        // assert
        assertEquals(avgBuyPriceTest, avgBuyPrice);
    }
}
