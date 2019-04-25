// JavaScript代码区域
//定义默认查询全部会员信息
var cardUrl="/card/getUserCardList";
//初始化执行
queryCard();
//监听事件
layui.use('form',function () {
    var form = layui.form;
    //监听选择搜索框,更改接口
    form.on('select(business)', function (data) {
        console.log("选中了：" + data.value);//被选中的文本
        cardUrl = "/card/getUserCardByName?rankName="+data.value;
        console.log("执行了选择搜索框");
        //执行类型搜索
        queryCard();
        return false;
    });
});
function queryCard() {
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
            url: cardUrl,//数据接口,关键字搜索
            method: "GET",
            title: '会员列表',
            page: true,//开启分页
            limit: 10,
            limits: [10, 20, 30],
            toolbar: 'default',//开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            cols: [
                [ //表头
                    {
                        type: 'checkbox',
                        fixed: 'left'
                    }, {
                    field: 'users',
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
    });
}