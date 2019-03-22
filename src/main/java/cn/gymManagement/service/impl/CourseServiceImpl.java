package cn.gymManagement.service.impl;

import cn.gymManagement.mapper.CourseMapper;
import cn.gymManagement.pojo.Course;
import cn.gymManagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 查询全部课程信息
     * @return
     */
    @Override
    public List<Course> getCourseAll() {
        return courseMapper.getCourseAll();
    }

    /**
     * 根据id查询预约限制人数以及当前预约人数
     * @param courseID
     * @return
     */
    @Override
    public Course getCourseNumber(int courseID) {
        return courseMapper.getCourseNumber(courseID);
    }

    /**
     * 调整预约课程已预约人数
     * @param reservationNumber
     * @param courseID
     * @return
     */
    @Override
    public int updateCourseRstNumber(int reservationNumber, int courseID) {
        return courseMapper.updateCourseRstNumber(reservationNumber, courseID);
    }
}
