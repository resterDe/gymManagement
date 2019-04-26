layui.use(['form', 'layer','element','layedit','laydate'], function () {
    var form = layui.form,
        layer = layui.layer,
        element=layui.element,
        layedit=layui.layedit,
        laydate=layui.laydate;

    //日期
    laydate.render({
        elem:'#date1'
    });
    laydate.render({
        elem:'#date2'
    });

    //监听编辑提交
    form.on('')
});

//建立一个信息查询弹窗
function queryStaff() {
    layui.use('layer', function () {
        var layer = layui.layer;

        //建立查询信息弹框
        layer.open({
            type: 1,
            title: "教程详细信息",
            closeBtn: false,
            offset:['100px'],
            shift: 1,
            area: ['600px'],
            shadeClose: true,
            content: $("#query-staffInfo"),
            success: function (layero, index) {
                layer.msg("查询成功");
            },
            yes: function () {

            }
        })
    });
}
//建立一个信息编辑弹窗
function onEditCourse(courseID) {
    layui.use(['layer','form'], function () {
        var layer = layui.layer,
            form=layui.form;
        //建立查询信息弹框
        layer.open({
            type: 1,
            title: "教程详细信息",
            closeBtn: false,
            offset:['30px'],
            shift: 1,
            area: ['600px'],
            shadeClose: true,
            content: $("#edit-staffInfo"),
            success: function (layero, index) {
                form.on('submit(updateCourse)',function (data) {
                    //监听修改按钮
                    layer.msg("修改中...");
                    editAjax(courseID);
                    return false;
                });
            },
            yes: function () {

            }
        })
    });
}
//请求查看信息
function query(id) {
    console.log("查看:" + id);
    $.ajax({
        url: "/course/getCourseInfoById",
        type: "GET",
        data: {
            "courseID": id
        },
        success(data) {
            console.log(data);
            queryInfo(data);
        },
        error() {
            console.log("请检查网络");
        }
    });
};

//渲染信息列表
function queryInfo(data) {
    layui.use('laytpl', function () {
        var laytpl = layui.laytpl;
        var staffData = {
            //数据
            "staffInfoList": data
        };
        var getTpl = staffDataInfo.innerHTML, view = $("#query-staffInfo");
        laytpl(getTpl).render(staffData, function (result) {
            //清空元素内部html代码
            view.empty();
            //重新添加
            view.append(result);
            //弹窗显示
            queryStaff();
        })
    });
};

//编辑查询需要的信息
function editquery(id) {
    $.ajax({
        url: "/course/getCourseInfoById",
        type: "GET",
        data: {
            "courseID": id,
        },
        success(data) {
            console.log("编辑所需的信息："+data);
            var str=data.courseTime;
            var startTime=(str.split("~")[0]);
            var endTime=(str.split("~")[1]);
            console.log(startTime+"-"+endTime);
            editInfo(data,startTime,endTime);
        },
        error() {
            console.log("请检查网络");
        }
    });
};
//渲染编辑的信息
function editInfo(data,startTime,endTime){
    layui.use('laytpl', function () {
        var laytpl = layui.laytpl;
        var courseData = {
            //数据
            "courseInfo": data,
            "startTime":startTime,
            "endTime":endTime
        };
        var getTpl = editStaff.innerHTML, view = $("#edit-staffInfo");
        laytpl(getTpl).render(courseData, function (result) {
            //清空元素内部html代码
            view.empty();
            //重新添加
            view.append(result);
            //弹窗显示
            onEditCourse(data.courseID);
        });
        //渲染完必须初始化动态元素
        layui.use(['form', 'laydate', 'element'], function () {
            var element = layui.element, laydate = layui.laydate, form = layui.form;
            //初始化动态元素，一些动态生成的元素如果不设置初始化，将不会有默认的动态效果
            element.render();
            form.render();
            laydate.render({
                elem: '#date1'
            });
            laydate.render({
                elem: '#date2'
            });
        });
    })
};
//发送编辑信息请求
function editAjax(courseID) {
    $.ajax({
        url:"/course/updateCourseById",
        type:"POST",
        data:{
            _method: "PUT",
            "courseID":courseID,
            "courseName":$("#up_courseName").val(),
            "startTime":$("#date1").val(),
            "endTime":$("#date2").val(),
            "site":$("#up_site").val(),
            "introduce":$("#text").val()
        },
        success(data){
            console.log("修改："+data);
            if (data==0){
                console.log("修改成功");
                window.location.reload();
            } else {
                console.log("修改失败");
                window.location.reload();
            }
        },
        error(){
            console.log("网络出错");
        }
    });
}
//删除
function del(id) {
    console.log("删除:" + id);
    $.ajax({
        url: "/course/delCourseById",
        type: "POST",
        data: {
            _method: "DELETE",
            "courseID": id
        },
        success(data) {
            console.log(data);
            window.location.reload();
        },
        error() {
            console.log("请检查网络");
        }
    });
};
//查询教程信息列表
$(document).ready(function () {
    $.ajax({
        url: "/course/getCourseAll",
        type: "GET",
        data: {},
        success(data) {
            console.log(data);
            $("#tbody")[0].innerHTML = template('test', data);
        },
        error() {
            console.log("网络出错");
        }
    })
});