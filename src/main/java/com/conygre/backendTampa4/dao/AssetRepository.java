package com.conygre.backendTampa4.dao;

import com.conygre.backendTampa4.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, String> {

}
