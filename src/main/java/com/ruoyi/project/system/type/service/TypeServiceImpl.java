package com.ruoyi.project.system.type.service;

import com.ruoyi.project.system.type.domain.ServiceType;
import com.ruoyi.project.system.type.mapper.ServiceTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:zwy
 * Date:2019-08-17
 * Time:21:16
 */
@Service
public class TypeServiceImpl implements ITypeService {

    @Autowired
    private ServiceTypeMapper serviceTypeMapper;

    @Override
    public List<ServiceType> getAllTypes() {
        return serviceTypeMapper.getAll();
    }

    @Override
    public ServiceType getById(Long id) {
        return serviceTypeMapper.getById(id);
    }
}
