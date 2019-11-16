package com.ruoyi.project.system.thing.service;

import com.ruoyi.project.system.thing.domain.*;

import java.util.List;

public interface IThingService {

    List<Thing> getLatest();

    List<ThingDTO> getLatestThingDTO();

    List<Thing> getLatestByTypeId(Long typeId);

    Thing getById(Long id);

    SkuDetailDTO getBySkuId(Long skuId);

    int uploadFile(String filePath,Long thingId);

    int addThing(Thing thing);

    int addThing(ThingAddDTO thingAddDTO);

    int updateThing(Long thingId,Long imageId);

    List<Thing> getUserThingByStatus(Integer status);

    List<Thing> getUserBuy();

    List<UserThingDTO> getUserOnSale();

    int updateThingStatus(Long thingId,Integer status);

    List<UserThingDTO> getUserDownSaleThing();
}
