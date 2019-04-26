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
     *
     * @return
     */
    @Override
    public List<Course> getCourseAll() {
        return courseMapper.getCourseAll();
    }

    /**
     * 根据id查询预约限制人数以及当前预约人数
     *
     * @param courseID
     * @return
     */
    @Override
    public Course getCourseNumber(int courseID) {
        return courseMapper.getCourseNumber(courseID);
    }

    /**
     * 调整预约课程已预约人数
     *
     * @param reservationNumber
     * @param courseID
     * @return
     */
    @Override
    public int updateCourseRstNumber(int reservationNumber, int courseID) {
        return courseMapper.updateCourseRstNumber(reservationNumber, courseID);
    }

    /**
     * 根据教程id查询教程信息
     *
     * @param courseID
     * @return
     */
    @Override
    public Course getCourseListById(int courseID) {
        return courseMapper.getCourseListById(courseID);
    }

    /**
     * 删除教程
     *
     * @param courseID
     * @return
     */
    @Override
    public int delCourseById(int courseID) {
        return courseMapper.delCourseById(courseID);
    }

    /**
     * 修改教程信息
     *
     * @param courseName 教程名称
     * @param courseTime 教程持续时间
     * @param site       教程地点
     * @param introduce  教程简介
     * @param courseID   教程唯一标识
     * @return
     */
    @Override
    public int updateCourseById(String courseName, String courseTime, String site, String introduce, int courseID) {
        return courseMapper.updateCourseById(courseName, courseTime, site, introduce, courseID);
    }

    /**
     * 新增教程信息绑定教练信息
     *
     * @param courseName
     * @param courseTime
     * @param staffID
     * @param site
     * @param introduce
     * @param maxNumber
     * @param reservationNumber
     * @return
     */
    @Override
    public int insertCourse(String courseName, String courseTime, int staffID, String site,
                            String introduce, int maxNumber, int reservationNumber) {
        return courseMapper.insertCourse(courseName, courseTime, staffID,
                site, introduce, maxNumber, reservationNumber);
    }
}
