layui.use(['form', 'layer', 'element', 'layedit', 'laydate'], function () {
    var form = layui.form,
        layer = layui.layer,
        element = layui.element,
        layedit = layui.layedit,
        laydate = layui.laydate;

    // <!--请求js ajax-->
    //预加载请求数据
    $(document).ready(function () {
        $.ajax({
            url: "/forward/getForward",
            type: "GET",
            success(data) {
                console.log(data);
                //开始渲染模板
                forwardInfo(data);
                layer.msg("显示预约信息");
            },
            error() {
                console.log("网络出错，请刷新重试");
                layer.msg("网络出错，请刷新重试");
            }
        })
    });

    //渲染展示信息
    function forwardInfo(data) {
        layui.use('laytpl', function () {
            var laytpl = layui.laytpl;
            var forwardDataList = {
                //数据
                "forwardInfoList": data,
            };
            var getTpl = forwardData.innerHTML, view = $("#forward");
            laytpl(getTpl).render(forwardDataList, function (result) {
                view.append(result);
            })
        });
    }
});

//监听查看按钮
function onForward(forwardID) {
    $.ajax({
        url: "/forward/getForwardById",
        type: "GET",
        data: {
            "forwardID": forwardID
        },
        success(data) {
            console.log("点击查看" + data);
            //渲染信息
            queryForward(data);
        },
        error() {
            console.log("网络出错");
        }
    })
};

//渲染查看信息
function queryForward(data) {
    layui.use('laytpl', function () {
        var laytpl = layui.laytpl;
        var forwardData = {
            //数据
            "forwardInfo": data
        };
        var getTpl = queryForwardInfo.innerHTML, view = $("#query-forward");
        laytpl(getTpl).render(forwardData, function (result) {
            //清空元素内部html代码
            view.empty();
            //重新添加
            view.append(result);
            //弹窗显示
            createForward();
        })
    });
};

//建立查看信息弹窗
function createForward() {
    layui.use('layer', function () {
        var layer = layui.layer;

        //建立查询信息弹框
        layer.open({
            type: 1,
            title: "预约详细信息",
            closeBtn: false,
            offset: ['100px'],
            shift: 1,
            area: ['600px'],
            shadeClose: true,
            content: $("#query-forward"),
            success: function (layero, index) {
                layer.msg("查询成功");
            },
            yes: function () {

            }
        })
    });
};

//监听删除按钮
function delForward(forwardID) {
    $.ajax({
        url: "/forward/delUserForward",
        type: "POST",
        data: {
            _method: "DELETE",
            "forwardID": forwardID
        },
        success(data) {
            console.log(data);
            if (data == 0) {
                console.log("删除成功");
                alert("删除成功");
                window.location.reload();
            } else {
                console.log("删除失败");
                alert("删除失败");
            }
        },
        error() {
            console.log("网络出错");
        }
    })
}