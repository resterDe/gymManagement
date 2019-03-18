package cn.gymManagement.controller;

import cn.gymManagement.pojo.User;
import cn.gymManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
     * @param session session域
     * @param userAccount 账号
     * @param userPassword 密码
     * @return
     */
    @RequestMapping(value = "userLogin")
    @ResponseBody
    public int userLogin(HttpSession session,String userAccount, String userPassword) {
        //执行登录
        User users=userService.userLogin(userAccount, userPassword);
        //若存在用户，则打印用户信息
        System.out.println("是否存在："+users);
        //若用户密码错误，则返回1
        if (users==null){
            System.out.println("对不起，您的账号或密码错误");
            return 1;
        }else {
            System.out.println("登录成功");
            //将用户信息存入session中
            session.setAttribute("userSession",users);
            return 0;
        }
    }
}
