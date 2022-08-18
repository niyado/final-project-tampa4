package com.conygre.backendTampa4.service;


import com.conygre.backendTampa4.dao.AssetRepository;
import com.conygre.backendTampa4.entity.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
}

