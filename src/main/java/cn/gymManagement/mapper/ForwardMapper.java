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

}
