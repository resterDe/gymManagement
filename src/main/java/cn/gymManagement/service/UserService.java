package cn.gymManagement.service;

import cn.gymManagement.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    User userLogin(@Param("userAccount") String userAccount, @Param("userPassword") String userPassword);

    List<User> getUserList(@Param("pages") int pages, @Param("limits") int limits);

    int getUserNumber();

    int deleteUserById(@Param("userID") int userID);

    int insertUser(@Param("userName") String userName, @Param("userAccount") String userAccount,
                   @Param("userPassword") String userPassword, @Param("gender") String gender,
                   @Param("identityCard") String identityCard, @Param("phone") String phone,
                   @Param("email") String email, @Param("activateCode") int activateCode,
                   @Param("regTime") String regTime, @Param("expireTime") String expireTime);

    User getUserIdCard(@Param("identityCard") String identityCard);

    User getUserAccount(@Param("userAccount") String userAccount);

    User getUserInfoById(@Param("userID") int userID);

    int updateUserInfo(@Param("userName") String userName, @Param("userPassword") String userPassword,
                       @Param("gender") String gender, @Param("identityCard") String identityCard,
                       @Param("phone") String phone, @Param("email") String email,
                       @Param("activateCode") int activateCode, @Param("expireTime") String expireTime, @Param("userID") int userID);

    List<User> getUserListByKeyword(@Param("pages") int pages, @Param("limits") int limits, @Param("keyword") String keyword);

    int getKeyUserNumber(@Param("keyword") String keyword);

    User getUsersById(@Param("userID") int userID);
}
