package cn.gymManagement.mapper;

import cn.gymManagement.pojo.Announcement;
import org.apache.ibatis.annotations.Select;
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
}
