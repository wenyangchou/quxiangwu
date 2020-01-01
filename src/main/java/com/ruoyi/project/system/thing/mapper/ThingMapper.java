package com.ruoyi.project.system.thing.mapper;

import com.ruoyi.project.system.thing.domain.GoodsDTO;
import com.ruoyi.project.system.thing.domain.Image;
import com.ruoyi.project.system.thing.domain.Thing;
import com.ruoyi.project.system.thing.domain.ThingDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThingMapper {

    List<Thing> getLatest();



    List<GoodsDTO> getLetestGoodsByUserId(Long userId);

    List<GoodsDTO> getLatestGoods();

    Thing getById(Long id);

    List<Image> getImagesByThingId(Long thingId);

    int insertThing(Thing thing);

    int updateThing(Thing thing);

    int deleteThing(Long thingId);

    List<Thing> getByUserIdAndStatus(Long userId,Integer status);

    List<Thing> getByUserFromOrder(Long userId);

    List<ThingDTO> getLatestThingDTO();

    List<ThingDTO> getLatestAndByTypeId(Long typeId);
}
