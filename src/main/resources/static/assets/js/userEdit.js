/**
 * Created by zhujr on 2018/10/8.
 * 用户新增/编辑页
 */
var form, loadIndex;
$(function () {
    layui.use('form', function () {
        form = layui.form;

        form.verify({
            account: function (value, item) {
                return checkName("账户", value);
            },
            pass:[/(.+){6,16}$/, '密码必须6到16位']
        });
        form.on('submit(submitAdd)', function (data) {
            loadIndex = loading('数据提交中');
            submitAdd();
            return false;
        });
    });
});

//提交
function submitAdd() {
    $.when(getSubmit()).done(function (data) {
        layer.close(loadIndex);
        Msg(data.msg, parent.loadUserTable());
        closeLayer();
    });
}

function getSubmit() {
    var defer = $.Deferred();
    $("#userEditForm").ajaxSubmit({
        url:'/dater/user/editUser',
        type:'POST',
        success:function (res) {
            defer.resolve(res);
        }
    });
    return defer.promise();
}
