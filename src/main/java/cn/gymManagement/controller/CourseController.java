package cn.gymManagement.controller;

import cn.gymManagement.pojo.Course;
import cn.gymManagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 查询全部课程信息
     * @return
     */
    @RequestMapping(value = "getCourseAll",method = RequestMethod.GET)
    @ResponseBody
    public List<Course> getCourseAll(){
        return courseService.getCourseAll();
    }

    /**
     * 根据id查询教程相关信息
     * @param courseID
     * @return
     */
    @RequestMapping(value = "getCourseInfoById",method = RequestMethod.GET)
    @ResponseBody
    public Course getCourseInfoById(int courseID){
        return courseService.getCourseListById(courseID);
    }

    /**
     * 删除教程
     * 0 表示成功 1表示失败
     * @param courseID
     * @return
     */
    @RequestMapping(value = "delCourseById",method = RequestMethod.DELETE)
    @ResponseBody
    public int delCourseById(int courseID){
        int row=courseService.delCourseById(courseID);
        if (row==1){
            System.out.println("删除成功");
            return 0;
        }else {
            System.out.println("删除失败");
            return 1;
        }
    }

    /**
     * 修改教程信息
     * @param courseName
     * @param startTime
     * @param endTime
     * @param site
     * @param introduce
     * @param courseID
     * @return
     */
    @RequestMapping(value = "updateCourseById",method = RequestMethod.PUT)
    @ResponseBody
    public int updateCourseById(String courseName, String startTime,String endTime, String site, String introduce, int courseID){
        //将获取到的起始时间与结束时间合并
        String courseTime=startTime+"~"+endTime;
        int row=courseService.updateCourseById(courseName,courseTime,site,introduce,courseID);
        if (row==1){
            System.out.println("修改成功");
            return 0;
        }else {
            System.out.println("修改失败");
            return 1;
        }
    }

    /**
     * 添加教程信息
     * @param courseName
     * @param site
     * @param startTime
     * @param endTime
     * @param introduce
     * @param staffID
     * @param maxNumber
     * @return
     */
    @RequestMapping(value = "addCourse",method = RequestMethod.POST)
    @ResponseBody
    public int addCourse(String courseName,String site,String startTime,String endTime,String introduce,int staffID,int maxNumber){
        //定义时间段字符串
        String courseTime=startTime+"~"+endTime;
        //定义初始化预约人数
        int reservationNumber=0;
        //执行添加操作
        int row=courseService.insertCourse(courseName,courseTime,staffID,site,introduce,maxNumber,reservationNumber);
        if (row==1){
            System.out.println("添加成功");
            return 0;
        }else {
            System.out.println("添加失败");
            return 1;
        }
    }
}
