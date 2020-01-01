package com.ruoyi.project.system.user.controller;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.service.DictService;
import com.ruoyi.project.system.user.domain.ContactCardDTO;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 个人信息 业务处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/user/profile")
public class ProfileController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    private String prefix = "system/user/profile";

    @Autowired
    private IUserService userService;

    @Autowired
    private DictService dict;

    /**
     * 个人信息
     */
    @GetMapping()
    public String profile(ModelMap mmap)
    {
        User user = getUser();
        user.setSex(dict.getLabel("sys_user_sex", user.getSex()));
        mmap.put("user", user);
        return prefix + "/profile";
    }

    @GetMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password)
    {
        User user = getUser();
        String encrypt = new Md5Hash(user.getLoginName() + password + user.getSalt()).toHex().toString();
        if (user.getPassword().equals(encrypt))
        {
            return true;
        }
        return false;
    }

    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwd(User user)
    {
        int rows = userService.resetUserPwd(user);
        if (rows > 0)
        {
            setUser(userService.selectUserById(user.getUserId()));
            return success();
        }
        return error();
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/edit";
    }

    /**
     * 修改头像
     */
    @GetMapping("/avatar/{userId}")
    public String avatar(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/avatar";
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(@RequestBody User user)
    {
        if (userService.updateUserInfo(user) > 0)
        {
            setUser(userService.selectUserById(user.getUserId()));
            return success();
        }
        return error();
    }

    @GetMapping("/getContactCard")
    @ResponseBody
    public AjaxResult getContactCard(){
        User user = ShiroUtils.getUser();
        ContactCardDTO contactCardDTO = new ContactCardDTO();
        contactCardDTO.setPhone(user.getPhonenumber());
        contactCardDTO.setWechat(user.getWechat());
        contactCardDTO.setQq(user.getQq());
        return AjaxResult.success().put("contactCard",contactCardDTO);
    }

    @GetMapping("/getContactCardByUserId")
    @ResponseBody
    public AjaxResult getUserContactCard(Long userId){
        User user = userService.selectUserById(userId);
        ContactCardDTO contactCardDTO = new ContactCardDTO();
        contactCardDTO.setPhone(user.getPhonenumber());
        contactCardDTO.setWechat(user.getWechat());
        contactCardDTO.setQq(user.getQq());
        return AjaxResult.success().put("contactCard",contactCardDTO);
    }

    @PostMapping("/fillContactCard")
    @ResponseBody
    public AjaxResult updateContactCard(String wechat,String qq,String phonenumber){
        User user = new User();
        user.setUserId(ShiroUtils.getUserId());
        user.setWechat(wechat);
        user.setQq(qq);
        user.setPhonenumber(phonenumber);
        if (userService.updateUserInfo(user)>0){
            user = userService.selectUserById(user.getUserId());
            if (StringUtils.isNotEmpty(user.getPhonenumber())&&StringUtils.isNotEmpty(user.getWechat())&&StringUtils.isNotEmpty(user.getQq())){
                user.setIsFillContact(UserConstants.FILLED_CONTACT);
                userService.updateUserInfo(user);
            }
        }else {
            return error();
        }
        ShiroUtils.setUser(user);
        return success();
    }

    /**
     * 保存头像
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateAvatar")
    @ResponseBody
    public AjaxResult updateAvatar(User user, @RequestParam("avatarfile") MultipartFile file)
    {
        try
        {
            if (!file.isEmpty())
            {
                String avatar = FileUploadUtils.upload(file);
                user.setAvatar(avatar);
                if (userService.updateUserInfo(user) > 0)
                {
                    setUser(userService.selectUserById(user.getUserId()));
                    return success();
                }
            }
            return error();
        }
        catch (Exception e)
        {
            log.error("修改头像失败！", e);
            return error(e.getMessage());
        }
    }
}
