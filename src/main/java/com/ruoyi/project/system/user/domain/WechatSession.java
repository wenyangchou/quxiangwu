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

    public WechatSession() {
    }

    public WechatSession(String session_key, String expires_in, String open_id, String errcode, String errmsg, WechatHint hints) {
        this.session_key = session_key;
        this.expires_in = expires_in;
        this.open_id = open_id;
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.hints = hints;
    }
}

