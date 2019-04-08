package cn.gymManagement.service.impl;

import cn.gymManagement.mapper.AdminMapper;
import cn.gymManagement.mapper.StaffMapper;
import cn.gymManagement.pojo.Staff;
import cn.gymManagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 员工（管理员）登录
     * @param seriaNumber 员工编号
     * @param newPassword 密码（初始密码为身份证号码后六位，后期可修改）
     * @return
     */
    @Override
    public Staff adminLogin(int seriaNumber, String newPassword) {
        return adminMapper.adminLogin(seriaNumber, newPassword);
    }

    /**
     * 修改管理员密码
     * @param staffID 管理员id
     * @param newPassword 新密码
     * @return
     */
    @Override
    public int updateAdminPwd(int staffID, String newPassword) {
        return adminMapper.updateAdminPwd(staffID, newPassword);
    }
}
