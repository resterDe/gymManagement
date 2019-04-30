package cn.gymManagement.controller;

import cn.gymManagement.pojo.Card;
import cn.gymManagement.pojo.User;
import cn.gymManagement.service.CardService;
import cn.gymManagement.service.UserService;
import cn.gymManagement.utils.NumberUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    /**
     * 会员接口,注册bean
     */
    @Autowired
    private UserService userService;
    /**
     * 会员卡接口
     */
    @Autowired
    private CardService cardService;

    /**
     * 会员登录
     *
     * @param session      session域
     * @param userAccount  账号
     * @param userPassword 密码
     * @return
     */
    @RequestMapping(value = "userLogin")
    @ResponseBody
    public int userLogin(HttpSession session, String userAccount, String userPassword, String code) {
        //验证码验证
        String myCode = ((String) session.getAttribute("code")).toLowerCase();
        System.out.println("获取的验证码：" + code);
        System.out.println("正确验证码：" + myCode);
        //获取验证码
        if (code.equals(myCode)) {
            //执行登录
            User users = userService.userLogin(userAccount, userPassword);
            //若存在用户，则打印用户信息
            System.out.println("是否存在：" + users);
            //若用户密码错误，则返回1
            if (users == null) {
                System.out.println("对不起，您的账号或密码错误");
                return 0;
            } else {
                System.out.println("登录成功");
                //将用户信息存入session中
                session.setAttribute("userSession", users);
                return 1;
            }
        } else {
            System.out.println("验证码错误：" + myCode + ":" + code);
            return 2;
        }
    }

    /**
     * 检查用户是否登录
     * 登录：1 未登录：0
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "examineLogin", method = RequestMethod.GET)
    @ResponseBody
    public int examineLogin(HttpSession session) {
        if (session.getAttribute("userSession") == null) {
            System.out.println("用户未登录");
            return 0;
        } else {
            System.out.println("用户已登录");
            return 1;
        }
    }

    /**
     * 用户退出登录，注销用户
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "userQuit", method = RequestMethod.GET)
    @ResponseBody
    public int userQuit(HttpSession session) {
        if (session.getAttribute("userSession") == null) {
            System.out.println("用户不存在,请前往登录");
            return 0;
        } else {
            System.out.println("退出成功");
            //删除指定对象中的所有信息
            session.removeAttribute("userSession");
            //清楚session中所有信息，注销用户，使session失效
            session.invalidate();
            return 1;
        }
    }

    /**
     * 获取会员全部信息
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "getUserList", method = RequestMethod.GET, produces = {"text/html;charset=UTF-8;", "application/js"})
    @ResponseBody
    public String getUserList(int page, int limit) {
        System.out.println("获取：" + page + "：" + limit);
        //添加到数据集合中
        int count = userService.getUserNumber();
        int limits = limit;
        int pages = (page - 1) * limit;
        List<User> userList = userService.getUserList(pages, limits);
        //设置相应格式
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", count);
        obj.put("data", userList);
        return obj.toJSONString();
    }

    /**
     * 关键字获取会员信息
     *
     * @param keyword 关键字包括：会员账户，会员姓名
     * @return
     */
    @RequestMapping(value = "getUserListByKeyword", method = RequestMethod.GET, produces = {"text/html;charset=UTF-8;", "application/js"})
    @ResponseBody
    public String getUserListByKeyword(int page, int limit, @RequestParam("keyword") String keyword) {
        System.out.println("获取的keyword=" + keyword);
        //添加数据到集合中
        int myCount = userService.getKeyUserNumber(keyword);
        int myLimit = limit;
        int myPage = (page - 1) * limit;
        List<User> keyUserList = userService.getUserListByKeyword(myPage, myLimit, keyword);
        //设置响应格式
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", myCount);
        obj.put("data", keyUserList);
        return obj.toJSONString();
    }

    /**
     * 根据会员ID删除会员
     * 0：表示删除会员、会员卡信息成功  1：删除会员失败  2：删除会员成功，删除会员卡信息失败
     *
     * @param userID 唯一标识
     * @return
     */
    @RequestMapping(value = "deleteUserById", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteUserById(int userID) {
        System.out.println("需要删除的会员：" + userID);
        int row = userService.deleteUserById(userID);
        if (row == 1) {
            System.out.println("删除会员成功");
            //删除会员信息后同步删除会员卡信息
            int code = cardService.delCardByUserId(userID);
            if (code == 1) {
                System.out.println("会员卡信息删除成功");
                return 0;
            } else {
                System.out.println("会员卡信息删除失败");
                return 2;
            }
        } else {
            System.out.println("删除会员失败");
            return 1;
        }
    }

    /**
     * 新增会员
     * 0表示成功 1表示失败 2表示已存在会员 3会员卡级别添加失败
     *
     * @param users 页面会员基本信息
     * @return
     */
    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    @ResponseBody
    public int saveUser(@RequestBody User users) {
        //查询是否已存在该会员
        String userAccount;

        //会员级别名称
        String rankName = users.getRankName();
        User userIdCards = userService.getUserIdCard(users.getIdentityCard());
        if (userIdCards == null) {
            //---开始新增---
            //获取随机不重复三位id,合成会员账号
            System.out.println("获取到的会员基本信息：" + users);
            userAccount = users.getUserAccount() + NumberUtil.getNumber();
            System.out.println("合成的会员账号：" + userAccount);
            //获取会员卡登记,0表示不激活，1表示激活，默认为1,到期时间为空
            int row = userService.insertUser(users.getUserName(),
                    userAccount, users.getUserPassword(), users.getGender(),
                    users.getIdentityCard(), users.getPhone(), users.getEmail(),
                    1, users.getRegTime(), users.getExpireTime());
            if (row == 1) {
                //会员卡有效时间
                String validTime = null;
                System.out.println("新增空户成功,未激活：" + row);
                //添加会员成功，获取会员卡有效时间以及名称
                if ("铜牌会员".equals(rankName)) {
                    validTime = "三个月";
                }
                if ("银牌会员".equals(rankName)) {
                    validTime = "半年";
                }
                if ("金牌会员".equals(rankName)) {
                    validTime = "一年";
                } else {
                    System.out.println("假会员");
                }
                //获取会员ID
                int userID = (userService.getUserIdCard(users.getIdentityCard())).getUserID();
                //添加会员卡级别
                int code = cardService.addUserCard(userID, rankName, validTime);
                if (code == 1) {
                    System.out.println("会员卡级别添加成功");
                    return 0;
                } else {
                    System.out.println("会员卡级别添加失败");
                    return 3;
                }
            } else {
                System.out.println("新增失败：" + row);
                return 1;
            }
        } else {
            System.out.println("已存在该会员，请确认身份信息再添加");
            return 2;
        }
    }

    /**
     * 根据会员ID查询会员详细信息，包括会员卡信息
     *
     * @param userID
     * @return
     */
    @RequestMapping(value = "getUserInfoById", method = RequestMethod.GET)
    @ResponseBody
    public User getUserInfoById(int userID) {
        return userService.getUserInfoById(userID);
    }

    /**
     * 修改会员信息
     * 0：表示会员、会员卡更新成功，1：更新失败，2：会员信息更新成功，会员卡信息更新失败
     *
     * @param users 会员相关信息
     * @return
     */
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.PUT)
    @ResponseBody
    public int updateUserInfo(@RequestBody User users) {
        //定义会员卡有效期
        String validTime = null;
        System.out.println("获取的对象数据：" + users);
        //执行对会员信息修改动作
        int row = userService.updateUserInfo(users.getUserName(), users.getUserPassword(),
                users.getGender(), users.getIdentityCard(), users.getPhone(), users.getEmail(),
                users.getActivateCode(), users.getExpireTime(), users.getUserID());
        if (row == 1) {
            System.out.println("修改会员信息成功");
            //修改完会员信息，更新会员对应会员卡信息
            //对会员卡信息进行判断
            if (users.getRankName().equals("铜牌会员")) {
                validTime = "三个月";
            }
            if (users.getRankName().equals("银牌会员")) {
                validTime = "半年";
            }
            if (users.getRankName().equals("金牌会员")) {
                validTime = "一年";
            }
            //执行修改
            int code = cardService.updateCardByUserId(users.getRankName(), validTime, users.getUserID());
            if (code == 1) {
                System.out.println("会员、会员卡信息更新完成");
                return 0;
            } else {
                System.out.println("会员卡信息更新失败");
                return 2;
            }
        } else {
            System.out.println("修改会员信息失败");
            return 1;
        }
    }

    /**
     * 会员修改密码 依据身份证号
     *
     * @param identityCard 身份证号码
     * @param newPassword  新密码
     * @return
     */
    @RequestMapping(value = "updatePwd", method = RequestMethod.POST)
    @ResponseBody
    public int updatePwd(String identityCard, String newPassword) {
        System.out.println("获取：" + identityCard + ":" + newPassword);
        //判断是否存在该会员
        User users=userService.getUserIdCard(identityCard);
        System.out.println("会员信息：" + users);
        if (users == null) {
            System.out.println("不存在该会员");
            return 2;
        } else {
            //开始修改
            int row = userService.updatePwd(newPassword,identityCard);
            if (row == 1) {
                System.out.println("修改成功");
                return 0;
            } else {
                System.out.println("修改失败");
                return 1;
            }
        }
    }
}
