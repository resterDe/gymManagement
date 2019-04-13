package cn.gymManagement.mapper;

import cn.gymManagement.pojo.Card;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * 会员卡信息
 */
@Repository
public interface CardMapper {
    /**
     * 新增会员卡成员
     *
     * @param userID
     * @param rankName
     * @param validTime
     * @return
     */
    @Insert("insert into t_card (userID,rankName,validTime) values(#{userID},#{rankName},#{validTime})")
    int addUserCard(@Param("userID") int userID, @Param("rankName") String rankName, @Param("validTime") String validTime);

    @Select("select * from t_card where userID=#{userID}")
    Card getCardById(@Param("userID")int userID);

    /**
     * 根据会员ID删除会员卡记录
     * @param userID
     * @return
     */
    @Delete("delete from t_card where userID=#{userID}")
    int delCardByUserId(@Param("userID")int userID);

    /**
     * 根据会员ID修改会员相关会员卡信息
     * @param rankName
     * @param validTime
     * @return
     */
    @Update("update t_card set rankName=#{rankName},validTime=#{validTime} where userID=#{userID}")
    int updateCardByUserId(@Param("rankName")String rankName,@Param("validTime")String validTime,@Param("userID")int userID);
}
