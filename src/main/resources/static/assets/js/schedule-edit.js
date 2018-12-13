
/**
 * Created by zhujr on 2018/10/16.
 * 待办事项新增/编辑
 */
var scheduleForm,loadIndex;
$(function () {
    layui.use(['form','laydate'],function () {
        scheduleForm = layui.form;
        var contentStart = layui.laydate;
        contentStart.render({
            elem:'#contentstart',
            type:'datetime'
        });

        scheduleForm.verify({
            task: function (value, item) {
                return checkTask("账户", value);
            }
        });
        scheduleForm.on('submit(submitSchedule)', function (data) {
            loadIndex = loading('数据提交中');
            submitSchedule();
            return false;
        });
    });
});

//
function submitSchedule() {
    $.ajax({
        url:'/dater/schedule/editSchedule',
        async:false,
        data:$("#scheduleEditForm").serialize(),
        success:function (res) {
            Msg(res.msg,parent.loadToDoList());
            closeLayer();
        }
    });
}