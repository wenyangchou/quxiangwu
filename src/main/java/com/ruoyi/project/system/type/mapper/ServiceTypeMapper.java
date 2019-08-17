package com.ruoyi.project.system.type.mapper;

import com.ruoyi.project.system.type.domain.ServiceType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author:zwy
 * Date:2019-08-17
 * Time:21:07
 */
@Repository
public interface ServiceTypeMapper {

    List<ServiceType> getAll();

    ServiceType getById(Long id);

    int add(ServiceType serviceType);

    int update(ServiceType serviceType);

    int delete(ServiceType serviceType);
}
