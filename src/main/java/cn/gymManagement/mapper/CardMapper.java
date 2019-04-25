package cn.gymManagement.mapper;

import cn.gymManagement.pojo.Card;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 根据会员类型查询总数
     * @param rankName
     * @return
     */
    @Select("select count(*) from t_card where rankName=#{rankName}")
    int getNumberByName(@Param("rankName")String rankName);

    /**
     * 根据会员卡类型查询会员信息
     * @param rankName
     * @return
     */
    @Select("select * from t_card where rankName=#{rankName} order by userID desc limit #{pages},#{limits}")
    @Results({
            @Result(id = true, column = "userID", property = "userID"),
            @Result(column = "userID", property = "users", many = @Many(select = "cn.gymManagement.mapper.UserMapper.getUsersById"))
    })
    List<Card> getUserCardByName(@Param("pages") int pages, @Param("limits") int limits,@Param("rankName")String rankName);

    /**
     * 查询会员卡类型所有会员
     * @param pages
     * @param limits
     * @return
     */
    @Select("select * from t_card order by userID desc limit #{pages},#{limits}")
    @Results({
            @Result(id = true, column = "userID", property = "userID"),
            @Result(column = "userID", property = "users", many = @Many(select = "cn.gymManagement.mapper.UserMapper.getUsersById"))
    })
    List<Card> getUserCardList(@Param("pages") int pages, @Param("limits") int limits);

    /**
     * 查询会员卡总数
     * @return
     */
    @Select("select count(*) from t_card")
    int getCardNumber();
}
