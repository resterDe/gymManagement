package cn.gymManagement.service.impl;

import cn.gymManagement.mapper.UserMapper;
import cn.gymManagement.pojo.User;
import cn.gymManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 获取全部会员信息
     * @return
     */
    @Override
    public List<User> getUserList(int pages,int limits) {
        return userMapper.getUserList(pages,limits);
    }

    /**
     * 获取会员总条数
     * @return
     */
    @Override
    public int getUserNumber() {
        return userMapper.getUserNumber();
    }

    /**
     * 根据id删除对应的会员
     * @param userID 唯一标识
     * @return
     */
    @Override
    public int deleteUserById(int userID) {
        return userMapper.deleteUserById(userID);
    }

    /**
     * 新增会员
     * @param userName 会员姓名
     * @param userAccount 会员账号
     * @param userPassword 会员密码
     * @param gender 性别
     * @param identityCard 身份证号
     * @param phone 电话
     * @param email 邮箱
     * @param activateCode 是否激活，0 表示未激活，1 表示已激活
     * @param regTime 注册时间
     * @param expireTime 到期时间
     * @return
     */
    @Override
    public int insertUser(String userName, String userAccount,
                          String userPassword, String gender,
                          String identityCard, String phone,
                          String email, int activateCode,
                          String regTime, String expireTime) {
        return userMapper.insertUser(userName, userAccount,
                userPassword, gender, identityCard, phone,
                email, activateCode, regTime, expireTime);
    }

    /**
     * 根据身份证号查询会员是否存在
     * @param identityCard
     * @return
     */
    @Override
    public User getUserIdCard(String identityCard) {
        return userMapper.getUserIdCard(identityCard);
    }

    /**
     * 根据会员账号，判断是否已经存在该账号
     * @param userAccount
     * @return
     */
    @Override
    public User getUserAccount(String userAccount) {
        return userMapper.getUserAccount(userAccount);
    }

    /**
     * 根据会员id查询会员信息，包括会员卡信息
     * @param userID
     * @return
     */
    @Override
    public User getUserInfoById(int userID) {
        return userMapper.getUserInfoById(userID);
    }

    /**
     * 修改会员相关信息
     * @param userName
     * @param userPassword
     * @param gender
     * @param identityCard
     * @param phone
     * @param email
     * @param activateCode
     * @param expireTime
     * @param userID
     * @return
     */
    @Override
    public int updateUserInfo(String userName, String userPassword,
                              String gender, String identityCard,
                              String phone, String email, int activateCode,
                              String expireTime, int userID) {
        return userMapper.updateUserInfo(userName, userPassword, gender,
                identityCard, phone, email, activateCode, expireTime, userID);
    }

    /**
     * 关键字查询会员信息
     * @param keyword 关键字包括：会员账户，会员姓名
     * @return
     */
    @Override
    public List<User> getUserListByKeyword(int pages, int limits, String keyword) {
        return userMapper.getUserListByKeyword(pages, limits, keyword);
    }

    /**
     * 获取模糊查询数据总数
     * @param keyword
     * @return
     */
    @Override
    public int getKeyUserNumber(String keyword) {
        return userMapper.getKeyUserNumber(keyword);
    }

    /**
     * 根据会员ID 查询会员信息
     * @param userID
     * @return
     */
    @Override
    public User getUsersById(int userID) {
        return userMapper.getUsersById(userID);
    }
}
