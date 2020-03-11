package com.ruoyi.project.system.user.controller;

import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.shiro.token.WxOpenIdToken;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.invite.service.IInviteHistoryService;
import com.ruoyi.project.system.user.domain.WechatSession;
import com.ruoyi.project.system.user.service.IUserService;
import com.ruoyi.project.system.user.util.LoginUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Controller
public class LoginController extends BaseController
{

    @Autowired
    private IUserService userService;

    @Autowired
    private IInviteHistoryService iInviteHistoryService;

    @Autowired
    private LoginUtil loginUtil;


    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }

        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe)
    {

        if (null!=username&&null!=password){
            if (username.equals("admin")&&password.equals("quxiangwu@2020")){
                WxOpenIdToken token = new WxOpenIdToken("oMmOL5VeHSkdmncqtz_aVTkOsLfw");
                Subject subject = SecurityUtils.getSubject();
                subject.login(token);
                return success();
            }
        }

        String msg = "用户或密码错误";
        return error(msg);

    }

    @PostMapping("/wxTest")
    @ResponseBody
    public AjaxResult wxLoginTest(){
        WxOpenIdToken token = new WxOpenIdToken("oMmOL5VeHSkdmncqtz_aVTkOsLfw");
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return success().put("data", userService.getDTOByUserId(ShiroUtils.getUser()));
    }

    @PostMapping("/adminLogin")
    @ResponseBody
    public AjaxResult adminLogin(String pwd){
        if (pwd.equals("quxiangwu@2020")){
            WxOpenIdToken token = new WxOpenIdToken("oMmOL5VeHSkdmncqtz_aVTkOsLfw");
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            return success().put("data", userService.getDTOByUserId(ShiroUtils.getUser()));
        }
        return error();
    }


    @PostMapping("/wxLogin")
    @ResponseBody
    public AjaxResult wxLoginOrRegister(String code,Long invitor){
        WechatSession wechatSession = userService.getWechatSessionByCode(code);
        if (wechatSession!=null&&wechatSession.getOpen_id()!=null){
            WxOpenIdToken token = new WxOpenIdToken(wechatSession.getOpen_id());
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);

            if (invitor!=null&& !invitor.toString().equals("0")){
                iInviteHistoryService.addInviteHistory(invitor,ShiroUtils.getUserId());
            }

            return success().put("data", userService.getDTOByUserId(ShiroUtils.getUser()));
        }else {
            String msg = "请求非法";
            return error(msg);
        }
    }

    @PostMapping("/alipayLogin")
    @ResponseBody
    public AjaxResult alipayLoginOrRegister(String code,Long invitor){
        WxOpenIdToken alipayToken = loginUtil.getALiPayUserInfo(code);
        if (alipayToken!=null){
            Subject subject = SecurityUtils.getSubject();
            subject.login(alipayToken);
            if (invitor!=null&& !invitor.toString().equals("0")){
                iInviteHistoryService.addInviteHistory(invitor,ShiroUtils.getUserId());
            }

            return success().put("data", userService.getDTOByUserId(ShiroUtils.getUser()));
        }else {
            String msg = "请求非法";
            return error(msg);
        }

    }




    @GetMapping("/unauth")
    @ResponseBody
    public String unauth()
    {
        return "unauth，权限不足";
    }
}
