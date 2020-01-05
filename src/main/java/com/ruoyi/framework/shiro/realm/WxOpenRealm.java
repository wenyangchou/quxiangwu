package com.ruoyi.framework.shiro.realm;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.shiro.token.WxOpenIdToken;
import com.ruoyi.project.system.menu.service.IMenuService;
import com.ruoyi.project.system.role.service.IRoleService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/** author:zwy Date:2019-06-30 Time:11:20 */
@Slf4j
public class WxOpenRealm extends AuthorizingRealm {

  @Autowired private IMenuService menuService;

  @Autowired private IRoleService roleService;

  @Autowired private IUserService userService;

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    // 管理员拥有所有权限
    info.addRole("admin");
    info.addStringPermission("*:*:*");
    return info;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
      throws AuthenticationException {
    WxOpenIdToken wxOpenIdToken = (WxOpenIdToken) authenticationToken;
    String openid = wxOpenIdToken.getOpenId();
    User user = userService.selectUserByOpenId(openid);
    if (user == null|| user.getUserId()==null){
      user = new User();
      user.setOpenId(openid);
      user.setLoginName(UUID.randomUUID().toString());

      if (StringUtils.isNotEmpty(wxOpenIdToken.getNickName())){
        user.setLoginName(wxOpenIdToken.getNickName());
        user.setUserName(wxOpenIdToken.getNickName());
      }else{
        user.setUserName(user.getLoginName());
      }

      if (StringUtils.isNotEmpty(wxOpenIdToken.getAvatar())){
        user.setAvatar(wxOpenIdToken.getAvatar());
      }

      if (StringUtils.isNotEmpty(wxOpenIdToken.getCity())){
        user.setCity(wxOpenIdToken.getCity());
      }

      if (StringUtils.isNotEmpty(wxOpenIdToken.getProvince())){
        user.setProvince(wxOpenIdToken.getProvince());
      }

      if (StringUtils.isNotEmpty(wxOpenIdToken.getGender())){
        user.setSex(wxOpenIdToken.getGender());
      }

      userService.insertWxUser(user);
    }

    return new SimpleAuthenticationInfo(user, "ok", getName());
  }
}
