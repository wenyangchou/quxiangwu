package com.ruoyi.project.system.thing.service;

import com.ruoyi.project.system.thing.domain.ThingType;

import java.util.List;

/**
 * author:zwy
 * Date:2019-08-10
 * Time:14:44
 */
public interface IThingTypeService {

    List<ThingType> getAll();

    ThingType getById(Long id);

    int insertThingType(ThingType thingType);
}
