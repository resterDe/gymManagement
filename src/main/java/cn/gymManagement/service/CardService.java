package cn.gymManagement.service;

import org.apache.ibatis.annotations.Param;

public interface CardService {
    int addUserCard(@Param("userID")int userID, @Param("rankName")String rankName, @Param("validTime")String validTime);
    int delCardByUserId(@Param("userID")int userID);
    int updateCardByUserId(@Param("rankName")String rankName,@Param("validTime")String validTime,@Param("userID")int userID);
}
