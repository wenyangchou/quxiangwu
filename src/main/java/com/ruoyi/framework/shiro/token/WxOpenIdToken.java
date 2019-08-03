package com.ruoyi.framework.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

/**
 * author:zwy
 * Date:2019-06-30
 * Time:11:16
 */
public class WxOpenIdToken extends UsernamePasswordToken implements Serializable {
    private static final long serialVersionUID = 4322168875042315147L;

    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public Object getPrincipal() {
        return getOpenId();
    }

    @Override
    public Object getCredentials() {
        return "ok";
    }
    public WxOpenIdToken(String openId){
        this.openId=openId;
    }

    public WxOpenIdToken() {
    }
}
