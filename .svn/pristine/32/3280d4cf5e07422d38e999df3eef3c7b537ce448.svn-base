<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<meta charset="UTF-8">
<title>邮件</title>
<%@include file="/script.html" %>
</head>
<body>
    <div id="toolbar">
        <span>标题：</span>
        <input type="text" class="easyui-textbox" id="subject" name="subject">            
        <a href="javascript:void(0)" class="easyui-linkbutton"  id="search" >查询  </a>
        <a href="javascript:void(0)" class="easyui-linkbutton"  id="add" > 添加</a>
    </div>
    <table id="datagrid"></table>
</body>
<script type="text/javascript">
$("#datagrid").datagrid({
            url:"email/emailList",
            method:"post",
            toolbar:"#toolbar",
            fitColumns:true, //自动调整列宽
            idField:"id", // 指示服务器传递的数据中，哪个字段是主键
            rownumbers:true, // 显示行号
            pagination:true, // 使用分页
            columns:[[
                {field:"id",title:"ID",width:80},
                {field:"subject",title:"标题",width:80},
                {field:"content",title:"内容",width:80},
                {field:"orderId",title:"排序",width:80},
                {field:'option',title:'操作',formatter:function(value,rowData,rowIndex){
                    var e = '<a href="javascript:void(0)" onclick="PorEdit(' + rowData.id + ')"><img src="static/easyui/themes/icons/pencil.png" border="0"></a> ';
                    var d = '<a href="javascript:void(0)" onclick="Delete(' + rowData.id + ')"><img src="static/easyui/themes/icons/cancel.png"></a>';
                    return e + d;
                    },width:120}
            ]],
            loadFilter:function(data){
                if(data && data.success){
                    return {"total":data.result.total,"rows":data.result.rows};
                }
            }
        })  

function doSearch(subject){
    $("#datagrid").datagrid("load",{            
        "subject":subject
    })
}
//查询用户  
$("#search").on("click", function search(){
    var subject = $("#subject").val();
    doSearch(subject);
})

// 打开窗口
function openTopWindow(options){            
    options.width  = options.width ? options.width : 600;
    options.height = options.height ? options.height : 500;
    options.title =  options.title ?  options.title : " ";
    options.onClose = options.onClose ? options.onClose : function(){};
    if(!options.url){
        // 如果url不存在，抛出异常，下面的代码就不再执行
        throw "新窗口必须设置url属性";
    }
     
    parent.$("#topWindow").window({
        width: options.width,
        height: options.height,
        title: options.title,
        modal: true, // 设置弹出的窗口为 模式窗口  
        content:"<iframe width='100%' height='100%' src='"+options.url+"'>",
        onClose:function(){ 
            doSearch();
        }   // 窗口关闭时的回调函数             
    })
}

//修改用户信息
function PorEdit(id){
    var options={title:"邮件模板修改",url:"email/toChangEmail?id="+id};
    openTopWindow(options);
}

//添加用户
$("#add").on("click", function search(){
    var options={title:"邮件模板添加",url:"email/toChangEmail"};
    openTopWindow(options);
})

//删除用户
function Delete(id){
    $.messager.confirm("操作提示", "您确定要删除该邮件模板吗？", function (data) {
        if(data) {
            $.post("email/deleteEmail",
                {"id":id},
                function(result){
                    if (result.success==true) {
                        doSearch();
                    }else{
                        $.messager.alert('Warning',result.message);
                    }
                },
                "json");
        }else {
        }
    });
}
</script>
</html>