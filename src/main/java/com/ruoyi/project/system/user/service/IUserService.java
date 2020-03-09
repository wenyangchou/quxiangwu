package com.ruoyi.project.system.user.service;

import com.ruoyi.project.system.user.domain.*;

import java.util.List;

/**
 * 用户 业务层
 * 
 * @author ruoyi
 */
public interface IUserService
{

    /**
     * 根据条件分页查询用户对象
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
     List<User> selectUserList(User user);

    User selectUserByOpenId(String openId);

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
     User selectUserByLoginName(String userName);

    /**
     * 通过手机号码查询用户
     * 
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
     User selectUserByPhoneNumber(String phoneNumber);

    /**
     * 通过邮箱查询用户
     * 
     * @param email 邮箱
     * @return 用户对象信息
     */
     User selectUserByEmail(String email);

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
     User selectUserById(Long userId);

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
     int deleteUserById(Long userId);

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
     int deleteUserByIds(String ids) throws Exception;

    /**
     * 保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
     int insertUser(User user);

    int insertWxUser(User user);

    /**
     * 保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
     int updateUser(User user);

    /**
     * 修改用户详细信息
     * 
     * @param user 用户信息
     * @return 结果
     */
     int updateUserInfo(User user);

    /**
     * 修改用户密码信息
     * 
     * @param user 用户信息
     * @return 结果
     */
     int resetUserPwd(User user);

    /**
     * 校验用户名称是否唯一
     * 
     * @param loginName 登录名称
     * @return 结果
     */
     String checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
     String checkPhoneUnique(User user);

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
     String checkEmailUnique(User user);

    WechatSession getWechatSessionByCode(String code);

    int isFollowed(Long followerId);

    int addFollow(Long followerId);

    int deleteFollow(Long followerId);

    List<User> getUserFollowers();

    List<User> getUserFans();

    List<FansDTO> getMyFans();

    List<FansDTO> getMyFolllow();

    UserDTO getDTOByUserId(User user);

    UserInfoDTO getUserInfo();

    FollowDTO getUserInfoById(Long id);

    int updateUserInfo(UserInfoDTO userInfoDTO);

}
