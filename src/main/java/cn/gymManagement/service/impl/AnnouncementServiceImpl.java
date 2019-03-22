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
     * @return
     */
    @Override
    public List<Announcement> getAnnListAll() {
        return announcementMapper.getAnnListAll();
    }
}
