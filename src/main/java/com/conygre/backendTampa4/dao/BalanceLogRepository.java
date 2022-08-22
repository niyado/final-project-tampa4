package com.conygre.backendTampa4.dao;

import com.conygre.backendTampa4.entity.BalanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceLogRepository extends JpaRepository<BalanceLog, Integer> {
}
