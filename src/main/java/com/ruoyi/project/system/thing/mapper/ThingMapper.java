package com.ruoyi.project.system.thing.mapper;

import com.ruoyi.project.system.thing.domain.Image;
import com.ruoyi.project.system.thing.domain.Thing;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThingMapper {

    List<Thing> getLatest();

    List<Thing> getLatestAndByTypeId(Long typeId);

    Thing getById(Long id);

    List<Image> getImagesByThingId(Long thingId);

    int insertThing(Thing thing);

    int updateThing(Thing thing);

    List<Thing> getByUserIdAndStatus(Long userId,Integer status);

    List<Thing> getByUserFromOrder(Long userId);
}
