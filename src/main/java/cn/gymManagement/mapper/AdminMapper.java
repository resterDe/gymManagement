package cn.gymManagement.mapper;

import cn.gymManagement.pojo.Staff;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 后台管理admin
 */
@Repository
public interface AdminMapper {
    /**
     * 员工（管理员）登录
     * @param seriaNumber
     * @param newPassword
     * @return
     */
    @Select("select * from t_staff where seriaNumber=#{seriaNumber} and newPassword=#{newPassword}")
    Staff adminLogin(@Param("seriaNumber")int seriaNumber,@Param("newPassword")String newPassword);

    /**
     * 修改管理员密码
     * @param staffID 管理员id
     * @param newPassword 新密码
     * @return
     */
    @Update("update t_staff set newPassword=#{newPassword} where staffID=#{staffID}")
    int updateAdminPwd(@Param("staffID")int staffID,@Param("newPassword")String newPassword);
}
