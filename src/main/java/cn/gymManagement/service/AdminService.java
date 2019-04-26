package cn.gymManagement.service;

import cn.gymManagement.pojo.Staff;
import org.apache.ibatis.annotations.Param;

public interface AdminService {
    Staff adminLogin(@Param("serialNumber") int serialNumber, @Param("newPassword") String newPassword);

    int updateAdminPwd(@Param("staffID") int staffID, @Param("newPassword") String newPassword);
}
