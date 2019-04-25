package cn.gymManagement.pojo;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 教程信息
 */
@Component
public class Course {
    private int courseID;
    //教程名称
    private String courseName;
    //持续时间段（xxxx.xx.xx-xxxx.xx.xx）
    private String courseTime;
    //教练信息t_staff
    private int staffID;
    //训练地点
    private String site;
    //教程介绍
    private String introduce;
    //预约上线人数
    private int maxNumber;
    //已预约人数
    private int reservationNumber;
    //课程绑定教练信息
    private Staff staffs;

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Staff getStaffs() {
        return staffs;
    }

    public void setStaffs(Staff staffs) {
        this.staffs = staffs;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", courseTime='" + courseTime + '\'' +
                ", staffID=" + staffID +
                ", site='" + site + '\'' +
                ", introduce='" + introduce + '\'' +
                ", maxNumber=" + maxNumber +
                ", reservationNumber=" + reservationNumber +
                ", staffs=" + staffs +
                '}';
    }
}
