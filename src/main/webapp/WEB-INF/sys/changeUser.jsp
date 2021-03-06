<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<base href="<%=basePath%>">

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
	
	<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
	<link href="static/easyui/themes/insdep/easyui.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="static/js/checkType.js"></script>
	<meta name="decorator" content="default">
    
</head>
<body>
    <br>
    <center>
        <h4>用户管理</h4>
    </center><br>
    <form id="inputForm" class="form-horizontal" action="#" method="post" novalidate="novalidate">
        <input id="id" name="id" type="hidden" value="${user.id}">        
        <div class="control-group">
            <label class="control-label">用户名称：</label>
            <div class="controls">
                <input id="username" name="username" class="easyui-validatebox" data-options="required:true,validType:'notNull'" type="text" value="${user.username}" maxlength="50">
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">用户密码：</label>
            <div class="controls">
                <input id="password" name="password" type="text" class="easyui-validatebox" data-options="required:true,validType:'notNull'">
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">电子邮件：</label>
            <div class="controls">
                <input id="email" name="email" class="input-xlarge " type="text" value="${user.email}" maxlength="50">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">手机号码：</label>
            <div class="controls">
                <input id="phone" name="phone" class="input-xlarge " type="text" value="${user.phone}" maxlength="13">
            </div>
        </div>        
        <div class="control-group">
            <label class="control-label">状态：</label>
            <div class="controls">

                <select id="status"  name="status" style="width:200px;height: 27px">
                    <c:choose>
                        <c:when test="${user.status==1}">
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
            <label class="control-label">用户角色:</label>
            <div class="controls">
                <c:set var="num" value="1"/>
	            <c:forEach var="item" items="${list}">
				    <span>
	                    
	                    <c:choose>
						    <c:when test="${item.status==1}">
						      <input id="check${num}" name="check" class="required" type="checkbox" value=<c:out value="${item.id}"/> checked=<c:out value="${item.status}"/>>
						    </c:when>
						    <c:otherwise>
						      <input id="check${num}" name="check" class="required" type="checkbox" value=<c:out value="${item.id}"/>>
						    </c:otherwise>
						</c:choose>
	                    <label for="admin"><c:out value="${item.name}"/></label>
	                </span>
	                <c:set var="num" value="${num+1}"/>
				</c:forEach>

                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        
        <div class="control-group">
            <label class="control-label">备注信息：</label>
            <div class="controls">
                <input id="usernote" name="usernote" class="input-xlarge " type="text" value="${user.usernote}" maxlength="255">
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
    var username = $("#username").val();
    var password = $("#password").val();
    var email = $("#email").val();
    var phone = $("#phone").val();
    var status = $("#status").val();
    var usernote = $("#usernote").val();
    
    obj = document.getElementsByName("check");
    arr = [];
    for(k in obj){
        if(obj[k].checked)
            arr.push(obj[k].value);
    }
    
    var userData = {"usernote":usernote,"id":id,"username":username,"password":password,"email":email,"phone":phone,"status":status,"arr":arr}
    
    if($("#inputForm").form('validate')){
    	if(arr.length>0){
    		$.post("sys/updateUser",
    	            userData,
    	                function(data){
    	                    if (data.success==true) {
    	                        closeTopWindow();
    	                    }else{
    	                        $.messager.alert('Warning',data.message);
    	                    }
    	                },
    	                "json");
    	}else{
    		$.messager.alert('操作提示','必须勾选至少一个',"warning");
    	}
        
    }else{
        $.messager.alert('操作提示','不能为空',"warning");
    }
})

</script>
</html>