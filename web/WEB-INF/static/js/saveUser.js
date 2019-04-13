//添加会员
function saveUser() {
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