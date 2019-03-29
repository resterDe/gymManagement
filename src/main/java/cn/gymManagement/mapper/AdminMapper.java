package cn.gymManagement.mapper;

import cn.gymManagement.pojo.Staff;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
}
