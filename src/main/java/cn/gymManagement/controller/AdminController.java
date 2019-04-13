package cn.gymManagement.controller;

import cn.gymManagement.pojo.Staff;
import cn.gymManagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public int adminLogin(HttpSession session, int seriaNumber, String newPassword) {
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
            session.setAttribute("adminSession", adminStaff);
            return 1;
        }
    }
    /**
     * 检查管理员用户是否登录
     * 登录：1 未登录：0
     *
     * @param session session
     * @return
     */
    @RequestMapping(value = "examineAdminLogin", method = RequestMethod.GET)
    @ResponseBody
    public int examineAdminLogin(HttpSession session) {
        if (session.getAttribute("adminSession") == null) {
            System.out.println("管理员不存在");
            return 0;
        } else {
            System.out.println("管理员已存在");
            return 1;
        }
    }
    /**
     * 管理员安全退出登录
     * 0：表示管理员不存在，清除错误
     * 1：安全退出
     *
     * @param session session
     * @return
     */
    @RequestMapping(value = "adminQuit", method = RequestMethod.GET)
    @ResponseBody
    public int adminQuit(HttpSession session) {
        if (session.getAttribute("adminSession") == null) {
            System.out.println("管理员不存在，请检查账号仔重新登录");
            return 0;
        } else {
            System.out.println("退出成功");
            //删除指定管理员登录信息
            session.removeAttribute("adminSession");
            //清除对应管理员session所有信息，安全退出注销
            session.invalidate();
            return 1;
        }
    }

    /**
     * 修改管理员密码
     * @param newPassword 新密码
     * @return 2：表示不存在管理员账户  1：表示修改成功   （非）!1表示修改失败
   */
    @RequestMapping(value = "updateAdminPassword",method = RequestMethod.PUT)
    @ResponseBody
    public int updateAdminPassword(HttpSession session,String newPassword){
        //获取管理员session信息
        Staff staffs= (Staff) session.getAttribute("adminSession");
        //判断是否存在该管理员
        if (staffs==null){
            System.out.println("不存在该管理员，请登录再执行此操作");
            return 2;
        }else {
            //获取管理员id
            int staffID=staffs.getStaffID();
            //执行密码修改
            System.out.println("需要修改的密码："+newPassword);
            int row=adminService.updateAdminPwd(staffID,newPassword);
            System.out.println("修改管理员密码返回值："+row);
            if (row==1){
                System.out.println("修改成功后清除原有session信息");
                //删除指定管理员登录信息
                session.removeAttribute("adminSession");
                //清除对应管理员session所有信息，安全退出注销
                session.invalidate();
            }else {
                System.out.println("修改失败");
            }
            return row;
        }
    }

    /**
     * 获取管理员信息
     */
    @RequestMapping(value = "getAdminInfo",method = RequestMethod.GET)
    @ResponseBody
    public Staff getAdminInfo(HttpSession session){
        Staff staffs= (Staff) session.getAttribute("adminSession");
        return staffs;
    }
}
