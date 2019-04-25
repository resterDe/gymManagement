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
    //对应会员信息
    private User userInfo;
    //对应教程信息
    private Course courseInfo;

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

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public Course getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(Course courseInfo) {
        this.courseInfo = courseInfo;
    }

    @Override
    public String toString() {
        return "Forward{" +
                "forwardID=" + forwardID +
                ", courseID=" + courseID +
                ", userID=" + userID +
                ", forwardTime='" + forwardTime + '\'' +
                ", courseList=" + courseList +
                ", userInfo=" + userInfo +
                ", courseInfo=" + courseInfo +
                '}';
    }
}
