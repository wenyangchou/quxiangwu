package com.ruoyi.project.system.thing.service;

import com.ruoyi.project.system.thing.domain.Thing;

import java.util.List;

public interface IThingService {

    List<Thing> getLatest();

    List<Thing> getLatestByTypeId(Long typeId);

    Thing getById(Long id);
}
