/**
 * Created by zhujr on 2018/10/9.
 * 通用JS
 */
//关闭弹出窗
function closeLayer() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

//通用loading层
function loading(msg) {
    if(msg==null||msg==undefined){
        msg = "加载中";
    }
    return layer.msg(msg, {
        icon: 16
        ,shade: 0.8
        ,time:0
    });
}

//弹窗完成提示
function Msg(msg,callback) {
    if(msg==null||msg==undefined){
        msg = "加载中";
    }
    layer.msg(msg, {
        icon: 6
        ,time:1000
    },callback);
}

/*********************表单校验****************************/
//校验XX名字
function checkName(Str,value) {
    if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
        return Str + '不能有特殊字符';
    }
    if(/(^\_)|(\__)|(\_+$)/.test(value)){
        return Str + '首尾不能出现下划线\'_\'';
    }
    if(!isNaN(value)){
        return Str + '不能全为数字';
    }
}

//校验待办事项
function checkTask(Str,value) {
    if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
        return Str + '不能有特殊字符';
    }
    if(Str.length>200){
        return Str+"不能超过200字符";
    }
}