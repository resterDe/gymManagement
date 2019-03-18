package cn.gymManagement.mapper;

import cn.gymManagement.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    /**
     * 会员登录
     * @param userAccount 账号
     * @param userPassword 密码，初始密码为身份证后六位
     * @return
     */
    @Select("select * from t_user where userAccount=#{userAccount} and userPassword=#{userPassword}")
    User userLogin(@Param("userAccount")String userAccount,@Param("userPassword")String userPassword);
}
