/**
 * Created by zhujr on 2018/9/26.
 * 用户list
 */
var userTable;
var showheight,showLimit;
$(function () {
    loadUserTable();
});

//加载用户table
function loadUserTable() {
    layui.use('table',function () {
        userTable = layui.table;
        userTable.render({
            elem:'#userList',
            id:'userList',
            url:'/dater/user/userList',
            page:true,
            height:'400px',
            // limit:'10',
            cols:[[
                {type:'numbers',title:'序号',align:'center'},
                {field:'name',title:'用户',align:'center'},
                {field:'createtimeCN',title:'创建时间',sort:true,align:'center'},
                {field:'operatetimeCN',title:'最新登录时间',sort:true,align:'center'},
                {toolbar:'#userTpl',title:'操作',align:'center'}
            ]]
        });

        var $ = layui.$,active = {
            userReload:function () {
                var userName = $("#userName").val();
                userTable.reload('userList',{
                    page:{curr:1},
                    where:{
                        name:userName
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

//新增用户
function addUser() {
    layer.open({
        type: 2,
        title: '新增用户',
        shadeClose: true,
        // shade:0,
        area: ['460px', '300px'],
        content: '/dater/user/toEditUser?operate=0&id='
    });
}

//修改用户信息
function openEditUser(obj,operate) {
    var id = $(obj).data("id");
    layer.open({
        type: 2,
        title: '编辑用户',
        shadeClose: true,
        // shade:0,
        area: ['460px', '300px'],
        content: '/dater/user/toEditUser?operate=1&id='+id
    });
}

//删除用户
function deleteUser(obj) {
    var id = $(obj).data("id");
    if(id==""&&id==undefined){
        layer.alert("未获取到用户id!");
    }else {
        layer.confirm("确定删除该用户信息吗？",function () {
            $.ajax({
                url:'/dater/user/deleteById?id='+id,
                type:'POST',
                async:false,
                success:function(res){
                    Msg(res.msg,loadUserTable());
                    closeLayer();
                }
            });
        });
    }
}
