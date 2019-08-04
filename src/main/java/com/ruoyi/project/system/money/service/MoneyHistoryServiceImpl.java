package com.ruoyi.project.system.money.service;

import com.ruoyi.project.system.money.domain.MoneyHistory;
import com.ruoyi.project.system.money.mapper.MoneyHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoneyHistoryServiceImpl implements IMoneyHistoryService {

    @Autowired
    private MoneyHistoryMapper moneyHistoryMapper;

    @Override
    public List<MoneyHistory> getByUserId(Long userId) {
        return moneyHistoryMapper.getByUserId(userId);
    }
}
