package com.ruoyi.project.system.user.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.system.role.domain.Role;
import lombok.Data;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户对象 sys_user
 *
 * @author ruoyi
 */
@Data
public class User extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /** 用户ID */
  @Excel(name = "用户序号")
  private Long userId;

  /** 用户微信分配的id */
  private String openId;

  private Integer isFillContact;

  private String wechat;

  private String qq;

  private String city;

  private String country;

  private String language;

  private String province;

  private Integer isQualified;

  private Integer friendValue;

  private String qualifyPositiveUrl;

  private String qualifyNegativeUrl;

  private BigDecimal xianquMoney;

  private Date bornDate;

  private String usualResidence;

  private String industry;

  private String jobTitle;

  private String educationExperience;


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

  /** 角色集合 */
  private List<Role> roles;

  /** 角色组 */
  private Long[] roleIds;



  public boolean isAdmin() {
    return isAdmin(this.userId);
  }

  public static boolean isAdmin(Long userId) {
    if (null!=userId){
      return  (userId==1L || userId==2L || userId==3L);
    }
    return false;
  }


  /** 生成随机盐 */
  public void randomSalt() {
    // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
    SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
    String hex = secureRandom.nextBytes(3).toHex();
    setSalt(hex);
  }


}
