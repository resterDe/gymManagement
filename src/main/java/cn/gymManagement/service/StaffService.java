package cn.gymManagement.service;

import cn.gymManagement.pojo.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffService {
    List<Staff> getStaffByPos(@Param("position") String position);

    List<Staff> getStaffList(@Param("pages") int pages, @Param("limits") int limits);

    int getStaffNumber();

    List<Staff> getStaffListByKeyword(@Param("pages") int pages, @Param("limits") int limits, @Param("keyword") String keyword);

    int getKeyStaffNumber(@Param("keyword") String keyword);

    Staff getStaffInfoById(@Param("staffID") int staffID);

    int deleteStaffById(@Param("staffID") int staffID);

    int updateStaffById(@Param("staffName") String staffName, @Param("newPassword") String newPassword,
                        @Param("address") String address, @Param("phone") String phone,
                        @Param("position") String position, @Param("salary") int salary,
                        @Param("introduce") String introduce, @Param("staffID") int staffID);

    Staff getStaffBySer(@Param("identityCard") String identityCard);

    int insertStaff(@Param("staffName") String staffName, @Param("age") int age, @Param("gender") String gender,
                    @Param("identityCard") String identityCard, @Param("newPassword") String newPassword,
                    @Param("address") String address, @Param("phone") String phone, @Param("position") String position,
                    @Param("jurisdiction") int jurisdiction, @Param("serialNumber") int serialNumber,
                    @Param("salary") int salary, @Param("introduce") String introduce, @Param("staffTime") String staffTime);
}
