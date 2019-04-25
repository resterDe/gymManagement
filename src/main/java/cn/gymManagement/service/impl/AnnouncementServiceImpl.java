package cn.gymManagement.service.impl;

import cn.gymManagement.mapper.AnnouncementMapper;
import cn.gymManagement.pojo.Announcement;
import cn.gymManagement.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;

    /**
     * 获取全部公告信息
     *
     * @return
     */
    @Override
    public List<Announcement> getAnnListAll() {
        return announcementMapper.getAnnListAll();
    }

    /**
     * 根据id查询所有公告信息
     *
     * @param annID
     * @return
     */
    @Override
    public Announcement getAnnInfo(int annID) {
        return announcementMapper.getAnnInfo(annID);
    }

    /**
     * 修改公告信息
     *
     * @param headline 标题
     * @param content  内容
     * @param annTime  发布时间
     * @return
     */
    @Override
    public int updateAnnInfo(String headline, String content, String annTime, int annID) {
        return announcementMapper.updateAnnInfo(headline, content, annTime, annID);
    }

    /**
     * 根据id删除公告
     *
     * @param annID
     * @return
     */
    @Override
    public int delAnnInfo(int annID) {
        return announcementMapper.delAnnInfo(annID);
    }
}
