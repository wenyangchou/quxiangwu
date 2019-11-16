package com.ruoyi.project.system.thing.service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.district.domain.District;
import com.ruoyi.project.system.district.mapper.DistrictMapper;
import com.ruoyi.project.system.message.mapper.MessageMapper;
import com.ruoyi.project.system.thing.constant.ThingConstant;
import com.ruoyi.project.system.thing.domain.*;
import com.ruoyi.project.system.thing.mapper.ImageMapper;
import com.ruoyi.project.system.thing.mapper.ThingMapper;
import com.ruoyi.project.system.thing.mapper.ThingUserLikeMapper;
import com.ruoyi.project.system.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ThingServiceImpl implements IThingService {

    @Autowired
    private ThingMapper thingMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private DistrictMapper districtMapper;

    @Autowired
    private ThingUserLikeMapper thingUserLikeMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Thing> getLatest() {
        return thingMapper.getLatest();
    }


    @Override
    public List<ThingDTO> getLatestThingDTO() {
        User user = ShiroUtils.getUser();
        List<ThingDTO> thingDTOS = thingMapper.getLatestThingDTO();
        thingDTOS.forEach(thingDTO -> {
            Long likeId = thingUserLikeMapper.getUserLikeByUserIdAndThingId(user.getUserId(),thingDTO.getId());
            thingDTO.setIfCollect(likeId!=null);
        });
        return thingDTOS;
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
    public SkuDetailDTO getBySkuId(Long skuId) {
        Thing thing = thingMapper.getById(skuId);
        List<Image> images = imageMapper.getByThingId(skuId);


        List<String> imageUrls = new ArrayList<>();
        images.forEach(image -> {
            imageUrls.add(image.getImgPath()+image.getImageUrl());
        });

        Long likeId = thingUserLikeMapper.getUserLikeByUserIdAndThingId(ShiroUtils.getUserId(),skuId);
        District district = districtMapper.getByDistrictId(thing.getDistrictId());
        SkuDetailDTO skuDetailDTO = new SkuDetailDTO();
        skuDetailDTO.setName(thing.getName());
        skuDetailDTO.setDesc(thing.getDescription());
        skuDetailDTO.setPrice(thing.getPrice());
        skuDetailDTO.setImg(imageUrls);
        skuDetailDTO.setIfCollected(likeId!=null);
        skuDetailDTO.setAvatar(ShiroUtils.getUser().getAvatar());
        skuDetailDTO.setUserName(ShiroUtils.getUser().getUserName());
        skuDetailDTO.setArea(district.getDistrictName());
        return skuDetailDTO;
    }

    @Override
    public int uploadFile(String filePath,Long thingId) {
        String file = "/"+filePath;
        Image image = new Image();
        image.setImageUrl(file);
        image.setImgPath("/profile");
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

    @Override
    public List<Thing> getUserThingByStatus(Integer status) {
        Long userId = ShiroUtils.getUserId();
        return thingMapper.getByUserIdAndStatus(userId,status);
    }

    @Override
    public List<Thing> getUserBuy() {
        Long userId = ShiroUtils.getUserId();
        return thingMapper.getByUserFromOrder(userId);
    }

    @Override
    public int addThing(ThingAddDTO thingAddDTO) {

        List<String> imageUrls = thingAddDTO.getImgList();
        Thing thing = new Thing();
        thing.setTopImgId(0L);
        thing.setName(thingAddDTO.getName());
        thing.setPrice(thingAddDTO.getPrice());
        thing.setPriceType(0);
        thing.setUserId(ShiroUtils.getUserId());
        thing.setTypeId(thingAddDTO.getTypeId());
        thing.setDistrictId(thingAddDTO.getDistrictId());
        thing.setDescription(thingAddDTO.getDesc());
        thing.setStatus(0);
        thing.setTradeType(thingAddDTO.getTradeType());
        thing.setIsNew(thingAddDTO.getIfNew());
        thingMapper.insertThing(thing);

        if (imageUrls==null){
            return 0;
        }

        log.info("thingId:{}",thing.getId());

        for (int i = 0; i < imageUrls.size(); i++) {

            String file ="/"+ imageUrls.get(i);
            Image image = new Image();
            image.setImageUrl(file);
            image.setImgPath("/profile");
            image.setThingId(thing.getId());
            imageMapper.insertImage(image);

            if (i==0){
                thing.setTopImgId(image.getId());
            }
        }

        return thingMapper.updateThing(thing);
    }

    @Override
    public List<UserThingDTO> getUserOnSale() {

        List<Thing> things = thingMapper.getByUserIdAndStatus(ShiroUtils.getUserId(), ThingConstant.ON_SALE);
        List<UserThingDTO> userThingDTOS = new ArrayList<>();
        things.forEach(thing -> {
            UserThingDTO userThingDTO = new UserThingDTO();
            userThingDTO.setSkuId(thing.getId());
            userThingDTO.setPrice(thing.getPrice());
            userThingDTO.setTime(thing.getModifyTime());
            userThingDTO.setTypeId(thing.getTypeId());
            userThingDTO.setCollectionAmount(thingUserLikeMapper.getCountByThingId(thing.getId()));
            userThingDTO.setMessageAmount(messageMapper.getCountByThingId(thing.getId()));
            userThingDTOS.add(userThingDTO);
        });

        return userThingDTOS;
    }

    @Override
    public int updateThingStatus(Long thingId, Integer status) {
        Thing thing = new Thing();
        thing.setId(thingId);
        thing.setStatus(status);
        return thingMapper.updateThing(thing);
    }

    @Override
    public List<UserThingDTO> getUserDownSaleThing() {
        List<UserThingDTO> userThingDTOS = new ArrayList<>();
        List<Thing> things = getUserThingByStatus(ThingConstant.DOWN_SHELF);
        things.forEach(thing -> {
            UserThingDTO userThingDTO = new UserThingDTO();
            userThingDTO.setSkuId(thing.getId());
            userThingDTO.setSkuName(thing.getName());
            userThingDTO.setTime(thing.getModifyTime());
            userThingDTO.setTypeId(thing.getTypeId());
            userThingDTO.setPrice(thing.getPrice());
            userThingDTOS.add(userThingDTO);
        });
        return userThingDTOS;
    }

    @Override
    public int deleteThing(Long thingId) {
        //TODO 级联删除趣吧的数据
        return thingMapper.deleteThing(thingId);
    }
}
