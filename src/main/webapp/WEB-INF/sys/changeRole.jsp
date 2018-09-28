<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<base href="<%=basePath%>">
<%@include file="/script.html" %>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户信息管理 - Powered By JeeSite</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" /><meta name="author" content="http://jeesite.com/"/>
    <meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
    <meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
    <link href="static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
    <link href="static/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
    <link href="static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
    <link href="static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
    <link href="static/common/jeesite.css" type="text/css" rel="stylesheet" />
    <meta name="decorator" content="default">
    <script type="text/javascript" src="static/js/checkType.js"></script>
</head>
<body>
    <br>
    <center>
        <h4>角色管理</h4>
    </center><br>
    <form id="inputForm" class="form-horizontal" action="#" method="post" novalidate="novalidate">
        <input id="id" name="id" type="hidden" value="${role.id}">        
        <div class="control-group">
            <label class="control-label">角色名称：</label>
            <div class="controls">
                <input id="rolename" name="rolename" class="input-xlarge required" type="text" value="${role.rolename}" maxlength="50">
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
              
        <div class="control-group">
            <label class="control-label">状态：</label>
            <div class="controls">

                <select id="status"  name="status" style="width:200px;height: 27px">
                    <c:choose>
                        <c:when test="${role.status==1}">
                            <option value="1">可用</option>
                            <option value="0">禁用</option>
                        </c:when>
                        <c:otherwise>
                            <option value="0">禁用</option>
                            <option value="1">可用</option>
                        </c:otherwise>
                    </c:choose> 
                </select><span class="help-inline"><font color="red">*</font> </span>
            </div>
         </div>
        
        <div class="control-group">
            <label class="control-label">角色授权:</label>
            <div class="controls">
                <!-- 树形结构 -->
                <ul id="menu"></ul>
            </div>
        </div>
        
        <div class="control-group">
            <label class="control-label">备注信息：</label>
            <div class="controls">
                <input id="rolenote" name="rolenote" class="input-xlarge " type="text" value="${role.rolenote}" maxlength="255">
            </div>
        </div>
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="button" value="保 存">&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="closeTopWindow()">
        </div>
    </form>
</body>
<script type="text/javascript">
//关闭窗口
function closeTopWindow(){
    parent.$("#topWindow").window("close");
}

//提交修改
$("#btnSubmit").on("click", function search(event){
	var id = $("#id").val();
    var rolename = $("#rolename").val();
    var status = $("#status").val();
    var rolenote = $("#rolenote").val();
    
	//获取所有选中的权限
	var rows = $('#menu').tree('getChecked','checked');
	var arr =[];
	for ( var i = 0; i <rows.length; i++){
		arr[i]=rows[i].id;
	}
	
	var roleData={"id":id,"rolename":rolename,"status":status,"rolenote":rolenote,"arr":arr};
    $.post("role/updateRole",
    		roleData,
            function(data){
                if (data.success==true) {
                    closeTopWindow();
                }else{
                    $.messager.alert('Warning',"失败");
                }
            },
            "json");
})

$("#menu").tree({
        url:"role/menu?roleId="+$("#id").val(),
        checkbox:true,
        loadFilter:function(data){
            if(data.success==true){
                return data.result;
            } else{
                return null;
            }
        }
    })
</script>
</html>