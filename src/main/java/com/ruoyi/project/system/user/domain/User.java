package com.ruoyi.project.system.user.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lombok.Data;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.system.dept.domain.Dept;
import com.ruoyi.project.system.role.domain.Role;

/**
 * 用户对象 sys_user
 *
 * @author ruoyi
 */
public class User extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /** 用户ID */
  @Excel(name = "用户序号")
  private Long userId;

  /** 用户微信分配的id */
  private String openId;

  private String city;

  private String country;

  private String language;

  private String province;

  private Integer isQualified;

  private Integer friendValue;

  private String qulifyPositiveUrl;

  private String qulifyNegativeUrl;

  private BigDecimal xianquMoney;

  /** 部门ID */
  private Long deptId;

  /** 部门父ID */
  private Long parentId;

  /** 登录名称 */
  @Excel(name = "登录名称")
  private String loginName;

  /** 用户名称 */
  @Excel(name = "用户名称")
  private String userName;

  /** 用户邮箱 */
  @Excel(name = "用户邮箱")
  private String email;

  /** 手机号码 */
  @Excel(name = "手机号码")
  private String phonenumber;

  /** 用户性别 */
  @Excel(name = "用户性别")
  private String sex;

  /** 用户头像 */
  private String avatar;

  /** 密码 */
  private String password;

  /** 盐加密 */
  private String salt;

  /** 帐号状态（0正常 1停用） */
  @Excel(name = "帐号状态")
  private String status;

  /** 删除标志（0代表存在 2代表删除） */
  private String delFlag;

  /** 最后登陆IP */
  @Excel(name = "最后登陆IP")
  private String loginIp;

  /** 最后登陆时间 */
  @Excel(name = "最后登陆时间")
  private Date loginDate;

  /** 部门对象 */
  private Dept dept;

  /** 角色集合 */
  private List<Role> roles;

  /** 角色组 */
  private Long[] roleIds;

  /** 岗位组 */
  private Long[] postIds;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public boolean isAdmin() {
    return isAdmin(this.userId);
  }

  public static boolean isAdmin(Long userId) {
    return userId != null && 1L == userId;
  }

  public Long getDeptId() {
    return deptId;
  }

  public void setDeptId(Long deptId) {
    this.deptId = deptId;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhonenumber() {
    return phonenumber;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  /** 生成随机盐 */
  public void randomSalt() {
    // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
    SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
    String hex = secureRandom.nextBytes(3).toHex();
    setSalt(hex);
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
  }

  public String getLoginIp() {
    return loginIp;
  }

  public void setLoginIp(String loginIp) {
    this.loginIp = loginIp;
  }

  public Date getLoginDate() {
    return loginDate;
  }

  public void setLoginDate(Date loginDate) {
    this.loginDate = loginDate;
  }

  public Dept getDept() {
    return dept;
  }

  public void setDept(Dept dept) {
    this.dept = dept;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public Long[] getRoleIds() {
    return roleIds;
  }

  public void setRoleIds(Long[] roleIds) {
    this.roleIds = roleIds;
  }

  public Long[] getPostIds() {
    return postIds;
  }

  public void setPostIds(Long[] postIds) {
    this.postIds = postIds;
  }

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public Integer getIsQualified() {
    return isQualified;
  }

  public void setIsQualified(Integer isQualified) {
    this.isQualified = isQualified;
  }

  public Integer getFriendValue() {
    return friendValue;
  }

  public void setFriendValue(Integer friendValue) {
    this.friendValue = friendValue;
  }

  public String getQulifyPositiveUrl() {
    return qulifyPositiveUrl;
  }

  public void setQulifyPositiveUrl(String qulifyPositiveUrl) {
    this.qulifyPositiveUrl = qulifyPositiveUrl;
  }

  public String getQulifyNegativeUrl() {
    return qulifyNegativeUrl;
  }

  public void setQulifyNegativeUrl(String qulifyNegativeUrl) {
    this.qulifyNegativeUrl = qulifyNegativeUrl;
  }

  public BigDecimal getXianquMoney() {
    return xianquMoney;
  }

  public void setXianquMoney(BigDecimal xianquMoney) {
    this.xianquMoney = xianquMoney;
  }

  @Override
  public String toString() {
    return "User [userId="
        + userId
        + ", deptId="
        + deptId
        + ", parentId="
        + parentId
        + ", loginName="
        + loginName
        + ", userName="
        + userName
        + ", email="
        + email
        + ", phonenumber="
        + phonenumber
        + ", sex="
        + sex
        + ", avatar="
        + avatar
        + ", password="
        + password
        + ", salt="
        + salt
        + ", status="
        + status
        + ", delFlag="
        + delFlag
        + ", loginIp="
        + loginIp
        + ", loginDate="
        + loginDate
        + ", dept="
        + dept
        + ", roles="
        + roles
        + ", roleIds="
        + Arrays.toString(roleIds)
        + ", postIds="
        + Arrays.toString(postIds)
        + "]";
  }
}
