package com.ruoyi.project.system.type.service;

import com.ruoyi.project.system.type.domain.ServiceType;

import java.util.List;

/**
 * author:zwy
 * Date:2019-08-17
 * Time:21:16
 */
public interface ITypeService {

    List<ServiceType> getAllTypes();

    ServiceType getById(Long id);
}
