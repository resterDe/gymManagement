package cn.gymManagement.service;

import cn.gymManagement.pojo.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseService {
    List<Course> getCourseAll();

    Course getCourseNumber(@Param("courseID")int courseID);

    int updateCourseRstNumber(@Param("reservationNumber")int reservationNumber,@Param("courseID")int courseID);

    Course getCourseListById(@Param("courseID")int courseID);

    int delCourseById(@Param("courseID") int courseID);

    int updateCourseById(@Param("courseName")String courseName,@Param("courseTime")String courseTime,
                         @Param("site")String site,@Param("introduce")String introduce,@Param("courseID")int courseID);

    int insertCourse(@Param("courseName")String courseName,@Param("courseTime")String courseTime,
                     @Param("staffID")int staffID,@Param("site")String site,@Param("introduce")String introduce,
                     @Param("maxNumber")int maxNumber,@Param("reservationNumber")int reservationNumber);
}
