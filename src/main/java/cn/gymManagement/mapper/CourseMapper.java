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
     *
     * @return
     */
    @Select("select * from t_course")
    @Results({
            @Result(id = true, column = "staffID", property = "staffID"),
            @Result(column = "staffID", property = "staffs", many = @Many(select = "cn.gymManagement.mapper.StaffMapper.getStaffById"))
    })
    List<Course> getCourseAll();

    /**
     * 根据教程id获取教程信息
     *
     * @param courseID
     * @return
     */
    @Select("select * from t_course where courseID=#{courseID}")
    @Results({
            @Result(id = true, column = "staffID", property = "staffID"),
            @Result(column = "staffID", property = "staffs", many = @Many(select = "cn.gymManagement.mapper.StaffMapper.getStaffById"))
    })
    Course getCourseListById(@Param("courseID") int courseID);


    /**
     * 查询个人用户的预约信息列表
     *
     * @param courseID
     * @return
     */
    @Select("select * from t_course where courseID=#{courseID}")
    List<Course> getCourseByUserID(@Param("courseID") int courseID);

    /**
     * 根据id查询单个课程预约限制人数以及当前人数
     *
     * @param courseID
     * @return
     */
    @Select("select maxNumber,reservationNumber from t_course where courseID=#{courseID}")
    Course getCourseNumber(@Param("courseID") int courseID);

    /**
     * 调整预约课程已预约人数
     *
     * @param reservationNumber
     * @param courseID
     * @return
     */
    @Update("update t_course set reservationNumber=#{reservationNumber} where courseID=#{courseID}")
    int updateCourseRstNumber(@Param("reservationNumber") int reservationNumber, @Param("courseID") int courseID);

    /**
     * 删除教程
     * @param courseID
     * @return
     */
    @Delete("delete from t_course where courseID=#{courseID}")
    int delCourseById(@Param("courseID") int courseID);

    /**
     * 修改教程内容
     * @param courseName 教程名称
     * @param courseTime 教程持续时间
     * @param site 教程开课地点
     * @param introduce 教程简介
     * @param courseID 教程唯一标识
     * @return
     */
    @Update("update t_course set courseName=#{courseName},courseTime=#{courseTime},site=#{site}," +
            "introduce=#{introduce} where courseID=#{courseID}")
    int updateCourseById(@Param("courseName")String courseName,@Param("courseTime")String courseTime,
                         @Param("site")String site,@Param("introduce")String introduce,@Param("courseID")int courseID);

    /**
     * 新增教程信息绑定教练信息
     * @param courseName
     * @param courseTime
     * @param staffID
     * @param site
     * @param introduce
     * @param maxNumber
     * @param reservationNumber
     * @return
     */
    @Insert("insert into t_course (courseName,courseTime,staffID,site,introduce,maxNumber,reservationNumber) " +
            "values(#{courseName},#{courseTime},#{staffID},#{site},#{introduce},#{maxNumber},#{reservationNumber})")
    int insertCourse(@Param("courseName")String courseName,@Param("courseTime")String courseTime,
                     @Param("staffID")int staffID,@Param("site")String site,@Param("introduce")String introduce,
                     @Param("maxNumber")int maxNumber,@Param("reservationNumber")int reservationNumber);

}
