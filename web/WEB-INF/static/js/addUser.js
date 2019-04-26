//JavaScript代码区
layui.use(['element', 'form', 'layedit', 'laydate'], function () {
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        laydate = layui.laydate;

    var element = layui.element;

    //问好
    layer.msg("新增会员");
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
    //监听提交
    form.on('submit(save)', function (data) {
        //监听添加按钮
        addUser();
        return false;
    })

});