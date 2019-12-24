package com.ruoyi.project.system.user.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.ruoyi.framework.shiro.token.WxOpenIdToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

/**
 * author:zwy
 * Date:2019-12-16
 * Time:21:48
 */
@Service
public class LoginUtil {

    @Value("${alipay.privateKey}")
    private String PRIVATE_KEY;
    @Value("${alipay.publicKey}")
    private String PUBLIC_KEY ;
    @Value("${alipay.appId}")
    private String APP_ID ;

    private final static String API_URL = "https://openapi.alipay.com/gateway.do";



    /***
     * 支付宝用户授权，获取用户信息
     * @author Mark
     * @param code
     * @return
     */
    public WxOpenIdToken getALiPayUserInfo(String code) {

        AlipayClient alipayClient = new DefaultAlipayClient(API_URL, APP_ID, PRIVATE_KEY, "json", "GBK", PUBLIC_KEY, "RSA2");
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        request.setCode(code);
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        String accessToken  = Objects.requireNonNull(response).getAccessToken();
        if (response.isSuccess()) {
            AlipayClient alipayClients = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                    APP_ID, PRIVATE_KEY, "json",
                    "GBK", PUBLIC_KEY, "RSA2");
            AlipayUserInfoShareRequest req = new AlipayUserInfoShareRequest();
            AlipayUserInfoShareResponse res = null;
            try {
                res = alipayClients.execute(req, accessToken);
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
            if (Objects.requireNonNull(res).isSuccess()){
                String wxOpenId = "alipay"+ UUID.randomUUID();
                WxOpenIdToken wxOpenIdToken = new WxOpenIdToken();
                wxOpenIdToken.setOpenId(wxOpenId);
                wxOpenIdToken.setAvatar(res.getAvatar());
                wxOpenIdToken.setCity(res.getCity());
                wxOpenIdToken.setGender(res.getGender());
                wxOpenIdToken.setNickName(res.getNickName());
                wxOpenIdToken.setProvince(res.getProvince());
                return wxOpenIdToken;
            }

        }

        return null;

    }

}
