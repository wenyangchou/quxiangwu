package com.ruoyi.project.system.thing.mapper;

import com.ruoyi.project.system.thing.domain.ThingType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author:zwy
 * Date:2019-08-10
 * Time:14:40
 */
@Repository
public interface ThingTypeMapper {

    List<ThingType> getAll();

    ThingType getById(Long id);

    int insertThingType(ThingType thingType);
}
