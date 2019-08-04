package com.ruoyi.project.system.thing.service;

import com.ruoyi.project.system.thing.domain.Image;
import com.ruoyi.project.system.thing.domain.Thing;
import com.ruoyi.project.system.thing.mapper.ThingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThingServiceImpl implements IThingService {

    @Autowired
    private ThingMapper thingMapper;

    @Override
    public List<Thing> getLatest() {
        return thingMapper.getLatest();
    }

    @Override
    public List<Thing> getLatestByTypeId(Long typeId) {
        return thingMapper.getLatestAndByTypeId(typeId);
    }

    @Override
    public Thing getById(Long id) {
        Thing thing = thingMapper.getById(id);
        List<Image> images = thingMapper.getImagesByThingId(id);
        thing.setImages(images);
        return thing;
    }
}
