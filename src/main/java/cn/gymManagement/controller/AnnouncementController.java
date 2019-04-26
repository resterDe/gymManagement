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

    /**
     * 获取全部公告信息
     *
     * @return
     */
    @RequestMapping(value = "getAnnListAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Announcement> getAnnListAll() {
        System.out.println("查询全部公告信息");
        return announcementService.getAnnListAll();
    }

    /**
     * 根据id查询所有公告信息
     *
     * @param annID
     * @return
     */
    @RequestMapping(value = "getAnnById", method = RequestMethod.GET)
    @ResponseBody
    public Announcement getAnnById(int annID) {
        return announcementService.getAnnInfo(annID);
    }

    /**
     * 修改公告信息
     *
     * @param headline 标题
     * @param content  内容
     * @param annTime  发布时间
     *                 0表示修改成功 1表示修改失败
     * @return
     */
    @RequestMapping(value = "updateAnnInfo", method = RequestMethod.PUT)
    @ResponseBody
    public int updateAnnInfo(String headline, String content, String annTime, int annID) {
        System.out.println(annTime);
        int row = announcementService.updateAnnInfo(headline, content, annTime, annID);
        if (row == 1) {
            System.out.println("修改成功");
            return 0;
        } else {
            System.out.println("修改失败");
            return 1;
        }
    }

    /**
     * 根据id删除公告
     * 0表示修改成功 1表示修改失败
     *
     * @param annID
     * @return
     */
    @RequestMapping(value = "delAnnInfo", method = RequestMethod.DELETE)
    @ResponseBody
    public int delAnnInfo(int annID) {
        int row = announcementService.delAnnInfo(annID);
        if (row == 1) {
            System.out.println("删除成功");
            return 0;
        } else {
            System.out.println("删除失败");
            return 1;
        }
    }

    /**
     * 新增公告
     *
     * @param headline 标题
     * @param content  内容
     * @param annTime  发布时间
     *                 0表示成功  1表示失败
     * @return
     */
    @RequestMapping(value = "addAnn", method = RequestMethod.POST)
    @ResponseBody
    public int addAnn(String headline, String content, String annTime) {
        int row = announcementService.insertAnn(headline, content, annTime);
        if (row == 1) {
            System.out.println("新增公告成功");
            return 0;
        } else {
            System.out.println("新增公告失败");
            return 1;
        }
    }
}
