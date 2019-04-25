//添加员工
function addStaff() {
    console.log("执行了添加");
    $.ajax({
        url: "/staff/addStaff",
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            "staffName": $("#staff_Name").val(),
            "serialNumber": $("#serialNumber").val(),
            "salary": $("#staffSalary").val(),
            "identityCard": $("#identityCard").val(),
            "phone": $("#phone").val(),
            "address":$("#staffAddress").val(),
            "age":$("#age").val(),
            "position": $("#staffPosition").find("option:selected").val(),
            "gender": $("input[name='gender']:checked").val(),
            "introduce": $("#staffIntroduce").val(),
            "staffTime": $("#date").val(),
        })
        ,
        success(data) {
            console.log("成功返回值：" + data);
            switch (data) {
                case 0:
                    alert("新增员工成功");
                    location.href = "staffInfoList.html";
                    break;
                case 1:
                    alert("新增员工失败");
                    window.location.reload();
                    break;
                case 2:
                    alert("已存在该员工");
                    window.location.reload();
                    break;
                default:
                    console.log("验证码返回错误");
            }
        },
        error() {
            console.log("网络出错");
            alert("网络出错");
        }
    });
};
//查询会员信息
function queryStaff(staffID) {
    $.ajax({
        url: "/staff/getStaffInfoById",
        type: "GET",
        data: {
            "staffID": staffID
        },
        success(data) {
            console.log("查询成功：" + data);
            //将数据传入layui模板
            onQueryStaff(data);
        },
        error() {
            console.log("网络出错");
        }
    })
};
//查询会员信息修改
function queryStaffUp(staffID) {
    $.ajax({
        url: "/staff/getStaffInfoById",
        type: "GET",
        data: {
            "staffID": staffID
        },
        success(data) {
            console.log("查询修改员工信息：" + data.staffName);
            onUpdateStaffInfo(data);
        },
        error() {
            console.log("失败");
        }
    })
};
<!--编辑，删除，增加请求ajax-->
//删除操作
function delStaff(staffID) {
    $.ajax({
        url: "/staff/deleteStaffById",
        type: "POST",
        data: {
            _method: "DELETE",
            "staffID": staffID
        },
        success(data) {
            if (data == 0) {
                window.location.reload(true);
            } else {
                window.location.reload(true);
            }
        },
        error() {
            alert("网络出错，请稍后重试");
            window.location.reload(true);
        }
    })
};
//修改信息操作
function upStaff(staffID) {
    $.ajax({
        //使用JSON.stringify，直接type:"PUT"，即可，不需要设置_method
        url: "/staff/updateStaffInfo",
        type: "PUT",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data:JSON.stringify({
            "staffID": staffID,
            "staffName":$("#staffName").val(),
            "newPassword":$("#newPassword").val(),
            "address":$("#address").val(),
            "phone":$("#staffPhone").val(),
            "position":$("#position").val(),
            "salary":$("#salary").val(),
            "introduce":$("#introduce").val()
        })
        ,
        success(data) {
            switch (data) {
                case 0:
                    alert("更新成功");
                    window.location.reload();
                    break;
                case 1:
                    alert("更新失败");
                    window.location.reload();
                    break;
                case 2:
                    alert("会员信息更新成功，会员卡信息更新出现错误");
                    window.location.reload();
                    break;
                default:
                    alert("返回代码出错，请联系开发人员");
                    window.location.reload();
            }
        },
        error() {
            console.log("网络出错，请稍后重试");
            alert("网络出错，请稍后重试");
        }
    })
};

