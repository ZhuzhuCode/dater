/**
 * Created by zhujr on 2018/9/20.
 * 母版页js
 */
var userTable;
$(function () {

});

//URL跳转
function toChange(obj, url, type) {
    $(".sidebar li .active").removeClass("active");
    $(obj).parent().addClass("active");
    document.getElementById("contentFrame").src = url;
}

//注销
function loginOut() {
    $.ajax({
        url:'/dater/login/loginOut',
        async:false,
        success:function (res) {
            window.location.href = res.data;
        }
    });
}