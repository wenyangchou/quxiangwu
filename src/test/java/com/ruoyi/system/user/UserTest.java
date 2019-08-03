package com.ruoyi.system.user;

import com.ruoyi.project.system.user.domain.WechatSession;
import com.ruoyi.project.system.user.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * author:zwy
 * Date:2019-06-30
 * Time:09:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private IUserService userService;

    @Test
    public void wechatSessionTest(){
        WechatSession wechatSession = userService.getWechatSessionByCode("081CUtcT0CXZl029y4bT00cJcT0CUtcE");
        System.out.println(wechatSession);
    }
}
