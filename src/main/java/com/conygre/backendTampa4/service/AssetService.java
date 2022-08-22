package com.conygre.backendTampa4.service;


import com.conygre.backendTampa4.dao.AssetRepository;
import com.conygre.backendTampa4.entity.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AssetService {

    @Autowired
    private AssetRepository dao;

    public Collection<Asset> getAllAssets() {
        return dao.findAll();
    }

    public Asset getAssetInfo(String symbol) {
        Optional<Asset> optionalAsset = dao.findById(symbol);
        if (optionalAsset.isPresent())
            return optionalAsset.get();
        return null;
    }

    public void addAsset(Asset asset) {
        dao.save(asset);
    }

    public Collection<Asset> findByType(String type) {
        return dao.findByAssetType(type);
    }

    public void deleteAsset(String symbol) {
        dao.deleteById(symbol);
    }

    public boolean assetExists(String symbol)
    {
        Optional<Asset> assetOptional = dao.findById(symbol);
        if (assetOptional.isEmpty())
            return false;
        else
            return true;
    }

    public Asset getAsset(String symbol)
    {
        Optional<Asset> assetOptional = dao.findById(symbol);
        if (assetOptional.isEmpty())
            throw new EntityNotFoundException("user doesn't own this symbol");
        else
            return assetOptional.get();
    }

    @Transactional
    public void modifyQuantity(String symbol, int quantity)
    {
        Asset asset = getAsset(symbol);
        asset.setQuantity(asset.getQuantity() + quantity);
        if (asset.getQuantity() == 0)
            deleteAsset(symbol);
    }
}

