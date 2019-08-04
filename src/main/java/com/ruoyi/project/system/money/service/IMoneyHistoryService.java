package com.ruoyi.project.system.money.service;

import com.ruoyi.project.system.money.domain.MoneyHistory;

import java.util.List;

public interface IMoneyHistoryService {

    List<MoneyHistory> getByUserId(Long userId);
}
