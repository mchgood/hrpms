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
    <title>公积金用户信息管理 - Powered By JeeSite</title>
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
        <h4>公积金用户修改</h4>
    </center><br>
    <form id="inputForm" class="form-horizontal" method="post" novalidate="novalidate" style="height: 30px">
        <input id="id" name="id" type="hidden" value="${map.getId()}">        
        <div class="control-group">
            <label class="control-label">用户名称：</label>
            <div class="controls">
                <input id="username" name="name" class="input-xlarge required" type="text" value="${map.getName()}" maxlength="50" style="height: 30px">
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">身份证号：</label>
            <div class="controls">
                <input id="password" name="idcard" class="input-xlarge required" type="text" value="${map.getIdcard()}" maxlength="50" style="height: 30px">
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">公积金号：</label>
            <div class="controls">
                <input id="email" name="accountno" class="input-xlarge " type="text" value="${map.getAccountno()}" maxlength="50" style="height: 30px">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">缴费期间：</label>
            <div class="controls">
                <input id="phone" name="paydate" class="input-xlarge " type="text" value="${map.getPaydate()}" maxlength="13" style="height: 30px">
            </div>
        </div>        
        <div class="control-group">
            <label class="control-label">用户状态：</label>
            <div class="controls">

                <select id="status"  name="status" style="width:200px;height: 27px">
                    <c:choose>
                        <c:when test="${map.getStatus()==0}">
                            <option value="0">可用</option>
                            <option value="1">禁用</option>
                        </c:when>
                        <c:otherwise>
                            <option value="1">禁用</option>
                            <option value="0">可用</option>
                        </c:otherwise>
                    </c:choose> 
				</select><span class="help-inline"><font color="red">*</font> </span>
            </div>
         </div> 
        
        <div class="control-group">
            <label class="control-label">缴费金额:</label>
   			<input id="admin" name="paymoney" class="required" type="text" value="${map.getPaymoney()}"  style="height: 30px">           
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

    $.post("ProvidentFund/updateFundUser",
            $("#inputForm").serialize(),
                function(data){
                    if (data.success==true) {
                    	closeTopWindow();
                    }else{
                    	$.messager.alert('Warning',data.message);
                    }
                },
                "json");
})
</script>
</html>