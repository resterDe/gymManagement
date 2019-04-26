package cn.gymManagement.mapper;

import cn.gymManagement.pojo.Announcement;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公告信息
 */
@Repository
public interface AnnouncementMapper {
    /**
     * 查询全部公告信息
     *
     * @return
     */
    @Select("select * from t_announcement")
    List<Announcement> getAnnListAll();

    /**
     * 根据id查询所有公告信息
     *
     * @param annID
     * @return
     */
    @Select("select * from t_announcement where annID=#{annID}")
    Announcement getAnnInfo(@Param("annID") int annID);

    /**
     * 修改公告信息
     *
     * @param headline 标题
     * @param content  内容
     * @param annTime  发布时间
     * @return
     */
    @Update("update t_announcement set headline=#{headline},content=#{content},annTime=#{annTime} where annID=#{annID}")
    int updateAnnInfo(@Param("headline") String headline, @Param("content") String content, @Param("annTime") String annTime, @Param("annID") int annID);

    /**
     * 根据id删除公告
     *
     * @param annID
     * @return
     */
    @Delete("delete from t_announcement where annID=#{annID}")
    int delAnnInfo(@Param("annID") int annID);

    /**
     * 新增公告信息
     *
     * @param headline 标题
     * @param content  内容
     * @param annTime  发布时间
     * @return
     */
    @Insert("insert into t_announcement(headline,content,annTime) values(#{headline},#{content},#{annTime})")
    int insertAnn(@Param("headline") String headline, @Param("content") String content, @Param("annTime") String annTime);
}
