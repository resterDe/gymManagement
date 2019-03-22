package cn.gymManagement.service;

import cn.gymManagement.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseService {
    List<Course> getCourseAll();

    Course getCourseNumber(@Param("courseID")int courseID);

    int updateCourseRstNumber(@Param("reservationNumber")int reservationNumber,@Param("courseID")int courseID);
}
