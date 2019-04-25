package cn.gymManagement.pojo;

import org.springframework.stereotype.Component;

/**
 * 会员信息
 * */
@Component
public class User {
    private int userID;
    //会员姓名
    private String userName;
    //会员账号
    private String userAccount;
    //会员密码
    private String userPassword;
    //性别
    private String gender;
    //身份证号
    private String identityCard;
    //联系方式
    private String phone;
    //电子邮箱
    private String email;
    //是否激活会员 0：未激活   1：已激活
    private int activateCode;
    //注册时间
    private String regTime;
    //到期时间
    private String expireTime;
    //用于记录会员卡名称
    private String rankName;
    //用于记录会员卡信息
    private Card cards;


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getActivateCode() {
        return activateCode;
    }

    public void setActivateCode(int activateCode) {
        this.activateCode = activateCode;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public Card getCards() {
        return cards;
    }

    public void setCards(Card cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", gender='" + gender + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", activateCode=" + activateCode +
                ", regTime='" + regTime + '\'' +
                ", expireTime='" + expireTime + '\'' +
                ", rankName='" + rankName + '\'' +
                ", cards=" + cards +
                '}';
    }
}
