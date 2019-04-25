package cn.gymManagement.controller;

import cn.gymManagement.pojo.Staff;
import cn.gymManagement.service.StaffService;
import cn.gymManagement.utils.NumberUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    /**
     * 根据职称查询员工信息
     *
     * @param position 职称
     * @return
     */
    @RequestMapping(value = "getStaffBypos", method = RequestMethod.GET)
    @ResponseBody
    public List<Staff> getStaffBypos(String position) {
        return staffService.getStaffByPos(position);
    }

    /**
     * 获取员工全部信息
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "getStaffList", method = RequestMethod.GET, produces = {"text/html;charset=UTF-8;", "application/js"})
    @ResponseBody
    public String getStaffList(int page, int limit) {
        System.out.println("查询中");
        //添加到数据集合中
        int count = staffService.getStaffNumber();
        int limits = limit;
        int pages = (page - 1) * limit;
        List<Staff> staffList = staffService.getStaffList(pages, limits);
        //设置响应格式
        JSONObject object = new JSONObject();
        object.put("code", 0);
        object.put("msg", "");
        object.put("count", count);
        object.put("data", staffList);
        return object.toJSONString();
    }

    /**
     * 模糊查询，搜索员工信息；列表
     *
     * @param page
     * @param limit
     * @param keyword
     * @return
     */
    @RequestMapping(value = "getStaffListByKeyword", method = RequestMethod.GET, produces = {"text/html;charset=UTF-8;", "application/js"})
    @ResponseBody
    public String getStaffListByKeyword(int page, int limit, @RequestParam("keyword") String keyword) {
        System.out.println("关键字：" + keyword);
        //添加数据到集合中
        int myCount = staffService.getKeyStaffNumber(keyword);
        int myLimit = limit;
        int myPage = (page - 1) * limit;
        List<Staff> keyStaffList = staffService.getStaffListByKeyword(myPage, myLimit, keyword);
        //设置响应格式
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", myCount);
        obj.put("data", keyStaffList);
        return obj.toJSONString();
    }

    /**
     * 根据员工id查询信息
     *
     * @param staffID
     * @return
     */
    @RequestMapping(value = "getStaffInfoById", method = RequestMethod.GET)
    @ResponseBody
    public Staff getStaffInfoById(int staffID) {
        return staffService.getStaffInfoById(staffID);
    }

    /**
     * 根据id删除员工
     *
     * @param staffID
     * @return
     */
    @RequestMapping(value = "deleteStaffById", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteStaffById(int staffID) {
        int row = staffService.deleteStaffById(staffID);
        if (row == 1) {
            System.out.println("删除成功");
            return 0;
        } else {
            System.out.println("删除失败");
            return 1;
        }
    }

    /**
     * 修改员工信息
     * @return
     */
    @RequestMapping(value = "updateStaffInfo", method = RequestMethod.PUT)
    @ResponseBody
    public int updateStaffInfo(@RequestBody Staff staffs) {
        System.out.println("获取的对象：" + staffs);
        int row = staffService.updateStaffById(staffs.getStaffName(), staffs.getNewPassword(), staffs.getAddress(),
                staffs.getPhone(), staffs.getPosition(), staffs.getSalary(), staffs.getIntroduce(), staffs.getStaffID());
        if (row == 1) {
            System.out.println("修改成功");
            return 0;
        } else {
            System.out.println("修改失败");
            return 1;
        }
    }


    /**
     * 0 添加成功 1 添加失败，服务器问题 2 添加失败，已存在该员工
     * @param staffs
     * @return
     */
    @RequestMapping(value = "addStaff", method = RequestMethod.POST)
    @ResponseBody
    public int addStaff(@RequestBody Staff staffs) {
        //根据员工身份证号判断是否已经存在该用户
        Staff staffSer = staffService.getStaffBySer(staffs.getIdentityCard());
        if (staffSer == null) {
            //开始新增
            //获取随机三位数，合成会员信息
            System.out.println("获取到的员工基本信息："+staffs);
            //定义员工编号
            int serialNumber = Integer.parseInt(staffs.getSerialNumber()+ NumberUtil.getNumber());
            System.out.println("合成的员工编号："+serialNumber);

            //定义员工级别权限,默认为普通权限
            int jurisdiction=3;
            //获取职称确定员工权限
            if (staffs.getPosition().equals("经理")){
                jurisdiction=0;
                System.out.println("经理的权限是："+jurisdiction);
            }
            if (staffs.getPosition().equals("管理员")){
                jurisdiction=1;
                System.out.println("管理员的权限是："+jurisdiction);
            }
            if (staffs.getPosition().equals("教练")){
                jurisdiction=2;
                System.out.println("教练的权限是："+jurisdiction);
            }
            if (staffs.getPosition().equals("普通员工")){
                jurisdiction=3;
                System.out.println("普通员工的权限是："+jurisdiction);
            }
            //--------------获取身份证号，设置默认密码----------------------
            String IC=staffs.getIdentityCard();
            String newPassword=IC.substring(IC.length()-6);//取出最后六位

            //开始执行添加操作
            int row=staffService.insertStaff(staffs.getStaffName(),staffs.getAge(),staffs.getGender(),
                    staffs.getIdentityCard(),newPassword,staffs.getAddress(),staffs.getPhone(),
                    staffs.getPosition(),jurisdiction,serialNumber,staffs.getSalary(),
                    staffs.getIntroduce(),staffs.getStaffTime());

            //判断添加是否成功
            if (row==1){
                System.out.println("添加成功");
                return 0;
            }else {
                System.out.println("添加失败");
                return 1;
            }
        }else {
            System.out.println("已存在该员工");
            return 2;
        }

    }
}
