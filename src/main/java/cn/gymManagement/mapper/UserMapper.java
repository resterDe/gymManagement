package cn.gymManagement.mapper;

import cn.gymManagement.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 会员信息
 */
@Repository
public interface UserMapper {
    /**
     * 会员登录
     *
     * @param userAccount  账号
     * @param userPassword 密码，初始密码为身份证后六位
     * @return
     */
    @Select("select * from t_user where userAccount=#{userAccount} and userPassword=#{userPassword}")
    User userLogin(@Param("userAccount") String userAccount, @Param("userPassword") String userPassword);

    /**
     * 获取全部会员信息
     *
     * @return
     */
    @Select("select * from t_user order by userID desc limit #{pages},#{limits}")
    List<User> getUserList(@Param("pages") int pages, @Param("limits") int limits);

    /**
     * 关键字查询会员信息
     * @param keyword 关键字包括：会员账户，会员姓名
     * @return
     */
    @Select("select * from t_user where concat(userAccount,userName) like concat('%',#{keyword},'%') order by userID desc limit #{pages},#{limits}")
    List<User> getUserListByKeyword(@Param("pages") int pages, @Param("limits") int limits,@Param("keyword")String keyword);

    /**
     * 获取会员总条数
     *
     * @return
     */
    @Select("select count(*) from t_user")
    int getUserNumber();

    /**
     * 获取模糊查询数据总数
     * @param keyword
     * @return
     */
    @Select("select count(*) from t_user where concat(userAccount,userName) like concat('%',#{keyword},'%')")
    int getKeyUserNumber(@Param("keyword")String keyword);

    /**
     * 根据id删除对应的会员
     * @param userID 唯一标识
     * @return
     */
    @Delete("delete from t_user where userID=#{userID}")
    int deleteUserById(@Param("userID")int userID);

    /**
     * 新增会员
     * 默认不激活 0
     * 并且不包括到期日期
     * @param userName 会员姓名
     * @param userAccount 会员账号
     * @param userPassword 会员密码
     * @param gender 性别
     * @param identityCard 身份证号
     * @param phone 电话
     * @param email 邮箱
     * @param activateCode 是否激活，0 表示未激活，1 表示已激活
     * @param regTime 注册时间
     * @param regTime 到期时间
     * @return
     */
    @Insert("insert into t_user (userName,userAccount,userPassword,gender," +
            "identityCard,phone,email,activateCode,regTime,expireTime) " +
            "values(#{userName},#{userAccount},#{userPassword}," +
            "#{gender},#{identityCard},#{phone},#{email}," +
            "#{activateCode},#{regTime},#{expireTime})")
    int insertUser(@Param("userName")String userName,@Param("userAccount")String userAccount,
                   @Param("userPassword")String userPassword,@Param("gender")String gender,
                   @Param("identityCard")String identityCard,@Param("phone")String phone,
                   @Param("email")String email,@Param("activateCode")int activateCode,
                   @Param("regTime")String regTime,@Param("expireTime")String expireTime);

    /**
     * 根据会员唯一身份证号码
     * 查询会员是否存在
     *
     * 查询会员获取会员ID
     * @param identityCard
     * @return
     */
    @Select("select * from t_user where identityCard=#{identityCard}")
    User getUserIdCard(@Param("identityCard")String identityCard);

    /**
     * 根据会员账号，判断是否已经存在该账号
     * @param userAccount
     * @return
     */
    @Select("select * from t_user userAccount=#{userAccount}")
    User getUserAccount(@Param("userAccount")String userAccount);

    /**
     * 根据会员ID查询会员相关信息
     * 联系会员卡信息表
     * @param userID
     * @return
     */
    @Select("select * from t_user where userID=#{userID}")
    @Results({
            @Result(id = true, column = "userID", property = "userID"),
            @Result(column = "userID", property = "cards", many = @Many(select = "cn.gymManagement.mapper.CardMapper.getCardById"))
    })
    User getUserInfoById(@Param("userID")int userID);

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
    @Update("update t_user set userName=#{userName}," +
            "userPassword=#{userPassword},gender=#{gender}," +
            "identityCard=#{identityCard},phone=#{phone},email=#{email}," +
            "activateCode=#{activateCode},expireTime=#{expireTime} where userID=#{userID}")
    int updateUserInfo(@Param("userName")String userName,@Param("userPassword")String userPassword,
                       @Param("gender")String gender,@Param("identityCard")String identityCard,
                       @Param("phone")String phone,@Param("email")String email,
                       @Param("activateCode")int activateCode,@Param("expireTime")String expireTime,@Param("userID")int userID);



}
