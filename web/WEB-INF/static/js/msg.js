//弹出对话框并输出一段提示信息
function ale(aleMsg) {
    //弹出一个对话框
    alert(aleMsg);

}
//弹出一个询问框，有确定和取消按钮
function firm(firmMsg) {
    //利用对话框返回的值 （true 或者 false）
    if (confirm("你确定执行该操作吗？")) {
        alert("确定："+firmMsg);
    }
    else {
        alert("取消："+firmMsg);
    }
};