$(document).ready(function () {
    //获取个人预约列表
    $.ajax({
        url: "/forward/getUserForward",
        type: "GET",
        success(data) {
            console.log(data);
            $("#timeData")[0].innerHTML = template('test', data);
        },
        error(xhr, status, error) {
            console.log("网络请求出错");
        }
    });
    //退出登录
    $("#myQuit").click(function () {
        $.ajax({
            url: "/user/userQuit",
            type: "GET",
            success(data) {
                if (data == 0) {
                    console.log("用户不存在，请前往登录");
                    location.href = "clientView/userLogin.html";
                } else {
                    console.log("用户成功退出");
                    location.href = "/index.html";
                }
            },
            error(xhr, status, error) {
                console.log("用户连接超时");
            }
        })
    })
});
//删除教程
function delForward(forwardID) {
    $.ajax({
        url: "/forward/delUserForward",
        type: "POST",
        data: {
            _method: "DELETE",
            "forwardID": forwardID
        },
        success(data){
            if (data==0){
                console.log("删除成功："+data);
                alert("删除成功");
                window.location.reload();
            } else {
                console.log("删除失败："+data);
                alert("删除失败");
                window.location.reload();
            }
        },
        error(){
            console.log("操作出错，请找管理员联系");
            alert("操作出错，请找管理员联系");
        }
    });
}