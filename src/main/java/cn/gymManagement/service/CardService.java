package cn.gymManagement.service;

import cn.gymManagement.pojo.Card;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CardService {
    int addUserCard(@Param("userID")int userID, @Param("rankName")String rankName, @Param("validTime")String validTime);
    int delCardByUserId(@Param("userID")int userID);
    int updateCardByUserId(@Param("rankName")String rankName,@Param("validTime")String validTime,@Param("userID")int userID);
    int getNumberByName(@Param("rankName")String rankName);
    List<Card> getUserCardByName(@Param("pages") int pages, @Param("limits") int limits, @Param("rankName")String rankName);
    List<Card> getUserCardList(@Param("pages") int pages, @Param("limits") int limits);
    int getCardNumber();
}
