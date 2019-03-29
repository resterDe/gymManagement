package cn.gymManagement.controller;

import cn.gymManagement.pojo.Staff;
import cn.gymManagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 员工（管理员）登录
     *
     * @param seriaNumber 员工编号
     * @param newPassword 登录密码 （后期可修改，初始密码为身份证号码后六位）
     * @return
     */
    @RequestMapping(value = "adminLogin", method = RequestMethod.POST)
    @ResponseBody
    public int adminLogin(HttpSession session,int seriaNumber, String newPassword) {

        //执行登录
        Staff adminStaff = adminService.adminLogin(seriaNumber, newPassword);
        System.out.println("管理员信息：" + adminStaff);

        //判断是否存在该用户,拦截器拦截已存在管理员session信息判断是否登录过
        if (adminStaff == null) {
            System.out.println("请核对身份再登陆");
            return 0;
        } else {
            System.out.println("登入管理后台成功");
            //保存session信息
            session.setAttribute("adminSession",adminStaff);
            return 1;
        }
    }
}
