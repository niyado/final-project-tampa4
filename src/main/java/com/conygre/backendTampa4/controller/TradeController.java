package com.conygre.backendTampa4.controller;

import com.conygre.backendTampa4.entity.Asset;
import com.conygre.backendTampa4.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/portfolio/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @GetMapping("/get/{symbol}")
    public ResponseEntity<Asset> getTradeInfo(@PathVariable("symbol") String symbol) {
        return null;
    }

    @GetMapping("/getAll")
    ResponseEntity<Iterable<Asset>> getAllTrades() {
        return null;
    }

    @PostMapping(value = "/newAsset", consumes = "application/json")
    public void newTrade(@RequestBody Asset asset){

    }
}
