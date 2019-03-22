package cn.gymManagement.pojo;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 预约信息
 */
@Component
public class Forward {
    private int forwardID;
    //教程信息 t_course
    private int courseID;
    //会员信息 t_user
    private int userID;
    //预约时间
    private String forwardTime;
    //查询预约课程信息
    private List<Course> courseList;

    public int getForwardID() {
        return forwardID;
    }

    public void setForwardID(int forwardID) {
        this.forwardID = forwardID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getForwardTime() {
        return forwardTime;
    }

    public void setForwardTime(String forwardTime) {
        this.forwardTime = forwardTime;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "Forward{" +
                "forwardID=" + forwardID +
                ", courseID=" + courseID +
                ", userID=" + userID +
                ", forwardTime='" + forwardTime + '\'' +
                ", courseList=" + courseList +
                '}';
    }
}
