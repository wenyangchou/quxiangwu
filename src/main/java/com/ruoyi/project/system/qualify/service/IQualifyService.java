package com.ruoyi.project.system.qualify.service;

import com.ruoyi.project.system.qualify.domain.ConfirmHistoryDTO;
import com.ruoyi.project.system.qualify.domain.Qualify;

import java.util.List;

/**
 * author:zwy
 * Date:2019-08-18
 * Time:01:40
 */
public interface IQualifyService {

    int addQualify(Qualify qualify);

    int updateQualify(Qualify qualify);

    int setConfirm(Integer type,String imagePath);

    List<ConfirmHistoryDTO> getConfirmHistory(Integer type);
}
