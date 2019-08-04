package com.ruoyi.project.system.money.mapper;

import com.ruoyi.project.system.money.domain.MoneyHistory;

import java.util.List;

public interface MoneyHistoryMapper {

    List<MoneyHistory> getByUserId(Long userId);

}
