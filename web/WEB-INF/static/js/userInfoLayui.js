//layui JavaScript代码区域
//定义路径修改,默认获取全部信息接口
var newUrl="/user/getUserList";
//初始化执行，查询全部数据
getUserAllList();
//模糊搜索
//监听按钮
$(document).ready(function () {
    $("#key").click(function () {
        //修改接口，模糊搜索
        newUrl="/user/getUserListByKeyword?keyword="+$("#keyword").val();
        console.log("获取到keyword="+$("#keyword").val());
        //开始执行模糊搜索
        getUserAllList();
    });
});
//通过关键词模糊查询显示，生成列表
function getUserAllList() {
    var limitcount = 10;
    var curnum = 1;
    layui.use(['laydate', 'laypage', 'layer', 'table', 'element', 'form', 'layedit', 'laydate'], function () {
        var laypage = layui.laypage //分页
            ,
            layer = layui.layer //弹层
            ,
            table = layui.table //表格
            ,
            element = layui.element //元素操作
        //--------弹出框操作------
        var form = layui.form,
            layer = layui.layer,
            layedit = layui.layedit,
            laydate = layui.laydate;
        //日期
        laydate.render({
            elem: '#date'
        });
        laydate.render({
            elem: '#date1'
        });
        //自定义验证规则
        form.verify({
            title: function (value) {
                if (value.length < 5) {
                    return '标题至少得5个字符啊';
                }
            },
            pass: [
                /^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格'
            ],
            content: function (value) {
                layedit.sync(editIndex);
            }
        });
        //--------弹出框end-----
        //监听提交
        form.on('submit(save)', function (data) {
            //监听添加按钮
            addUser();
            return false;
        });
        //问个好
        layer.msg('这里是会员信息列表');
        //监听Tab切换
        element.on('tab(demo)', function (data) {
            layer.tips('切换了 ' + data.index + '：' + this.innerHTML, this, {
                tips: 1
            });
        });
        //查询会员数据列表
        //执行一个 table 实例
        table.render({
            elem: '#demo',
            height: 480,
            url: newUrl,//数据接口
            method: "GET",
            title: '会员列表',
            page: true,//开启分页
            limit: 10,
            data:{
                //模糊搜索的关键字
                "keyword": $("#keyword").val()
            },
            limits: [10, 20, 30],
            toolbar: 'default',//开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            cols: [
                [ //表头
                    {
                        type: 'checkbox',
                        fixed: 'left'
                    }, {
                    field: 'userAccount',
                    title: '会员账号',
                    width: 100,
                    sort: true,
                    fixed: 'left',
                }, {
                    field: 'userName',
                    title: '会员名字',
                    width: 100
                }, {
                    field: 'userPassword',
                    title: '密码',
                    width: 90,
                    sort: true,
                }, {
                    field: 'gender',
                    title: '性别',
                    width: 80,
                    sort: true
                }, {
                    field: 'identityCard',
                    title: '身份证号',
                    width: 80,
                    sort: true,
                }, {
                    field: 'phone',
                    title: '联系方式',
                    width: 150
                }, {
                    field: 'email',
                    title: '电子邮箱',
                    width: 200
                }, {
                    field: 'expireTime',
                    title: '到期时间',
                    width: 135,
                    sort: true,
                }, {
                    field: 'regTime',
                    title: '注册时间',
                    width: 135,
                    sort: true,
                }, {
                    fixed: 'right',
                    width: 165,
                    align: 'center',
                    toolbar: '#barDemo'
                }, {
                    fixed: 'userID',
                    hide: true,
                }
                ]
            ],
            //数据渲染完回调
            done: function (res, curr, count) {
                //分页
                laypage.render({
                    elem: '#demo' //分页容器的id
                    ,
                    count: count //总条数
                    ,
                    curr: curnum
                    ,
                    limit: limitcount //当前页显示数据
                    ,
                    skin: '#1E9FFF' //自定义选中色值
                    // ,skip: true //开启跳页
                    ,
                    jump: function (obj, first) {
                        if (!first) {
                            curnum = obj.curr;
                            limitcount = obj.limit;
                            layer.msg('第' + obj.curr + '页', {
                                offset: 'b'
                            });
                        }
                    }
                });
            }
        });
        //监听头工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id),
                data = checkStatus.data; //获取选中的数据

            switch (obj.event) {
                case 'add':
                    onAddBtn();
                    break;
                case 'update':
                    if (data.length === 0) {
                        layer.msg('请选择一行');
                    } else if (data.length > 1) {
                        layer.msg('只能同时编辑一个');
                    } else {
                        layer.alert('编辑 [id]：' + checkStatus.data[0].userID);
                    }
                    break;
                case 'delete':
                    if (data.length === 0) {
                        layer.msg('请选择一行');
                    } else {
                        //执行删除
                        delUser(checkStatus.data[0].userID);
                    }
                    break;
            }
            ;
        });
        //监听行工具事件
        table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data,//获得当前行数据
                layEvent = obj.event; //获得 lay-event 对应的值
            console.log(data.userID);
            if (layEvent === 'detail') {
                //执行查看信息
                queryUser(data.userID);

            } else if (layEvent === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    //obj.del(); //删除对应行（tr）的DOM结构
                    delUser(data.userID);
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if (layEvent === 'edit') {
                //查询修改会员信息
                queryUserUp(data.userID);
                console.log("执行查询");
            }
        });
        //底部信息
        // var footerTpl = lay('#footer')[0].innerHTML;
        // lay('#footer').html(layui.laytpl(footerTpl).render({}))
        //     .removeClass('layui-hide');

    });
}

//创建layer弹窗---增加
function onAddBtn() {
    layer.open({
        type: 1,
        title: "新增会员",
        closeBtn: false,
        shift: 2,
        area: ['550px'],
        shadeClose: true,
        // btn: ['新增', '取消'],
        // btnAlign: 'c',
        content: $("#add-main"),
        success: function (layero, index) {
            console.log("成功弹");
        },
        yes: function () {
        }
    });
};

//创建一个layer弹窗---查询
function onQueryBtn() {
    layer.open({
        type: 1,
        title: "会员基本信息",
        closeBtn: false,
        shift: 2,
        area: ['800px'],
        shadeClose: true,
        content: $("#query-main"),
        success: function (layero, index) {

        },
        yes: function () {
        }
    })
};

//查询会员基本信息
function onQueryUser(data) {
    layui.use('laytpl', function () {
        var laytpl = layui.laytpl;
        var myDatas = {
            //数据
            "userInfoList": data
        }
        var getTpl = myData.innerHTML, view = $("#query-main");
        laytpl(getTpl).render(myDatas, function (result) {
            //清空元素内部的html代码
            view.empty();
            //重新添加
            view.append(result);
            //弹窗
            onQueryBtn();
        });
    })
};

//查询会员信息修改
function onUpdateUserInfo(data) {
    layui.use('laytpl', function () {
        var laytpl = layui.laytpl;
        var myDatas = {
            //数据
            "userInfoList": data
        }
        var getTpl = userData.innerHTML, view = $("#up-main");
        laytpl(getTpl).render(myDatas, function (result) {
            //清空元素内部的html代码
            view.empty();
            //重新添加
            view.append(result);
            //弹窗
            onUpdateUser(data.userID);
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

//修改会员信息监听按钮
function onUpdateUser(userID) {
    layui.use('form', function () {
        var form = layui.form;
        layer.open({
            type: 1,
            title: "修改会员信息",
            closeBtn: false,
            shift: 2,
            area: ['550px'],
            shadeClose: true,
            content: $("#up-main"),
            success: function (layero, index) {
                //监听修改
                form.on('submit(update)', function (data) {
                    //监听修改按钮
                    upUser(userID);
                    return false;
                });
                console.log("成功弹出");
            },
            yes: function () {
            }
        });
    });
};

    
