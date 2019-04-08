package cn.gymManagement.mapper;

import cn.gymManagement.pojo.Forward;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForwardMapper {
    /**
     * 新增预约
     * @param courseID
     * @param userID
     * @param forwardTime
     * @return
     */
    @Insert("insert into t_forward(courseID,userID,forwardTime) values(#{courseID},#{userID},#{forwardTime})")
    int addUserCourse(@Param("courseID")int courseID,@Param("userID") int userID,@Param("forwardTime")String forwardTime);

    /**
     * 查询会员预约课程
     * 联表查询课程信息
     * @param userID
     * @return
     */
    @Results({
            @Result(id=true,column = "courseID",property = "courseID"),
            @Result(column = "courseID",property = "courseList",many = @Many(select = "cn.gymManagement.mapper.CourseMapper.getCourseByUserID"))
    })
    @Select("select * from t_forward where userID=#{userID}")
    List<Forward> getUserForward(@Param("userID")int userID);

    /**
     * 1、查询会员是否已经预约过此课程
     * 2、查询会员已预约的课程
     * @param userID 会员唯一标识
     * @return
     */
    @Select("select * from t_forward where userID=#{userID}")
    List<Forward> selectUserCourse(@Param("userID")int userID);

    /**
     * 根据教程id删除相应教程
     * @param forwardID
     * @return
     */
    @Delete("delete from t_forward where forwardID=#{forwardID}")
    int delUserForward(@Param("forwardID")int forwardID);
}
