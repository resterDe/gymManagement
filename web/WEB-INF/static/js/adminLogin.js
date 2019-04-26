//Demo
layui.use('form', function () {
    var form = layui.form;
    //监听提交
    form.on('submit(formDemo)', function (data) {
        //文字弹窗，获取文本弹窗
        // layer.msg(JSON.stringify(data.field));
        return false;
    });
});

//预定义
$(document).ready(function () {
    //按钮点击确认登录
    $("#adminLogin").click(function () {
        $.ajax({
            url: "/admin/adminLogin",
            type: "POST",
            data: {
                "serialNumber": $("#serialNumber").val(),
                "newPassword": $("#newPassword").val()
            },
            success(data) {
                if (data == 0) {
                    alert("登入失败，请检查登陆身份");
                    window.location.reload();
                } else {
                    //进入主页面
                    alert("登入成功,请妥善管理");
                    location.href = "main.html";
                }
            },
            error() {
                alert("登入失败，请检查网络设置");
                window.location.reload();
            }
        })
    });
    //enter键绑定确认提交
    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            document.getElementById("adminLogin").click();
        }
    })
});