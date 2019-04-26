//加载公告
$(document).ready(function () {
    start();
    $.ajax({
        url: "/announcement/getAnnListAll",
        type: "GET",
        success(data) {
            //成功回调函数
            console.log(data);
            $("#t_ol")[0].innerHTML=template('gonggao',data);
            examine();
        },
        error(xhr, status, error) {
            //出错回调函数
            $.MsgBox.Alert("错误提示","连接超时，请刷新当前页面");
        }
    })
});
//公告移动
var timer = null;

function start() {
    $("#div1").mouseover(function () {
        startMove(0);
    });
    $("#div1").mouseout(function () {
        startMove(-150);
    });
};

function startMove(iTarget) {
    clearInterval(timer);
    timer = setInterval(function () {
        var iSpeed = 0;
        if ($("#div1").offset().left < iTarget) {
            iSpeed = 10;
        } else {
            iSpeed = -10;
        }
        ;
        if ($("#div1").offset().left == iTarget) {
            clearInterval(timer);
        } else {
            $("#div1").css("left", $("#div1").offset().left + iSpeed + 'px');
        }
    }, 30)
};
//检查用户是否登录
//默认未登录
var code = 0;

function examine() {
    $.ajax({
        url: "/user/examineLogin",
        type: "GET",
        success(data) {
            if (data == 0) {
                console.log("未登录：" + data);
                $("#aText").text("未登录");
                $("#loginText").attr("href", "clientView/userLogin.html");
                code = data;
            } else if (data == 1) {
                console.log("用户已登录：" + data);
                $("#aText").text("已登录");
                $("#loginText").text("退出登录");
                code = data;
            } else {
                console.log("登录超时");
            }
        },
        error(xhr, status, error) {
            console.log("连接超时");
        }
    })
};
//退出登录
$(document).ready(function () {
    $("#loginText").click(function () {
        $.ajax({
            url: "/user/userQuit",
            type: "GET",
            success(data) {
                if (data == 0) {
                    ale("用户不存在，请前往登录");
                    location.href = "clientView/userLogin.html";
                } else {
                    ale("用户成功退出");
                    location.href = "/index.html";
                }
            },
            error(xhr, status, error) {
                console.log("用户连接超时");
            }
        })
    })
});
//点击预约
$(document).ready(function () {
    $("#course").click(function () {
        //检查是否登录
        examine();
        if (code == 0) {
            ale("请先登录再预约!");
            location.href = "clientView/userLogin.html";
        } else {
            console.log("正在进入预约界面!");
            location.href = "clientView/courseList.html";
        }
    })
})