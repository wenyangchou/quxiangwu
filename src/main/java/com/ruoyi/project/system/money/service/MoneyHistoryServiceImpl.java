package com.ruoyi.project.system.money.service;

import com.ruoyi.common.constant.MoneyConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.money.domain.CoinChangeDTO;
import com.ruoyi.project.system.money.domain.MoneyHistory;
import com.ruoyi.project.system.money.mapper.MoneyHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoneyHistoryServiceImpl implements IMoneyHistoryService {

    @Autowired
    private MoneyHistoryMapper moneyHistoryMapper;

    @Override
    public List<MoneyHistory> getByUserId(Long userId) {
        return moneyHistoryMapper.getByUserId(userId);
    }

    @Override
    public List<CoinChangeDTO> getUserMoneyHistory() {
        Long userId = ShiroUtils.getUserId();
        List<MoneyHistory> moneyHistories = getByUserId(userId);
        List<CoinChangeDTO> coinChangeDTOS = new ArrayList<>();
        moneyHistories.forEach(moneyHistory -> {
            CoinChangeDTO coinChangeDTO = new CoinChangeDTO();
            coinChangeDTO.setAmount(moneyHistory.getXiangquMoney());
            coinChangeDTO.setTime(moneyHistory.getCreateTime());
            coinChangeDTO.setTitle(moneyHistory.getDescription());
            if (moneyHistory.getType().equals(MoneyConstant.TYPE_INCOME)){
                coinChangeDTO.setType("income");
            }else{
                coinChangeDTO.setType("pay");
            }
            coinChangeDTOS.add(coinChangeDTO);
        });
        return coinChangeDTOS;
    }
}
