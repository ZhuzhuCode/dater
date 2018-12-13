/**
 * Created by zhujr on 2018/10/12.
 */
var toDoTable,userArray;
var ws = null;
$(function () {
    loadToDoList();
    getUserList();
    initWebsocket();
});

//初始化websocket
function initWebsocket() {
    //判断当前浏览器是否支持websocket
    if('WebSocket' in window){
        ws = new WebSocket("ws://localhost:18080/dater/websocket/10");
    }else {
        layer.msg('Not support websocket',{anim: 6});
    }

    ws.onerror = function (ev) {
        console.log("发生错误");
    };
    ws.onopen = function (ev) {
        console.log("打开连接");
    };
    ws.onmessage = function (ev) {
        layer.msg(ev.data,{anim: 6})
    };
    ws.onclose = function (ev) {
          console.log("关闭链接")
    };
}

//获取角色list
function getUserList() {
    $.ajax({
        url:'/dater/user/getAllUser',
        async:false,
        success:function (res) {
            userArray = res.data;
        }
    });
}
//通过id获取name
function userName(id) {
    for(var i=0;i<userArray.length;i++){
        if(userArray[i].id==id)
            return userArray[i].name;
    }
}

//加载待办事项list
function loadToDoList() {
    layui.use('table',function () {
        toDoTable = layui.table;
        toDoTable.render({
            elem:'#toDoList',
            id:'toDoList',
            url:'/dater/schedule/scheduleList',
            page:true,
            cols:[[
                {type:'numbers',title:'序号',align:'center'},
                {field:'content',title:'任务',align:'center'},
                {field:'userid',title:'所属用户',align:'center',templet:'#userTpl'},
                {field:'createtimeCN',title:'创建时间',sort:true,align:'center'},
                {field:'contentstartCN',title:'任务执行时间',sort:true,align:'center'},
                // {field:'contentendCN',title:'任务结束时间',sort:true,align:'center'},
                {toolbar:'#toDoTpl',title:'操作',align:'center'}
            ]]
        });
        var $ = layui.$,active = {
            toDoReload:function () {
                var userId = $("#userId").val();
                var content = $("#content").val();
                toDoTable.reload('toDoList',{
                    page:{curr:1},
                    where:{
                        userId:userId,
                        content:content
                    }
                });
            }
        };

        $('blockquote .layui-btn').on('click',function () {
            var type = $(this).data("type");
            active[type] ? active[type].call(this) : '';
        });

    });
}

//新增待办事项
function addSchedule() {
    layer.open({
        type: 2,
        title: '新增待办事项',
        shadeClose: true,
        // shade:0,
        area: ['460px', '300px'],
        content: '/dater/schedule/toEditSchedule?operate=0&id=""'
    });
}

//修改待办事项信息
function openEditSchedule(obj,operate) {
    var id = $(obj).data("id");
    layer.open({
        type: 2,
        title: '编辑待办事项',
        shadeClose: true,
        // shade:0,
        area: ['460px', '300px'],
        content: '/dater/schedule/toEditSchedule?operate=1&id='+id
    });
}

//删除待办事项
function deleteSchedule(obj) {
    var id = $(obj).data("id");
    if(id==""&&id==undefined){
        layer.alert("未获取到待办事项id!");
    }else {
        layer.confirm("确定删除该待办事项吗？",function () {
            $.ajax({
                url:'/dater/schedule/deleteById?id='+id,
                async:false,
                success:function(res){
                    Msg(res.msg,loadToDoList());
                    closeLayer();
                }
            });
        });
    }
}