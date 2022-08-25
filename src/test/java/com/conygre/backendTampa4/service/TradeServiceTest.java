package com.conygre.backendTampa4.service;

import com.conygre.backendTampa4.dao.TradeRepository;
import com.conygre.backendTampa4.entity.Account;
import com.conygre.backendTampa4.entity.Asset;
import com.conygre.backendTampa4.entity.Trade;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Disabled
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
        //when(accountService.getAccount()).thenReturn(account);
        when(assetService.assetExists(sampleTrade.getSymbol())).thenReturn(true);
        when(assetService.getAsset(sampleTrade.getSymbol())).thenReturn(asset);
        when(dao.save(sampleTrade)).thenReturn(sampleTrade);

        // act
        tradeService.addTrade(sampleTrade);

        // assert
        assertEquals(4, asset.getQuantity());
        verify(accountService, times(1)).modifyBalance(-total);
    }

    @Disabled
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

        // act
        tradeService.addTrade(sampleTrade);

        // assert
        verify(accountService, times(1)).modifyBalance(total);
    }
}
