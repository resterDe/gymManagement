package cn.gymManagement.service.impl;

import cn.gymManagement.mapper.UserMapper;
import cn.gymManagement.pojo.User;
import cn.gymManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * @param userAccount 账号
     * @param userPassword 密码，初始密码为身份证号后六位
     * @return
     */
    @Override
    public User userLogin(String userAccount, String userPassword) {
        return userMapper.userLogin(userAccount, userPassword);
    }
}
