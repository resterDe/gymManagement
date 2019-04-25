package cn.gymManagement.mapper;

import cn.gymManagement.pojo.Staff;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工信息表
 */
@Repository
public interface StaffMapper {

    /**
     * 获取全部员工信息
     * @param pages
     * @param limits
     * @return
     */
    @Select("select * from t_staff order by staffID desc limit #{pages},#{limits}")
    List<Staff> getStaffList(@Param("pages")int pages,@Param("limits")int limits);

    /**
     * 获取员工总条数
     * @return
     */
    @Select("select count(*) from t_staff")
    int getStaffNumber();

    /**
     * 关键字查询员工信息
     * @param keyword 关键字包括：员工姓名，员工编号
     * @return
     */
    @Select("select * from t_staff where concat(staffName,serialNumber) like concat('%',#{keyword},'%') order by staffID desc limit #{pages},#{limits}")
    List<Staff> getStaffListByKeyword(@Param("pages") int pages, @Param("limits") int limits,@Param("keyword")String keyword);

    /**
     * 获取模糊查询数据总数
     * @param keyword
     * @return
     */
    @Select("select count(*) from t_staff where concat(staffName,serialNumber) like concat('%',#{keyword},'%')")
    int getKeyStaffNumber(@Param("keyword")String keyword);

    /**
     * 根据员工id查询员工信息
     * 有连表查询需求
     * @param staffID 员工id
     * @return
     */
    @Select("select * from t_staff where staffID=#{staffID}")
    Staff getStaffById(int staffID);

    /**
     * 根据职称查询相应职务
     * @param position 职称（教练，管理员，经理等）
     * @return
     */
    @Select("select * from t_staff where position=#{position}")
    List<Staff> getStaffByPos(@Param("position")String position);

    /**
     * 根据员工id查询员工信息
     * @param staffID
     * @return
     */
    @Select("select * from t_staff where staffID=#{staffID}")
    Staff getStaffInfoById(@Param("staffID")int staffID);

    /**
     * 根据id删除员工
     * @param staffID
     * @return
     */
    @Delete("delete from t_staff where staffID=#{staffID}")
    int deleteStaffById(@Param("staffID")int staffID);

    /**
     * 修改员工信息
     * @param staffName 名字
     * @param newPassword 密码
     * @param address 地址
     * @param phone 联系方式
     * @param position 职称
     * @param salary 薪资
     * @param introduce 描述
     * @param staffID
     * @return
     */
    @Update("update t_staff set staffName=#{staffName},newPassword=#{newPassword}," +
            "address=#{address},phone=#{phone},position=#{position},salary=#{salary}," +
            "introduce=#{introduce} where staffID=#{staffID}")
    int updateStaffById(@Param("staffName")String staffName,@Param("newPassword")String newPassword,
                        @Param("address")String address,@Param("phone")String phone,
                        @Param("position")String position,@Param("salary")int salary,
                        @Param("introduce")String introduce,@Param("staffID")int staffID);

    /**
     * 根据身份证查询员工信息
     * 判断用户是否已经存在
     * @param identityCard
     * @return
     */
    @Select("select * from t_staff where identityCard=#{identityCard}")
    Staff getStaffBySer(@Param("identityCard")String identityCard);

    /**
     * 新增员工
     * @param staffName 姓名
     * @param age 年龄
     * @param gender 性别
     * @param identityCard 身份证号
     * @param newPassword 密码 默认为身份证后六位
     * @param address 地址
     * @param phone 电话
     * @param position 职称
     * @param jurisdiction 权限
     * @param serialNumber 员工编号
     * @param salary 薪资
     * @param introduce 描述
     * @param staffTime 注册时间
     * @return
     */
    @Insert("insert into t_staff (staffName,age,gender,identityCard,newPassword,address,phone,position," +
            "jurisdiction,serialNumber,salary,introduce,staffTime) values(#{staffName},#{age},#{gender}," +
            "#{identityCard},#{newPassword},#{address},#{phone},#{position},#{jurisdiction},#{serialNumber}," +
            "#{salary},#{introduce},#{staffTime})")
    int insertStaff(@Param("staffName")String staffName,@Param("age")int age,@Param("gender")String gender,
                    @Param("identityCard")String identityCard,@Param("newPassword")String newPassword,
                    @Param("address")String address,@Param("phone")String phone,@Param("position")String position,
                    @Param("jurisdiction")int jurisdiction,@Param("serialNumber")int serialNumber,
                    @Param("salary")int salary,@Param("introduce")String introduce,@Param("staffTime")String staffTime);
}
