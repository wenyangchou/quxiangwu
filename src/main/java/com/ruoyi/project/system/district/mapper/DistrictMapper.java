package com.ruoyi.project.system.district.mapper;

import com.ruoyi.project.system.district.domain.District;
import org.springframework.stereotype.Repository;

/**
 * author:zwy
 * Date:2019-11-10
 * Time:17:59
 */
@Repository
public interface DistrictMapper {

    District getByDistrictId(Long districtId);

}
