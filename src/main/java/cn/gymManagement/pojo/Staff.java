package cn.gymManagement.pojo;

import org.springframework.stereotype.Component;

/**
 * 员工信息
 */
@Component
public class Staff {
    private int staffID;
    //员工姓名
    private String staffName;
    //年龄
    private int age;
    //性别
    private String gender;
    //身份证号（身份证号码后六位作为登录密码）
    private String identityCard;
    //修改后的密码
    private String newPassword;
    //家庭住址
    private String address;
    //联系方式
    private String phone;
    //职称（经理，教练，管理员）
    private String position;
    //级别权限(0：表示最高权限，1：表示普通管理员 2：教练)
    private int jurisdiction;
    //员工编号(入职年份+随机三位数)
    private int  serialNumber;
    //员工薪资
    private int salary;
    //员工介绍
    private String introduce;
    //注册时间
    private String staffTime;

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(int jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getStaffTime() {
        return staffTime;
    }

    public void setStaffTime(String staffTime) {
        this.staffTime = staffTime;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffID=" + staffID +
                ", staffName='" + staffName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", position='" + position + '\'' +
                ", jurisdiction=" + jurisdiction +
                ", serialNumber=" + serialNumber +
                ", salary=" + salary +
                ", introduce='" + introduce + '\'' +
                ", staffTime='" + staffTime + '\'' +
                '}';
    }
}
