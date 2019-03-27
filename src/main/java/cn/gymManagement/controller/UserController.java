package cn.gymManagement.controller;

import cn.gymManagement.pojo.User;
import cn.gymManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    /**
     * 会员接口
     */
    @Autowired
    private UserService userService;

    /**
     * 会员登录
     *
     * @param session      session域
     * @param userAccount  账号
     * @param userPassword 密码
     * @return
     */
    @RequestMapping(value = "userLogin")
    @ResponseBody
    public int userLogin(HttpSession session, String userAccount, String userPassword, String code) {
        //验证码验证
        String myCode= ((String) session.getAttribute("code")).toLowerCase();
        System.out.println("获取的验证码："+code);
        System.out.println("正确验证码："+myCode);
        //获取验证码
        if (code.equals(myCode)) {
            //执行登录
            User users = userService.userLogin(userAccount, userPassword);
            //若存在用户，则打印用户信息
            System.out.println("是否存在：" + users);
            //若用户密码错误，则返回1
            if (users == null) {
                System.out.println("对不起，您的账号或密码错误");
                return 0;
            } else {
                System.out.println("登录成功");
                //将用户信息存入session中
                session.setAttribute("userSession", users);
                return 1;
            }
        } else {
            System.out.println("验证码错误：" + myCode + ":" + code);
            return 2;
        }
    }

    /**
     * 检查用户是否登录
     * 登录：1 未登录：0
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "examineLogin", method = RequestMethod.GET)
    @ResponseBody
    public int examineLogin(HttpSession session) {
        if (session.getAttribute("userSession") == null) {
            System.out.println("用户未登录");
            return 0;
        } else {
            System.out.println("用户已登录");
            return 1;
        }
    }

    /**
     * 用户退出登录，注销用户
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "userQuit", method = RequestMethod.GET)
    @ResponseBody
    public int userQuit(HttpSession session) {
        if (session.getAttribute("userSession") == null) {
            System.out.println("用户不存在,请前往登录");
            return 0;
        } else {
            System.out.println("退出成功");
            //删除指定对象中的所有信息
            session.removeAttribute("userSession");
            //清楚session中所有信息，注销用户，使session失效
            session.invalidate();
            return 1;
        }
    }
}
