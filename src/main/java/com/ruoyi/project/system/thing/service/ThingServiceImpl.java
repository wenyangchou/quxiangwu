package com.ruoyi.project.system.thing.service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.district.mapper.DistrictMapper;
import com.ruoyi.project.system.message.mapper.MessageMapper;
import com.ruoyi.project.system.thing.constant.ThingConstant;
import com.ruoyi.project.system.thing.domain.*;
import com.ruoyi.project.system.thing.mapper.ImageMapper;
import com.ruoyi.project.system.thing.mapper.ThingMapper;
import com.ruoyi.project.system.thing.mapper.ThingUserLikeMapper;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.mapper.UserMapper;
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

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Thing> getLatest() {
        return thingMapper.getLatest();
    }


    @Override
    public List<ThingDTO> getLatestThingDTO() {
        User user = ShiroUtils.getUser();
        List<ThingDTO> thingDTOS = thingMapper.getLatestThingDTO();

        if (user!=null&&user.getUserId()!=null){
            thingDTOS.forEach(thingDTO -> {
                Long likeId = thingUserLikeMapper.getUserLikeByUserIdAndThingId(user.getUserId(),thingDTO.getId());
                thingDTO.setIfCollect(likeId!=null);
            });
        }
        return thingDTOS;
    }

    @Override
    public List<ThingDTO> getLatestByTypeId(Long typeId) {
        List<ThingDTO> thingDTOS = thingMapper.getLatestAndByTypeId(typeId);
        User user = ShiroUtils.getUser();
        if (user!=null&&user.getUserId()!=null){
            thingDTOS.forEach(thingDTO -> {
                Long likeId = thingUserLikeMapper.getUserLikeByUserIdAndThingId(user.getUserId(),thingDTO.getId());
                thingDTO.setIfCollect(likeId!=null);
            });
        }
        return thingDTOS;
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
        User releaser = userMapper.selectUserById(thing.getUserId());

        List<Image> images = imageMapper.getByThingId(skuId);


        List<String> imageUrls = new ArrayList<>();
        images.forEach(image -> imageUrls.add(image.getImgPath()+image.getImageUrl()));

        Long likeId = null;
        if (!ShiroUtils.getUserId().equals(ShiroUtils.NO_LOGIN_ID)){
            likeId = thingUserLikeMapper.getUserLikeByUserIdAndThingId(ShiroUtils.getUserId(),skuId);
        }

        SkuDetailDTO skuDetailDTO = new SkuDetailDTO();
        skuDetailDTO.setName(thing.getName());
        skuDetailDTO.setStatus(thing.getStatus());
        skuDetailDTO.setDesc(thing.getDescription());
        skuDetailDTO.setPrice(thing.getPrice());
        skuDetailDTO.setUserId(thing.getUserId());
        skuDetailDTO.setImg(imageUrls);
        skuDetailDTO.setIfCollected(likeId!=null);
        skuDetailDTO.setAvatar(releaser.getAvatar());
        skuDetailDTO.setUserName(releaser.getLoginName());
        skuDetailDTO.setArea(thing.getArea());
        skuDetailDTO.setSkuType(thing.getTypeId());
        return skuDetailDTO;
    }

    @Override
    public long uploadFile(String filePath,Long thingId) {
        String file = "/"+filePath;
        Image image = new Image();
        image.setImageUrl(file);
        image.setImgPath("/profile");
        image.setThingId(thingId);
         imageMapper.insertImage(image);
        long imageId = image.getId();
        Thing thing = thingMapper.getById(thingId);
        if (thing==null){
            log.error("物品没有，物品id:{}",thingId);
        }else if (thing.getTopImgId().equals(0L)){
            thing.setTopImgId(imageId);
            thingMapper.updateThing(thing);
        }

        return imageId;
    }

    @Override
    public int addImage(String filePath, Long thingId) {
        String file = "/"+filePath;
        Image image = new Image();
        image.setImageUrl(file);
        image.setImgPath("/profile");
        image.setThingId(thingId);
        int imageId = imageMapper.insertImage(image);

        Thing thing = thingMapper.getById(thingId);
        if (thing.getTopImgId().equals(0L)){
            thing.setTopImgId((long) imageId);
            thingMapper.updateThing(thing);
        }

        return imageId;
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
    public List<ThingDTO> getThingDTOByUserIdOnSale(Long userId) {
        if (userId==null){
            userId = ShiroUtils.getUserId();
        }
        return thingMapper.getThingDTOByUserIdAndStatus(userId,ThingConstant.ON_SALE);
    }

    @Override
    public List<ThingDTO> getThingByQubaId(Long qubaId) {
        return thingMapper.getThingDTOByQubaIdAndStatus(qubaId,ThingConstant.ON_SALE);
    }

    @Override
    public List<Thing> getUserThingByStatusAndUserId(Integer status, Long userId) {
        return thingMapper.getByUserIdAndStatus(userId,status);
    }

    @Override
    public List<Thing> getUserBuy() {
        Long userId = ShiroUtils.getUserId();
        return thingMapper.getByUserFromOrder(userId);
    }

    @Override
    public long addThing(ThingAddDTO thingAddDTO) {

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
        thing.setArea(thingAddDTO.getArea());

        thingMapper.insertThing(thing);
        long thingId = thing.getId();

        if (imageUrls==null||imageUrls.size()==0){
            return thingId;
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
        thingMapper.updateThing(thing);
        return thingId;

    }

    @Override
    public List<UserThingDTO> getUserOnSale() {
        List<Thing> things = thingMapper.getByUserIdAndStatus(ShiroUtils.getUserId(), ThingConstant.ON_SALE);
        List<UserThingDTO> userThingDTOS = new ArrayList<>();
        return getUserThingDTOS(things, userThingDTOS);
    }


    @Override
    public List<UserThingDTO> getUserDownSaleThing() {
        List<UserThingDTO> userThingDTOS = new ArrayList<>();
        List<Thing> things = getUserThingByStatus(ThingConstant.DOWN_SHELF);
        return getUserThingDTOS(things, userThingDTOS);
    }

    @Override
    public int updateThingStatus(Long thingId, Integer status) {
        Thing thing = new Thing();
        thing.setId(thingId);
        thing.setStatus(status);
        return thingMapper.updateThing(thing);
    }


    @Override
    public int deleteThing(Long thingId) {
        //TODO 级联删除趣吧的数据
        return thingMapper.deleteThing(thingId);
    }

    private List<UserThingDTO> getUserThingDTOS(List<Thing> things, List<UserThingDTO> userThingDTOS) {
        things.forEach(thing -> {
            Image image = imageMapper.getById(thing.getTopImgId());
            UserThingDTO userThingDTO = new UserThingDTO();
            userThingDTO.setAvatar(ShiroUtils.getUser().getAvatar());
            userThingDTO.setSkuName(thing.getName());
            userThingDTO.setSkuId(thing.getId());
            userThingDTO.setPrice(thing.getPrice());
            userThingDTO.setTime(thing.getModifyTime());
            userThingDTO.setTypeId(thing.getTypeId());
            userThingDTO.setStatus(thing.getStatus());
            userThingDTO.setCollectionAmount(thingUserLikeMapper.getCountByThingId(thing.getId()));
            userThingDTO.setMessageAmount(messageMapper.getCountByThingId(thing.getId()));
            if (image!=null&&image.getId()!=null){
                userThingDTO.setImg(image.getImgPath()+image.getImageUrl());
            }
            userThingDTOS.add(userThingDTO);
        });

        return userThingDTOS;
    }

}
