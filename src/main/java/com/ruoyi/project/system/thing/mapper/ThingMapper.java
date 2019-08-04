package com.ruoyi.project.system.thing.mapper;

import com.ruoyi.project.system.thing.domain.Image;
import com.ruoyi.project.system.thing.domain.Thing;

import java.util.List;

public interface ThingMapper {

    List<Thing> getLatest();

    List<Thing> getLatestAndByTypeId(Long typeId);

    Thing getById(Long id);

    List<Image> getImagesByThingId(Long thingId);
}
