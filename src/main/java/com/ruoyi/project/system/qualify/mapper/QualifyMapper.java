package com.ruoyi.project.system.qualify.mapper;

import com.ruoyi.project.system.qualify.domain.ConfirmHistoryDTO;
import com.ruoyi.project.system.qualify.domain.ConfirmResultDTO;
import com.ruoyi.project.system.qualify.domain.Qualify;
import com.ruoyi.project.system.qualify.domain.UserQualifyDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author:zwy
 * Date:2019-08-18
 * Time:01:31
 */
@Repository
public interface QualifyMapper {

    int insert(Qualify qualify);

    int update(Qualify qualify);

    List<ConfirmHistoryDTO> getConfirmHistory(@Param("type") Integer type,@Param("userId") Long userId);

    ConfirmResultDTO getLastQualifyStatus(@Param("userId") Long userId, @Param("type") Integer type);

    List<UserQualifyDTO> getQualifyUserByStatus(Integer status);

    List<UserQualifyDTO> getQualifyUser();

    Qualify getById(Long qualifyId);

}
