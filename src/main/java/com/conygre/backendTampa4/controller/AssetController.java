package com.conygre.backendTampa4.controller;

import com.conygre.backendTampa4.entity.Asset;
import com.conygre.backendTampa4.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;

@RestController
@CrossOrigin
@RequestMapping("/api/portfolio/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping("/get/{symbol}")
    public ResponseEntity<Asset> getAssetInfo(@PathVariable("symbol") String symbol) {
        Asset asset = assetService.getAssetInfo(symbol);
        return new ResponseEntity<Asset>(asset, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    ResponseEntity<Iterable<Asset>> getAllAssets() {
        return new ResponseEntity<Iterable<Asset>>(assetService.getAllAssets(), HttpStatus.OK);
    }

    @PostMapping(value = "/newAsset", consumes = "application/json")
    public void addStock(@RequestBody Asset asset){
        assetService.addAsset(asset);
    }

   @GetMapping("/findByType/{type}")
    public ResponseEntity<Iterable<Asset>> findByType(@PathVariable("type") String type) {
        return new ResponseEntity<Iterable<Asset>>(assetService.findByType(type), HttpStatus.OK);
    }

    @PutMapping("/edit/{symbol}")
    public ResponseEntity<String> getAllAssets(@PathVariable("type") Asset asset) {
        return new ResponseEntity<String>(String.format("Updated %s", asset.getSymbol()), HttpStatus.OK);
    }

    @DeleteMapping("/{symbol}")
    public void deleteAsset(@PathVariable String symbol) {
        assetService.deleteAsset(symbol);
    }
}
