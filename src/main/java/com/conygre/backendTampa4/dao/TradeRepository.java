package com.conygre.backendTampa4.dao;

import com.conygre.backendTampa4.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {

    String getAverageBuyQuery = "SELECT AVG(price)"
                                        + " FROM portfolio.trades"
                                        + " WHERE type = 'BUY' AND symbol = :symbol ;";

    @Query(nativeQuery=true, value=getAverageBuyQuery)
    Double getAverageBuyPrice(String symbol);
}
