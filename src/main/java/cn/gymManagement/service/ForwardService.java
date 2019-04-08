package cn.gymManagement.service;

import cn.gymManagement.pojo.Forward;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ForwardService {
    int addUserCourse(@Param("courseID")int courseID, @Param("userID") int userID, @Param("forwardTime")String forwardTime);

    List<Forward> getUserForward(@Param("userID")int userID);

    List<Forward> selectUserCourse(@Param("userID")int userID);

    int delUserForward(@Param("forwardID")int forwardID);
}
