package cn.gymManagement.mapper;

import cn.gymManagement.pojo.Staff;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工信息表
 */
@Repository
public interface StaffMapper {

    /**
     * 根据员工id查询员工信息
     * 有连表查询需求
     * @param staffID 员工id
     * @return
     */
    @Select("select * from t_staff where staffID=#{staffID}")
    List<Staff> getStaffById(int staffID);
}
