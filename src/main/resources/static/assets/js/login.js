/**
 * Created by zhujr on 2018/10/15.
 * 登录页
 */
var loginForm,loadIndex;
$(function () {
    layui.use('form',function () {
        loginForm = layui.form;

        loginForm.on('submit(login)',function (data) {
            loadIndex = loading("数据提交中");
            submitLogin();
            return false;
        });
    });
});

//提交login
function submitLogin() {
    $.ajax({
        url:'/dater/login/loginIn',
        async:false,
        data:$("#loginForm").serialize(),
        success:function (res) {
            Msg(res.msg,locationURL(res));
        }
    });
}

function locationURL(res) {
    if(res.code=="0"){
        window.location.href = res.data;
    }
}