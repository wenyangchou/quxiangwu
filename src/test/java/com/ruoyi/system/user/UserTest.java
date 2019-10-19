package com.ruoyi.system.user;

import com.ruoyi.project.system.thing.domain.ThingAddDTO;
import com.ruoyi.project.system.thing.service.IThingService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.domain.WechatSession;
import com.ruoyi.project.system.user.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** author:zwy Date:2019-06-30 Time:09:43 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class UserTest {

//  @Autowired private IUserService userService;

//  @Autowired
//  private IThingService thingService;
//
//  @Test
//  public void wechatSessionTest() {
//    WechatSession wechatSession =
//        userService.getWechatSessionByCode("081CUtcT0CXZl029y4bT00cJcT0CUtcE");
//    System.out.println(wechatSession);
//  }
//
//  @Test
//  public void userTest() {
//    List<User> userList = userService.selectUserList(new User());
//    System.out.println(userList.size());
//  }
//
//  @Test
//  public void thingLikeTest(){
//    thingService.getLatestThingDTO().forEach(System.out::println);
//  }
//
//  @Test
//  public void addThing(){
//    ThingAddDTO thingAddDTO = new ThingAddDTO();
//    thingAddDTO.setDesc("aa");
//    thingAddDTO.setDistrictId(1L);
//    thingAddDTO.setIfNew(1);
//    thingAddDTO.setName("aa");
//    thingAddDTO.setPrice(BigDecimal.ONE);
//    thingAddDTO.setTradeType(1);
//    thingAddDTO.setTypeId(1L);
//    List<String> images = new ArrayList<>();
//    images.add("aa");
//    images.add("bb");
//    images.add("cc");
//    thingAddDTO.setImgList(images);
//    thingService.addThing(thingAddDTO);
//  }
}
