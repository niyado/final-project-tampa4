package com.conygre.backendTampa4.service;

import com.conygre.backendTampa4.dao.BalanceLogRepository;
import com.conygre.backendTampa4.entity.BalanceLog;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BalanceLogServiceTest {

    @InjectMocks
    BalanceLogService balanceLogService;

    @Mock
    BalanceLogRepository dao;

    private final ArrayList<BalanceLog> balanceLogs = new ArrayList<BalanceLog> (Arrays.asList(
            new BalanceLog(100000, 1000d),
            new BalanceLog(100001, 10020d),
            new BalanceLog(100002, 1020d)));


    @Test
    void getAll() {
        when(dao.findAll()).thenReturn(balanceLogs);
        ArrayList<BalanceLog> balanceLogsReturned = (ArrayList<BalanceLog>) balanceLogService.getAll();
        assertEquals(balanceLogsReturned, balanceLogs);

    }

    @Test
    void logBalanceChange() {
        balanceLogService.logBalanceChange(balanceLogs.get(0));
        verify(dao, times(1)).save(any(BalanceLog.class));
    }
}