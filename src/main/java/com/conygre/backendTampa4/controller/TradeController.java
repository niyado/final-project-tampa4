package com.conygre.backendTampa4.controller;

import com.conygre.backendTampa4.entity.Asset;
import com.conygre.backendTampa4.entity.Trade;
import com.conygre.backendTampa4.service.AccountService;
import com.conygre.backendTampa4.service.AssetService;
import com.conygre.backendTampa4.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/portfolio/trades")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @Autowired
    private AssetService assetService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Trade> getTradeInfo(@PathVariable("id") Integer id) {
        Trade trade = tradeService.getTradeInfo(id);
        return new ResponseEntity<Trade>(trade, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    ResponseEntity<Iterable<Trade>> getAllTrades() {
        return new ResponseEntity<Iterable<Trade>>(tradeService.getAllTrades(), HttpStatus.OK);
    }

    @PostMapping(value = "/newTrade", consumes = "application/json")
    public void newTrade(@RequestBody Trade trade){
        tradeService.addTrade(trade);

        if (trade.getType().equalsIgnoreCase("BUY"))
        {

            if (assetService.assetExists(trade.getSymbol()))
            {
                Asset asset = assetService.getAsset(trade.getSymbol());
                asset.setQuantity(asset.getQuantity() + trade.getShares());
            }
            else
            {
                // still to be implemented
                // assetService.addAsset(new Asset(trade.getSymbol(), , trade.getShares(), trade.get));
            }
        }
        else
        {
            // still to be implemented
        }
    }
}
