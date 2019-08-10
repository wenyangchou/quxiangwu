package com.ruoyi.project.system.thing.service;

import com.ruoyi.project.system.thing.domain.ThingType;
import com.ruoyi.project.system.thing.mapper.ThingTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:zwy
 * Date:2019-08-10
 * Time:14:45
 */
@Service
public class ThingTypeServiceImpl implements IThingTypeService {

    @Autowired
    private ThingTypeMapper thingTypeMapper;

    @Override
    public List<ThingType> getAll() {
        return thingTypeMapper.getAll();
    }

    @Override
    public ThingType getById(Long id) {
        return thingTypeMapper.getById(id);
    }

    @Override
    public int insertThingType(ThingType thingType) {
        return thingTypeMapper.insertThingType(thingType);
    }
}
