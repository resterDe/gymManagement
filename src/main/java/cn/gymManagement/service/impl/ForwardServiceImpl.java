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
     *
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
     *
     * @param userID
     * @return
     */
    @Override
    public List<Forward> getUserForward(int userID) {
        return forwardMapper.getUserForward(userID);
    }

    /**
     * 1、查询会员是否已经预约过此课程
     * 2、查询会员已预约的课程
     *
     * @param userID 会员唯一标识
     * @return
     */
    @Override
    public List<Forward> selectUserCourse(int userID) {
        return forwardMapper.selectUserCourse(userID);
    }

    /**
     * 根据教程id删除相应教程
     *
     * @param forwardID
     * @return
     */
    @Override
    public int delUserForward(int forwardID) {
        return forwardMapper.delUserForward(forwardID);
    }

    /**
     * 查询所有预约教程信息
     *
     * @return
     */
    @Override
    public List<Forward> getForward() {
        return forwardMapper.getForward();
    }

    /**
     * 根据id查询单个预约详细信息
     *
     * @param forwardID
     * @return
     */
    @Override
    public Forward getForwardById(int forwardID) {
        return forwardMapper.getForwardById(forwardID);
    }
}
