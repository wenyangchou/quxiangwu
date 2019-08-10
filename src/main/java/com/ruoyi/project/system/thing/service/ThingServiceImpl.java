package com.ruoyi.project.system.thing.service;

import com.ruoyi.project.system.thing.domain.Image;
import com.ruoyi.project.system.thing.domain.Thing;
import com.ruoyi.project.system.thing.mapper.ImageMapper;
import com.ruoyi.project.system.thing.mapper.ThingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThingServiceImpl implements IThingService {

    @Autowired
    private ThingMapper thingMapper;

    @Autowired
    private ImageMapper imageMapper;

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

    @Override
    public int uploadFile(String filePath,Long thingId) {
        String file = "/profile/"+filePath;
        Image image = new Image();
        image.setImageUrl(file);
        image.setThingId(thingId);
        return imageMapper.insertImage(image);
    }

    @Override
    public int addThing(Thing thing) {
        return thingMapper.insertThing(thing);
    }

    @Override
    public int updateThing(Long thingId, Long imageId) {
        Thing thing = new Thing();
        thing.setId(thingId);
        thing.setTopImgId(imageId);
        return thingMapper.updateThing(thing);
    }
}
