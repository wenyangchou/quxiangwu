package com.ruoyi.project.system.user.service;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.shiro.service.PasswordService;
import com.ruoyi.project.system.quba.domain.Quba;
import com.ruoyi.project.system.quba.domain.QubaDTO;
import com.ruoyi.project.system.quba.mapper.QubaMapper;
import com.ruoyi.project.system.role.mapper.RoleMapper;
import com.ruoyi.project.system.thing.service.IThingUserLikeService;
import com.ruoyi.project.system.user.domain.*;
import com.ruoyi.project.system.user.mapper.UserFollowMapper;
import com.ruoyi.project.system.user.mapper.UserMapper;
import com.ruoyi.project.system.user.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户 业务层处理
 * 
 * @author ruoyi
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService
{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserFollowMapper userFollowMapper;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IThingUserLikeService thingUserLikeService;

    @Autowired
    private QubaMapper qubaMapper;


    @Value("${wechat.appid}")
    private String appId;

    @Value("${wechat.secret}")
    private String secret;

    /**
     * 根据条件分页查询用户对象
     * 
     * @param user 用户信息
     * 
     * @return 用户信息集合信息
     */
    @Override
    public List<User> selectUserList(User user)
    {
        // 生成数据权限过滤条件
//        user.getParams().put("dataScope", DataScopeUtils.dataScopeFilter());
        return userMapper.selectUserList(user);
    }

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public User selectUserByLoginName(String userName)
    {
        return userMapper.selectUserByLoginName(userName);
    }

    /**
     * 通过手机号码查询用户
     * 
     * @return 用户对象信息
     */
    @Override
    public User selectUserByPhoneNumber(String phoneNumber)
    {
        return userMapper.selectUserByPhoneNumber(phoneNumber);
    }

    /**
     * 通过邮箱查询用户
     * 
     * @param email 邮箱
     * @return 用户对象信息
     */
    @Override
    public User selectUserByEmail(String email)
    {
        return userMapper.selectUserByEmail(email);
    }

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public User selectUserById(Long userId)
    {
        return userMapper.selectUserById(userId);
    }

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long userId)
    {
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        return userMapper.deleteUserById(userId);
    }

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(String ids) throws Exception
    {
        Long[] userIds = Convert.toLongArray(ids);
        for (Long userId : userIds)
        {
            if (User.isAdmin(userId))
            {
                throw new Exception("不允许删除超级管理员用户");
            }
        }
        return userMapper.deleteUserByIds(userIds);
    }

    /**
     * 新增保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int insertUser(User user)
    {
        user.randomSalt();
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setCreateBy(ShiroUtils.getLoginName());
        // 新增用户信息
        int rows = userMapper.insertUser(user);
        // 新增用户与角色管理
        insertUserRole(user);
        return rows;
    }


    @Override
    public int insertWxUser(User user) {
        return userMapper.insertUser(user);
    }

    /**
     * 修改保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(User user)
    {
        Long userId = user.getUserId();
        user.setUpdateBy(ShiroUtils.getLoginName());
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 新增用户与角色管理
        insertUserRole(user);
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户个人详细信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserInfo(User user)
    {
        user.setUserName(user.getLoginName());
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户密码
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetUserPwd(User user)
    {
        user.randomSalt();
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        return updateUserInfo(user);
    }

    /**
     * 新增用户角色信息
     * 
     * @param user 用户对象
     */
    public void insertUserRole(User user)
    {
        // 新增用户与角色管理
        List<UserRole> list = new ArrayList<UserRole>();
        for (Long roleId : user.getRoleIds())
        {
            UserRole ur = new UserRole();
            ur.setUserId(user.getUserId());
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0)
        {
            userRoleMapper.batchUserRole(list);
        }
    }


    /**
     * 校验用户名称是否唯一
     * 
     * @param loginName 用户名
     * @return
     */
    @Override
    public String checkLoginNameUnique(String loginName)
    {
        int count = userMapper.checkLoginNameUnique(loginName);
        if (count > 0)
        {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @return
     */
    @Override
    public String checkPhoneUnique(User user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        User info = userMapper.checkPhoneUnique(user.getPhonenumber());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.USER_PHONE_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @return
     */
    @Override
    public String checkEmailUnique(User user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        User info = userMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.USER_EMAIL_NOT_UNIQUE;
        }
        return UserConstants.USER_EMAIL_UNIQUE;
    }



    @Override
    public WechatSession getWechatSessionByCode(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+appId+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
        String result  = restTemplate.getForObject(url,String.class);
        return JSON.parseObject(result,WechatSession.class);

    }

    @Override
    public User selectUserByOpenId(String openId) {
        return userMapper.selectByUserByOpenId(openId);
    }


    @Override
    public int isFollowed(Long followerId) {
        Long userId = ShiroUtils.getUserId();
        return userFollowMapper.getByUserIdAndFollowerId(userId,followerId)==null?0:1;
    }

    @Override
    public int addFollow(Long followerId) {
        Long userId = ShiroUtils.getUserId();
        if (isFollowed(followerId)>0){
            return 0;
        }

        return userFollowMapper.insertFollow(userId,followerId);
    }

    @Override
    public int deleteFollow(Long followerId) {
        Long userId = ShiroUtils.getUserId();
        return userFollowMapper.deleteFollow(userId,followerId);
    }

    @Override
    public List<User> getUserFollowers() {
        Long userId = ShiroUtils.getUserId();
        return userFollowMapper.getUserFollower(userId);
    }

    @Override
    public List<User> getUserFans() {
        Long userId = ShiroUtils.getUserId();
        return userFollowMapper.getUserFans(userId);
    }

    @Override
    public UserDTO getDTOByUserId(User user) {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getUserId());
        userDTO.setUserAvatar(user.getAvatar());
        userDTO.setUserName(user.getUserName());

        Integer isQualified = user.getIsQualified();
        if (isQualified==null||isQualified.equals(0)){
            userDTO.setIsQualified(false);
        }else{
            userDTO.setIsQualified(true);
        }

        String sex = user.getSex();
        log.info("当前用户:{}",user);
        if (sex==null||sex.equals("0")||sex.equals("男")){
            userDTO.setGender("男");
        }else if (sex.equals("1")||sex.equals("女")){
            userDTO.setGender("女");
        }else{
            userDTO.setGender("未知");
        }

        userDTO.setIsFillContact(user.getIsFillContact()!=null&&user.getIsFillContact().equals(UserConstants.FILLED_CONTACT));
        userDTO.setCoinNumber(user.getXianquMoney());
        Long collectedNumber = thingUserLikeService.getUserLikeByUserId(user.getUserId());
        userDTO.setCollectedNumber(collectedNumber);
        Long focusNumber = userFollowMapper.userFansNumber(user.getUserId());
        userDTO.setFocusNumber(focusNumber);
        userDTO.setMyBar(getUserQuba(user.getUserId()));

        return userDTO;
    }

    private List<QubaDTO> getUserQuba(Long userId){
        List<Quba> qubas = qubaMapper.getByUserId(userId);

        List<QubaDTO> qubaDTOS = new ArrayList<>();
        qubas.forEach(quba -> {
            QubaDTO qubaDTO = new QubaDTO();
            qubaDTO.setBarId(quba.getId());
            qubaDTO.setBarLogo(quba.getLogo());
            qubaDTO.setBarName(quba.getName());
            qubaDTOS.add(qubaDTO);
        });
        return qubaDTOS;
    }

    @Override
    public UserInfoDTO getUserInfo() {
        User user = ShiroUtils.getUser();
        if (user!=null){
            UserInfoDTO userInfoDTO = new UserInfoDTO();
            userInfoDTO.setBirthday(user.getBornDate());
            userInfoDTO.setEducation(user.getEducationExperience());
            userInfoDTO.setIndustry(user.getIndustry());
            userInfoDTO.setJobName(user.getJobTitle());
            userInfoDTO.setPermanentAdd(user.getCity());
            return userInfoDTO;
        }

        return null;
    }

    @Override
    public int updateUserInfo(UserInfoDTO userInfoDTO) {
        User user = ShiroUtils.getUser();
        if (user==null){
            return 0;
        }

        user.setBornDate(userInfoDTO.getBirthday());
        user.setEducationExperience(userInfoDTO.getEducation());
        user.setIndustry(userInfoDTO.getIndustry());
        user.setJobTitle(userInfoDTO.getJobName());
        user.setCity(userInfoDTO.getPermanentAdd());

        int result = userMapper.updateUser(user);

        if (result>0){
            ShiroUtils.setUser(user);
            return result;
        }

        return 0;


    }
}
