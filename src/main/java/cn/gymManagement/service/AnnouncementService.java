package cn.gymManagement.service;

import cn.gymManagement.pojo.Announcement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnnouncementService {
    List<Announcement> getAnnListAll();

    Announcement getAnnInfo(@Param("annID")int annID);

    int updateAnnInfo(@Param("headline")String headline,@Param("content")String content,@Param("annTime")String annTime,@Param("annID")int annID);

    int delAnnInfo(@Param("annID")int annID);
}
