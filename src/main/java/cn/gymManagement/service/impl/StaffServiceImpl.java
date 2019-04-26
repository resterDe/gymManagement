package cn.gymManagement.service.impl;

import cn.gymManagement.mapper.StaffMapper;
import cn.gymManagement.pojo.Staff;
import cn.gymManagement.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工信息
 */
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffMapper staffMapper;

    /**
     * 根据职称查询相应职务
     *
     * @param position 职称（教练，管理员，经理等）
     * @return
     */
    @Override
    public List<Staff> getStaffByPos(String position) {
        return staffMapper.getStaffByPos(position);
    }

    /**
     * 获取全部员工信息
     *
     * @param pages
     * @param limits
     * @return
     */
    @Override
    public List<Staff> getStaffList(int pages, int limits) {
        return staffMapper.getStaffList(pages, limits);
    }

    /**
     * 获取员工总条数
     *
     * @return
     */
    @Override
    public int getStaffNumber() {
        return staffMapper.getStaffNumber();
    }

    /**
     * 关键字查询会员信息
     *
     * @param keyword 关键字包括：会员账户，会员姓名
     * @return
     */
    @Override
    public List<Staff> getStaffListByKeyword(int pages, int limits, String keyword) {
        return staffMapper.getStaffListByKeyword(pages, limits, keyword);
    }

    /**
     * 获取模糊查询数据总数
     *
     * @param keyword
     * @return
     */
    @Override
    public int getKeyStaffNumber(String keyword) {
        return staffMapper.getKeyStaffNumber(keyword);
    }

    /**
     * 根据id查询员工信息
     *
     * @param staffID
     * @return
     */
    @Override
    public Staff getStaffInfoById(int staffID) {
        return staffMapper.getStaffInfoById(staffID);
    }

    /**
     * 根据id删除员工
     *
     * @param staffID
     * @return
     */
    @Override
    public int deleteStaffById(int staffID) {
        return staffMapper.deleteStaffById(staffID);
    }

    /**
     * 修改员工信息
     *
     * @param staffName   名字
     * @param newPassword 密码
     * @param address     地址
     * @param phone       联系方式
     * @param position    职称
     * @param salary      薪资
     * @param introduce   描述
     * @param staffID
     * @return
     */
    @Override
    public int updateStaffById(String staffName, String newPassword,
                               String address, String phone, String position,
                               int salary, String introduce, int staffID) {
        return staffMapper.updateStaffById(staffName, newPassword, address, phone,
                position, salary, introduce, staffID);
    }

    /**
     * 根据员工编号查询员工信息
     * 判断用户是否已经存在
     *
     * @param identityCard
     * @return
     */
    @Override
    public Staff getStaffBySer(String identityCard) {
        return staffMapper.getStaffBySer(identityCard);
    }

    /**
     * 新增员工
     *
     * @param staffName    姓名
     * @param age          年龄
     * @param gender       性别
     * @param identityCard 身份证号
     * @param newPassword  密码 默认为身份证后六位
     * @param address      地址
     * @param phone        电话
     * @param position     职称
     * @param jurisdiction 权限
     * @param serialNumber 员工编号
     * @param salary       薪资
     * @param introduce    描述
     * @param staffTime    注册时间
     * @return
     */
    @Override
    public int insertStaff(String staffName, int age, String gender, String identityCard,
                           String newPassword, String address, String phone, String position,
                           int jurisdiction, int serialNumber, int salary, String introduce,
                           String staffTime) {
        return staffMapper.insertStaff(staffName, age, gender, identityCard,
                newPassword, address, phone, position, jurisdiction, serialNumber,
                salary, introduce, staffTime);
    }
}
