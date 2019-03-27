package cn.gymManagement.controller;

import cn.gymManagement.utils.VerificationCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 刷新验证码
 */
@Controller
public class VerificationController {
    @RequestMapping(value = "/getVerification",method = RequestMethod.GET)
    @ResponseBody
    public String getVerification(HttpSession session){
        //获取长度为4的随机验证码返回
        String code=VerificationCodeUtil.getRandomString(4);
        session.setAttribute("code",code);
        return code;
    }
}
