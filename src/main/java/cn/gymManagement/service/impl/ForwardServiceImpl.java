package cn.gymManagement.service.impl;

import cn.gymManagement.mapper.ForwardMapper;
import cn.gymManagement.pojo.Forward;
import cn.gymManagement.service.ForwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForwardServiceImpl implements ForwardService {
    @Autowired
    private ForwardMapper forwardMapper;

    /**
     * 新增预约
     * @param courseID
     * @param userID
     * @param forwardTime
     * @return
     */
    @Override
    public int addUserCourse(int courseID, int userID, String forwardTime) {
        return forwardMapper.addUserCourse(courseID, userID, forwardTime);
    }

    /**
     * 查询会员预约课程信息
     * @param userID
     * @return
     */
    @Override
    public List<Forward> getUserForward(int userID) {
        return forwardMapper.getUserForward(userID);
    }
}
