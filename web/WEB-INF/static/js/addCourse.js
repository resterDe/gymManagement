//LayUI JavaScript代码区
layui.use(['element', 'form', 'layedit', 'laydate'], function () {
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        laydate = layui.laydate;

    var element = layui.element;

    //问好
    layer.msg("新增教程");
    //日期
    laydate.render({
        elem: '#date'
    });
    laydate.render({
        elem: '#date1'
    });
    //监听提交
    form.on('submit(add)', function (data) {
        //监听添加按钮
        layer.msg("添加中...");
        addCourse();
        return false;
    })

});

//预加载教练信息，查询所有教练
$(document).ready(function () {
    layui.use(['element', 'form', 'layedit', 'laydate'], function () {
        var form = layui.form,
            element = layui.element;
        $.ajax({
            url: "/staff/getStaffBypos",
            type: "GET",
            data: {
                "position": "教练"
            },
            success(data) {
                console.log(data);
                $("#staffID")[0].innerHTML = template('test', data);

                element.render();
                form.render();
            },
            error() {
                console.log("网络出错");
            }
        });
    });
});

//新增教程请求
function addCourse() {
    $.ajax({
        url: "/course/addCourse",
        type: "POST",
        data: {
            "courseName": $("#courseName").val(),
            "site": $("#site").val(),
            "startTime": $("#date1").val(),
            "endTime": $("#date2").val(),
            "introduce": $("#introduce").val(),
            "staffID": $("#sID").val(),
            "maxNumber": $("#maxNumber").val()
        },
        success(data) {
            console.log("添加" + data);
            if (data == 1) {
                console.log("添加成功");
                location.href = "courseList.html";
            } else {
                console.log("添加失败");
                location.href = "courseList.html";
            }
        },
        error() {
            console.log("网络出错");
        }
    });
};