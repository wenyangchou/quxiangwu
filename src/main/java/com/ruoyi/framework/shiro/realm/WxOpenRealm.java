package com.ruoyi.framework.shiro.realm;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.shiro.service.LoginService;
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

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * author:zwy
 * Date:2019-06-30
 * Time:11:20
 */
@Slf4j
public class WxOpenRealm extends AuthorizingRealm  {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = ShiroUtils.getUser();
        // 角色列表
        Set<String> roles = new HashSet<String>();
        // 功能列表
        Set<String> menus = new HashSet<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        }
        else
        {
            roles = roleService.selectRoleKeys(user.getUserId());
            menus = menuService.selectPermsByUserId(user.getUserId());
            // 角色加入AuthorizationInfo认证对象
            info.setRoles(roles);
            // 权限加入AuthorizationInfo认证对象
            info.setStringPermissions(menus);
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        WxOpenIdToken wxOpenIdToken = (WxOpenIdToken)  authenticationToken;
        String openid = wxOpenIdToken.getOpenId();
        User user = userService.selectUserByOpenId(openid);
        if (user!=null){
        }else {
            user = new User();
            user.setOpenId(openid);
            user.setLoginName(UUID.randomUUID().toString());
            user.setUserName(user.getLoginName());
            userService.insertWxUser(user);
        }

        return new SimpleAuthenticationInfo(openid,"ok",getName());
    }
}
