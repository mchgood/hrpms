<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<meta charset="UTF-8">
<title>字典</title>
<%@include file="/script.html" %>
</head>
<body>
    <div id="toolbar">
        <span>字典名称：</span>
        <input type="text" class="easyui-textbox" id="name" name="name">
        <span>状态：</span>
        <select id="status"  name="status" style="width:200px;height: 27px">
            <option value="" selected>-- 选择状态 --</option>
                    <c:forEach var="item" items="${DictList}">
                    
                        <c:if test="${item.name=='del_flag'}">
                            <c:choose>
                                <c:when test="${item.value==func.status}">
                                  <option value="${item.value}" selected>${item.label}</option>
                                </c:when>
                                <c:otherwise>
                                  <option value="${item.value}">${item.label}</option>
                                </c:otherwise>
                            </c:choose>                     
                        </c:if>
                    </c:forEach>
                </select>            
        <a href="javascript:void(0)" class="easyui-linkbutton"  id="search" >查询  </a>
        <a href="javascript:void(0)" class="easyui-linkbutton"  id="add" > 添加</a>
    </div>
    <table id="datagrid"></table>
</body>
<script type="text/javascript">
$("#datagrid").datagrid({
            url:"dict/dictList",
            method:"post",
            toolbar:"#toolbar",
            fitColumns:true, //自动调整列宽
            idField:"id", // 指示服务器传递的数据中，哪个字段是主键
            rownumbers:true, // 显示行号
            pagination:true, // 使用分页
            columns:[[
                {field:"name",title:"字典名称",width:80},
                {field:"value",title:"储存的值",width:80},
                {field:"label",title:"显示的值",width:80},
                {field:"description",title:"描述",width:80},
                {field:"sort",title:"排序",width:80},
                {field:"statusName",title:"状态",width:80},
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

function doSearch(name,status){
    $("#datagrid").datagrid("load",{            
        "name":name,
        "status":status
    })
}
//查询用户  
$("#search").on("click", function search(){
    var name = $("#name").val();
    var status = $("#status").val();
    doSearch(name,status);
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
    var options={title:"字典模板修改",url:"dict/toChangDict?id="+id};
    openTopWindow(options);
}

//添加用户
$("#add").on("click", function search(){
    var options={title:"字典模板添加",url:"dict/toChangDict"};
    openTopWindow(options);
})

//删除用户
function Delete(id){
    $.messager.confirm("操作提示", "您确定要删除该字典模板吗？", function (data) {
        if(data) {
            $.post("dict/deleteDict",
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