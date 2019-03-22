package cn.gymManagement.controller;

import cn.gymManagement.pojo.Announcement;
import cn.gymManagement.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @RequestMapping(value = "getAnnListAll",method = RequestMethod.GET)
    @ResponseBody
    public List<Announcement> getAnnListAll(){
        System.out.println("查询全部公告信息");
        return announcementService.getAnnListAll();
    }
}
