package com.ruoyi.project.system.money.mapper;

import com.ruoyi.project.system.money.domain.MoneyHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoneyHistoryMapper {

    List<MoneyHistory> getByUserId(Long userId);

    int addMoneyHistory(MoneyHistory moneyHistory);

}
