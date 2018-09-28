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
    <title>短信模板管理</title>
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
        <h4>短信模板管理</h4>
    </center><br>
    <form id="inputForm" class="form-horizontal" action="#" method="post" novalidate="novalidate">
        <input id="id" name="id" type="hidden" value="${sms.id}">        
        <div class="control-group">
            <label class="control-label">模板编码：</label>
            <div class="controls">
                <input id="templateCode" name="templateCode" class="input-xlarge required" type="text" value="${sms.templateCode}" maxlength="50">
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">主题：</label>
            <div class="controls">
                <input id="subject" name="subject" class="input-xlarge required" type="text" value="${sms.subject}" maxlength="50">
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">内容：</label>
            <div class="controls">
                <textarea id="content" name="content" class="input-xlarge ">${sms.content}</textarea>
            </div>
        </div> 
        <div class="control-group">
            <label class="control-label">顺序号：</label>
            <div class="controls">
                <input id="orderId" name="orderId" class="input-xlarge required" type="text" value="${sms.orderId}" maxlength="50">
                <span class="help-inline"><font color="red">*</font> </span>
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
    $.post("sms/updateSMS",
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