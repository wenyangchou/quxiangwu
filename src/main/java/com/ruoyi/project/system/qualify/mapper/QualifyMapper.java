package com.ruoyi.project.system.qualify.mapper;

import com.ruoyi.project.system.qualify.domain.Qualify;
import org.springframework.stereotype.Repository;

/**
 * author:zwy
 * Date:2019-08-18
 * Time:01:31
 */
@Repository
public interface QualifyMapper {

    int insert(Qualify qualify);

    int update(Qualify qualify);


}
