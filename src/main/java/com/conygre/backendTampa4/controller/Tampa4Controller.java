package com.conygre.backendTampa4.controller;

import com.conygre.backendTampa4.entity.Asset;
import com.conygre.backendTampa4.service.Tampa4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;



import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/portfolio")
public class Tampa4Controller {

    @Autowired
    private Tampa4Service tampa4Service;

    @GetMapping("/get/{symbol}")
    public ResponseEntity<Asset> getAssetInfo(@PathVariable("symbol") String symbol) {
        return new ResponseEntity<Asset>(tampa4Service.getAssetInfo(symbol), HttpStatus.OK);

    }

    @GetMapping("/getAll")
    ResponseEntity<Iterable<Asset>> getAllAssets() {
        return new ResponseEntity<Iterable<Asset>>(tampa4Service.getAllAssets(), HttpStatus.OK);
    }
}
