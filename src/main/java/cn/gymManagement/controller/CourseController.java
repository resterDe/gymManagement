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


}
