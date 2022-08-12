package com.conygre.backendTampa4.service;


import com.conygre.backendTampa4.dao.AssetRepository;
import com.conygre.backendTampa4.entity.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AssetService {

    @Autowired
    private AssetRepository dao;

    public Collection<Asset> getAllAssets() {
        return dao.findAll();
    }

    public Asset getAssetInfo(String symbol) {
        try {
            Asset asset = dao.getReferenceById(symbol);
            return asset;
        } catch(EntityNotFoundException e) {
            return null;
        }
    }

    public void addAsset(Asset asset) {
        dao.save(asset);
    }

    //public Collection<Asset> findByType(String type) {return (Collection<Asset>) dao.findByType(type);}

}

