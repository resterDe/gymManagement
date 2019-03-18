package cn.gymManagement.pojo;

import org.springframework.stereotype.Component;

/**
 * 会员卡信息
 */
@Component
public class Card {
    private int cardID;
    //会员信息
    private int userID;
    //会员卡级别
    private String rankName;
    //会员卡有效期(如：一个月 三个月 半年 一年等)
    private String validTime;

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardID=" + cardID +
                ", userID=" + userID +
                ", rankName='" + rankName + '\'' +
                ", validTime='" + validTime + '\'' +
                '}';
    }
}
