package cn.gymManagement.pojo;

import org.springframework.stereotype.Component;

/**
 * 公告信息
 */
@Component
public class Announcement {
    private int annID;
    //标题
    private String headline;
    //内容
    private String content;
    //发布时间
    private String annTime;

    public int getAnnID() {
        return annID;
    }

    public void setAnnID(int annID) {
        this.annID = annID;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnnTime() {
        return annTime;
    }

    public void setAnnTime(String annTime) {
        this.annTime = annTime;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "annID=" + annID +
                ", headline='" + headline + '\'' +
                ", content='" + content + '\'' +
                ", annTime='" + annTime + '\'' +
                '}';
    }
}
