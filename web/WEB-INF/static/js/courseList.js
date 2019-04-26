//加载预约课程信息
$(document).ready(function () {
    $.ajax({
        url: "/course/getCourseAll",
        type: "GET",
        success(data) {
            console.log(data);
            console.log(data.length);
            $("#bTable")[0].innerHTML = template('test', data);
        },
        error(xhr, status, error) {
            alert("连接超时，请检查网络是否正常使用！");
        }
    })
});
//退出登录
$(document).ready(function () {
    $("#userQuit").click(function () {
        $.ajax({
            url: "/user/userQuit",
            type: "GET",
            success(data) {
                if (data == 0) {
                    alert("用户不存在，请前往登录");
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

//进入个人预约列表，检查是否登录
function quit() {
    $.ajax({
        url: "/user/examineLogin",
        type: "GET",
        success(data) {
            if (data == 0) {
                alert("请先登录再查看" );
                location.href = "userLogin.html";
            } else {
                console.log("正在进入个人预约列表：" + data);
                location.href = "myList.html";
            }
        },
        error(xhr, status, error) {
            alert("连接超时,请刷新重试");
        }
    })
};

//点击预约
function addForward(courseID) {
    //获取系统时间
    var myDate = new Date();
    var forwardTime = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate();
    $.ajax({
        url: "/forward/addForwardCourse",
        type: "POST",
        data: {
            "courseID": courseID,
            "forwardTime": forwardTime
        },
        success(data) {
            switch (data) {
                case (0):
                    alert("添加错误，请检查网络重试");
                    location.href = "courseList.html";
                    break;
                case (1):
                    alert("添加成功，请按时参加教程");
                    location.href="courseList.html";
                    break;
                case (2):
                    alert("预约失败，人数已达上限，请选择其他课程");
                    location.href="courseList.html";
                    break;
                case (3):
                    alert("登录失效，请重新登录");
                    location.href="userLogin.html";
                    break;
                case (4):
                    alert("已经预约过当前课程，请不要重复预约");
                    location.href="courseList.html";
                    break;
                default:
                    alert("对不起，选择出错，请重新选择");
            }
        },
        error(xhr, status, error) {
            ale("预约超时，请检查网络再重试");
        }
    })
};