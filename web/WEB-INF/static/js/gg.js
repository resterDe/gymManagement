layui.use(['form', 'layer', 'element', 'layedit', 'laydate'], function () {
    var form = layui.form,
        layer = layui.layer,
        element = layui.element,
        layedit = layui.layedit,
        laydate = layui.laydate;

    layui.use('element', function () {
        var element = layui.element;

    });

    laydate.render({
        elem:'#date2'
    });
    laydate.render({
        elem:'#date1'
    });

    //监听提交
    form.on('submit(addAnn)', function (data) {
        //是否确认执行执行增加
        addAnn();
        return false;
    });
});

//预加载绑定添加弹出窗口
$(document).ready(function () {
    $("#onAdd").click(function () {
        layui.use('layer',function () {
            var layer=layui.layer;
            //建立添加公告弹出
            layer.open({
                type: 1,
                title: "添加新公告",
                closeBtn: false,
                offset: ['30px'],
                shift: 2,
                area: ['600px'],
                shadeClose: true,
                content: $("#add-annInfo"),
                success: function (layero, index) {
                    layer.msg("请填写公告内容");
                },
                yes: function () {

                }
            });
        });
    });
});
//执行添加公告
function addAnn() {
    $.ajax({
        url:"/announcement/addAnn",
        type: "POST",
        data:{
            "headline":$("#add_headline").val(),
            "annTime":$("#date1").val(),
            "content":$("#add_content").val()
        },
        success(data){
            if (data==0){
                alert("添加成功");
                window.location.reload();
            } else {
                alert("添加失败，请重试一次");
                window.location.reload();
            }
        },
        error(){
            console.log("网络出错，请稍后重试");
            alert("网络出错，请稍后重试");
        }
    });
};

//建立一个信息查询弹窗
function queryAnn() {
    layui.use('layer', function () {
        var layer = layui.layer;

        //建立查询信息弹框
        layer.open({
            type: 1,
            title: "公告详细信息",
            closeBtn: false,
            offset: ['100px'],
            shift: 1,
            area: ['600px'],
            shadeClose: true,
            content: $("#query-annInfo"),
            success: function (layero, index) {
                layer.msg("查询成功");
            },
            yes: function () {

            }
        })
    });
}

//建立一个信息编辑弹窗
function onEditAnn(annID) {
    layui.use(['layer', 'form'], function () {
        var layer = layui.layer,
            form = layui.form;
        //建立查询信息弹框
        layer.open({
            type: 1,
            title: "公告详细信息",
            closeBtn: false,
            offset: ['30px'],
            shift: 1,
            area: ['600px'],
            shadeClose: true,
            content: $("#edit-annInfo"),
            success: function (layero, index) {
                form.on('submit(updateAnn)', function (data) {
                    //监听修改按钮
                    layer.msg("修改中...");
                    editAjax(annID);
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
        url: "/announcement/getAnnById",
        type: "GET",
        data: {
            "annID": id
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
        var annData = {
            //数据
            "annInfoList": data
        };
        var getTpl = annDataInfo.innerHTML, view = $("#query-annInfo");
        laytpl(getTpl).render(annData, function (result) {
            //清空元素内部html代码
            view.empty();
            //重新添加
            view.append(result);
            //弹窗显示
            queryAnn();
        })
    });
};

//编辑查询需要的信息
function editquery(id) {
    $.ajax({
        url: "/announcement/getAnnById",
        type: "GET",
        data: {
            "annID": id,
        },
        success(data) {
            console.log("编辑所需的信息：" + data);
            editInfo(data);
        },
        error() {
            console.log("请检查网络");
        }
    });
};

//渲染编辑的信息
function editInfo(data) {
    layui.use('laytpl', function () {
        var laytpl = layui.laytpl;
        var annData = {
            //数据
            "annInfo": data,
        };
        var getTpl = editAnn.innerHTML, view = $("#edit-annInfo");
        laytpl(getTpl).render(annData, function (result) {
            //清空元素内部html代码
            view.empty();
            //重新添加
            view.append(result);
            //弹窗显示
            onEditAnn(data.annID);
        });
        //渲染完必须初始化动态元素
        layui.use(['form', 'laydate', 'element'], function () {
            var element = layui.element, laydate = layui.laydate, form = layui.form;
            //初始化动态元素，一些动态生成的元素如果不设置初始化，将不会有默认的动态效果
            element.render();
            form.render();
            laydate.render({
                elem: '#date2'
            });
        });
    })
};

//发送编辑信息请求
function editAjax(annID) {
    $.ajax({
        url: "/announcement/updateAnnInfo",
        type: "POST",
        data: {
            _method: "PUT",
            "annID": annID,
            "headline": $("#headline").val(),
            "annTime":$("#date2").val(),
            "content": $("#content").val()
        },
        success(data) {
            console.log("修改：" + data);
            if (data == 0) {
                alert("修改成功");
                window.location.reload();
            } else {
                alert("修改失败");
                window.location.reload();
            }
        },
        error() {
            alert("网络出错");
        }
    });
}

//删除
function del(id) {
    console.log("删除:" + id);
    $.ajax({
        url: "/announcement/delAnnInfo",
        type: "POST",
        data: {
            _method: "DELETE",
            "annID": id
        },
        success(data) {
            console.log(data);
            alert("删除成功");
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
        url: "/announcement/getAnnListAll",
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