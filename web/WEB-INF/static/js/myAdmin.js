// LayUI JavaScript代码区域
layui.use('element', function () {
    var element = layui.element;

});
layui.use('form', function () {
    var form = layui.form;
    //监听提交
    form.on('submit(formDemo)', function (data) {
        //文字弹窗，获取文本弹窗
        btuClick();
        return false;
    });
});

$(document).ready(function () {
    //确认退出
    $("#quitAdmin").click(function () {
        $.ajax({
            url: "/admin/adminQuit",
            type: "GET",
            success(data) {
                if (data == 1) {
                    console.log("退出成功：" + data);
                    window.location.reload();
                } else {
                    console.log("退出失败：" + data);
                }
            },
            error() {
                console.log("网络出错，请刷新重试");
            }
        })
    });

});
//判断新密码与确认密码是否一致
function ifPassword() {
    var newPassword = $("#newPassword").val();//新密码
    var conPassword = $("#conPassword").val();//确认新密码

    if (newPassword == conPassword) {
        console.log("密码一致");
        return true;
    } else {
        console.log("密码不一致，请确认");
        return false;
    }
}
//修改密码
function btuClick() {
    // $("#updateBtu").click(function () {
    if (ifPassword()) {
        $.ajax({
            url: "/admin/updateAdminPassword",
            type: "POST",
            data: {
                //put，delete请求需要携带"_method"---属性指定请求，type为post
                _method: "put",
                "newPassword": $("#newPassword").val()
            },
            success(data) {
                console.log("修改返回值：" + data);
                //修改成功，后台清除session后刷新当前页面可返回登陆页面
                location.reload();
            },
            error() {
                console.log("网络出错，请稍后重试");
            }
        })
    } else {
        layer.msg("密码不一致，请确认");
    }
    // })
}