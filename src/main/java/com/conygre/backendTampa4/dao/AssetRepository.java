package com.conygre.backendTampa4.dao;

import com.conygre.backendTampa4.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AssetRepository extends JpaRepository<Asset, String> {
    Collection<Asset> findByAssetType(String type);
}
