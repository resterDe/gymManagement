package cn.gymManagement.service;

import cn.gymManagement.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    User userLogin(@Param("userAccount")String userAccount, @Param("userPassword")String userPassword);
}
