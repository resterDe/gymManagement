//添加会员
function addUser() {
    console.log("执行了添加");
    console.log("获取的数据：" + $("#userAccount").val() + ":" + $("input[name='gender']:checked").val() + $("#date").val() + ":" + $("#rankName").find("option:selected").val());
    $.ajax({
        url: "/user/saveUser",
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            "userName": $("#userName").val(),
            "userAccount": $("#userAccount").val(),
            "userPassword": $("#userPassword").val(),
            "gender": $("input[name='gender']:checked").val(),
            "identityCard": $("#identityCard").val(),
            "phone": $("#phone").val(),
            "email": $("#email").val(),
            "regTime": $("#date").val(),
            "expireTime": $("#date1").val(),
            "rankName": $("#rankName").find("option:selected").val()
        })
        ,
        success(data) {
            console.log("成功返回值：" + data);
            switch (data) {
                case 0:
                    alert("新增客户成功");
                    location.href = "userInfoList.html";
                    break;
                case 1:
                    alert("新增客户失败");
                    break;
                case 2:
                    alert("已存在该会员");
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
function queryUser(userID) {
    $.ajax({
        url: "/user/getUserInfoById",
        type: "GET",
        data: {
            "userID": userID
        },
        success(data) {
            console.log("查询成功：" + data);
            //将数据传入layui模板
            onQueryUser(data);
        },
        error() {
            console.log("网络出错");
        }
    })
};
//查询会员信息修改
function queryUserUp(userID) {
    $.ajax({
        url: "/user/getUserInfoById",
        type: "GET",
        data: {
            "userID": userID
        },
        success(data) {
            console.log("查询修改会员信息：" + data);
            onUpdateUserInfo(data);
        },
        error() {
            console.log("失败");
        }
    })
};
<!--编辑，删除，增加请求ajax-->
//删除操作
function delUser(userID) {
    $.ajax({
        url: "/user/deleteUserById",
        type: "POST",
        data: {
            _method: "DELETE",
            "userID": userID
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
function upUser(userID) {
    $.ajax({
        //使用JSON.stringify，直接type:"PUT"，即可，不需要设置_method
        url: "/user/updateUserInfo",
        type: "PUT",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data:JSON.stringify({
            "userID": userID,
            "userName": $("#up_userName").val(),
            "userPassword": $("#up_userPassword").val(),
            "gender": $("input[name='up_gender']:checked").val(),
            "identityCard": $("#up_identityCard").val(),
            "phone": $("#up_phone").val(),
            "email": $("#up_email").val(),
            "expireTime": $("#date2").val(),
            "rankName": $("#up_rankName").find("option:selected").val(),
            "activateCode": $("input[name='activateCode']:checked").val()
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

