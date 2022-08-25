package com.conygre.backendTampa4.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import com.conygre.backendTampa4.dao.AssetRepository;
import com.conygre.backendTampa4.entity.Asset;
import org.junit.Before;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AssetServiceTest {

    @Mock
    private AssetRepository dao;

    @InjectMocks
    private AssetService assetService;

    private final ArrayList<Asset> assets = new ArrayList<Asset> (Arrays.asList(
            new Asset("AAPL", "APPLE", 15, "stock"),
            new Asset("GOOGL", "GOOGLE", 2, "stock"),
            new Asset("AMZN", "AMAZON", 1, "stock"),
            new Asset("PFE", "PFIZER", 1, "???")));


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void getAllAssets() {
        when(dao.findAll()).thenReturn(assets);
        ArrayList<Asset> assetsReturned = (ArrayList<Asset>) assetService.getAllAssets();
        assertEquals(assetsReturned, assets);
    }

    @Test
    void getAssetInfo() {
        for (Asset asset:
             assets) {
            when(dao.findById(asset.getSymbol())).thenReturn(java.util.Optional.of(asset));
            Asset assetReturned = assetService.getAssetInfo(asset.getSymbol());
            assertEquals(assetReturned, asset);
        }
    }


    @Test
    void getAssetInfoThrowNotFound() {
        Asset asset = assets.get(0);
        when(dao.findById(asset.getSymbol())).thenReturn(java.util.Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> assetService.getAssetInfo(asset.getSymbol()));
    }

    @Test
    void addAsset() {
        assetService.addAsset(assets.get(0));
        verify(dao, times(1)).save(any(Asset.class));
    }

    @Test
    void getAssetAlreadyExists() {
        for (Asset asset:
                assets) {
            when(dao.findById(asset.getSymbol())).thenReturn(java.util.Optional.of(asset));
            assertThrows(EntityExistsException.class, () -> assetService.addAsset(asset));
        }
    }

    @Test
    void findByType() {
        when(dao.findByAssetType("stock")).thenReturn(assets.subList(0,3));
        List<Asset> assetsReturned = (List<Asset>) assetService.findByType("stock");
        verify(dao, times(1)).findByAssetType("stock");
        assertEquals(assetsReturned, assets.subList(0,3));
    }

    @Test
    void deleteAsset() {
        when(dao.findById(assets.get(0).getSymbol()))
                .thenReturn(java.util.Optional.of(assets.get(0)));
        assetService.deleteAsset(assets.get(0).getSymbol());
        verify(dao, times(1)).deleteById(assets.get(0).getSymbol());
    }

    @Test
    void deleteAssetThrowsNotFound() {
        Asset asset = assets.get(0);
        when(dao.findById(asset.getSymbol())).thenReturn(java.util.Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> assetService.deleteAsset(asset.getSymbol()));
    }

    @Test
    void assetExists() {
        for (Asset asset:
                assets) {
            when(dao.findById(asset.getSymbol())).thenReturn(java.util.Optional.of(asset));
            Asset assetReturned = assetService.getAsset(asset.getSymbol());
            assertEquals(assetReturned, asset);
        }
    }

    @Test
    void assetDoesNotExist() {
        Asset asset = assets.get(0);
        when(dao.findById(asset.getSymbol())).thenReturn(java.util.Optional.empty());
        assertFalse(assetService.assetExists(asset.getSymbol()));
    }

    @Test
    void getAsset() {
        for (Asset asset:
                assets) {
            when(dao.findById(asset.getSymbol())).thenReturn(java.util.Optional.of(asset));
            Asset assetReturned = assetService.getAsset(asset.getSymbol());
            assertEquals(assetReturned, asset);
        }
    }

    @Test
    void getAssetThrowNotFound() {
        Asset asset = assets.get(0);
        when(dao.findById(asset.getSymbol())).thenReturn(java.util.Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> assetService.getAsset(asset.getSymbol()));
    }

    @Test
    void modifyQuantityNegative() {
        Asset asset = assets.get(0);
        int amount = -1;
        int newQuantity = asset.getQuantity() + amount;
        when(dao.findById(asset.getSymbol())).thenReturn(java.util.Optional.of(asset));
        assetService.modifyQuantity(asset.getSymbol(), amount);
        assertEquals(newQuantity, asset.getQuantity());
    }

    @Test
    void modifyQuantityPositive() {
        Asset asset = assets.get(0);
        int amount = 1;
        int newQuantity = asset.getQuantity() + amount;
        when(dao.findById(asset.getSymbol())).thenReturn(java.util.Optional.of(asset));
        assetService.modifyQuantity(asset.getSymbol(), amount);
        assertEquals(newQuantity, asset.getQuantity());
    }

    @Test
    void modifyQuantityDeleteAsset() {
        Asset asset = assets.get(2);
        int amount = -1;
        int newQuantity = asset.getQuantity() + amount;
        when(dao.findById(asset.getSymbol())).thenReturn(java.util.Optional.of(asset));
        assetService.modifyQuantity(asset.getSymbol(), amount);
        assertEquals(newQuantity, asset.getQuantity());
        verify(dao, times(1)).deleteById(asset.getSymbol());
    }
}