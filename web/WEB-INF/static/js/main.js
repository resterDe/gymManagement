//LsyUI JavaScript代码区域
layui.use('element', function () {
    var element = layui.element;

});

//执行返回管理员信息
$(document).ready(function () {
    $.ajax({
        url: "/admin/getAdminInfo",
        type: "GET",
        success(data) {
            console.log("获取得管理员信息：" + data);
        },
        error() {
            console.log("网络获取失败，请刷新重试");
        }
    })
});
