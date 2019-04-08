package cn.gymManagement.controller;

import cn.gymManagement.pojo.Course;
import cn.gymManagement.pojo.Forward;
import cn.gymManagement.pojo.User;
import cn.gymManagement.service.CourseService;
import cn.gymManagement.service.ForwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/forward")
public class ForwardController {
    @Autowired
    private ForwardService forwardService;
    @Autowired
    private CourseService courseService;

    /**
     * *预约课程
     * 按照人数要求，先查询是否已达到人数上限，预约成功后修改已预约人数数量
     * 0表示出错，1表示成功，2表示人数已达上限,3表示登录失效（未登录）,4表示已预约过当前课程
     *
     * @param session
     * @param courseID
     * @param forwardTime
     * @return
     */
    @RequestMapping(value = "addForwardCourse", method = RequestMethod.POST)
    @ResponseBody
    private int addForwardCourse(HttpSession session, int courseID, String forwardTime) {
        //判断是否登录
        if (session.getAttribute("userSession") == null) {
            System.out.println("登录失效,请先登录再预约");
            return 3;
        } else {
            //获取用户信息
            User users = (User) session.getAttribute("userSession");
            //获取用户ID
            int userID = users.getUserID();
            //判断当前预约人数是否已达上限
            Course courses = courseService.getCourseNumber(courseID);
            //获取预约信息中的课程唯一标识
            List<Forward> forwardList=forwardService.selectUserCourse(userID);
            //判断是否已经预约当前课程
            for (Forward f:forwardList){
                if (f.getCourseID()==courseID){
                    //如果已经预约，则跳出方法
                    System.out.println("已经存在课程："+courseID);
                    return 4;
                }
            }
            //继续
            int maxNumber = courses.getMaxNumber();
            int reservationNumber = courses.getReservationNumber();
            if (reservationNumber < maxNumber) {
                //执行添加
                int result = forwardService.addUserCourse(courseID, userID, forwardTime);
                if (result == 1) {
                    System.out.println("添加课程成功");
                    //查询成功后对课程预约人数进行调整
                    int code = courseService.updateCourseRstNumber(reservationNumber + 1, courseID);
                    if (code == 1) {
                        System.out.println("修改人数成功");
                        return 1;
                    } else {
                        System.out.println("修改人数错误，系统出错");
                        return 0;
                    }
                } else {
                    System.out.println("添加失败，系统出错,暂停修改人数");
                    return 0;
                }
            } else {
                System.out.println("人数已达上限，请选择其他课程");
                return 2;
            }
        }
    }

    /**
     * 查询个人预约列表
     * @param session
     * @return
     */
    @RequestMapping(value = "getUserForward",method = RequestMethod.GET)
    @ResponseBody
    public List<Forward> getUserForward(HttpSession session){
        int userID=((User)session.getAttribute("userSession")).getUserID();
        return forwardService.getUserForward(userID);
    }

    /**
     * 根据教程id删除相应教程
     * 0 表示成功删除  1 表示删除失败
     * @param forwardID
     * @return
     */
    @RequestMapping(value = "delUserForward",method = RequestMethod.DELETE)
    @ResponseBody
    public int delUserForward(int forwardID){
        int row=forwardService.delUserForward(forwardID);
        if (row==1){
            System.out.println("删除成功："+row);
            return 0;
        }else {
            System.out.println("删除失败："+row);
            return 1;
        }
    }
}
