package com.ruoyi.project.system.user.domain;

import lombok.Data;

/**
 * author:zwy
 * Date:2019-06-30
 * Time:09:30
 */
@Data
public class WechatSession {

    private String session_key;

    private String expires_in;

    private String open_id;

    private String errcode;

    private String errmsg;

    private WechatHint hints;
}

