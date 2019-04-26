//初始化验证码的值
var myCode = null;
//验证验证码是否正确
function yanzheng() {
    if ($("#codeText").val() == null) {
        ale("请输入验证码");
        return 0;
        //toLowerCase()转为小写，不区分大小写
    } else if (($("#codeText").val()).toLowerCase() == myCode.toLowerCase()) {
        return 1;
    } else {
        return 0;
    }
};
//绑定登录按钮
$(document).ready(function () {
    $("#loginBtn").click(function () {
        $.ajax({
            url: "/user/userLogin",
            type: "POST",
            data: {
                "userAccount": $("#userName").val(),
                "userPassword": $("#password").val(),
                "code": $("#codeText").val()
            },
            success(data) {
                if (data == 1) {
                    console.log("登录成功");
                    location.href = "/index.html";
                } else if (data == 0) {
                    console.log("账号或者密码错误");
                    alert("账号或者密码错误");
                    window.location.reload();
                } else {
                    console.log("验证码错误");
                    alert("验证码错误");
                    qingchu();
                    dianji();
                    $("#codeText").val("");
                }
            },
            error() {
                console.log("登录超时，请检查网络问题或重新打开此页面");
                alert("登录超时，请检查网络问题或重新打开此页面");
                window.location.reload();
            }
        })
    });
});

//验证码实现
$(document).ready(function () {
    //初始化验证码
    dianji();
    //点击刷新验证码
    $("#getCode").click(function () {
        //清除画布内容
        qingchu();
        //重新填写画布内容
        dianji();
    });
});

//执行刷新验证码
function dianji() {
    $.ajax({
        url: "/getVerification",
        type: "GET",
        success(data) {
            //刷新验证码
            getCode(data);
            myCode = data;
            console.log("刷新验证码");
        },
        error() {
            console.log("刷新失败，请检查网络");
            ale("刷新失败，请检查网络");
        }
    })
}

//绘制画布验证码
function getCode(code) {
    var c = document.getElementById("myCanvas");
    var ctx = c.getContext("2d");
    ctx.font = "25px Georgia";
    ctx.font = "22px Verdana";
    // Create gradient
    var gradient = ctx.createLinearGradient(0, 0, c.width, 0);
    gradient.addColorStop("0", "magenta");
    gradient.addColorStop("0.5", "blue");
    gradient.addColorStop("1.0", "red");
    // Fill with gradient
    ctx.fillStyle = gradient;
    ctx.fillText(code, 13, 23);
}

//清空画布内容
function qingchu() {
    var c = document.getElementById("myCanvas");
    var ctx = c.getContext("2d");
    ctx.clearRect(0, 0, 100, 28);
}