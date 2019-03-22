package cn.gymManagement.mapper;

import cn.gymManagement.pojo.Course;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 预约课程信息
 */
@Repository
public interface CourseMapper {

    /**
     * 查询全部预约课程
     * @return
     */
    @Select("select * from t_course")
    @Results({
            @Result(id = true, column = "staffID", property = "staffID"),
            @Result(column = "staffID", property = "staffList", many = @Many(select = "cn.gymManagement.mapper.StaffMapper.getStaffById"))
    })
    List<Course> getCourseAll();

    /**
     * 查询个人用户的预约信息列表
     * @param courseID
     * @return
     */
    @Select("select * from t_course where courseID=#{courseID}")
    List<Course> getCourseByUserID(@Param("courseID")int courseID);

    /**
     * 根据id查询单个课程预约限制人数以及当前人数
     * @param courseID
     * @return
     */
    @Select("select maxNumber,reservationNumber from t_course where courseID=#{courseID}")
    Course getCourseNumber(@Param("courseID")int courseID);

    /**
     * 调整预约课程已预约人数
     * @param reservationNumber
     * @param courseID
     * @return
     */
    @Update("update t_course set reservationNumber=#{reservationNumber} where courseID=#{courseID}")
    int updateCourseRstNumber(@Param("reservationNumber")int reservationNumber,@Param("courseID")int courseID);

}
