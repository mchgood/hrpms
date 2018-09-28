<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人才信息管理 - Powered By JeeSite</title>
<base href="<%=basePath%>">
<%@include file="/script.html" %>
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
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>

</head>
<body>
	
	<form id="inputForm" class="form-horizontal" method="post" novalidate="novalidate">
		<input id="id" name="id" type="hidden" value="">
	
		<table class="table table-bordered table-condensed">
			<tbody><tr>
				<td><label class="control-label">客户名称：</label></td>
				<td>
					<input type="text" name="name" value="${map.getName()}" class="input-xlarge required">
				</td>
				<td><label class="control-label">身份证号：</label></td>
				<td>
					<input id="idcard" name="idcard" class="input-xlarge required" type="text" value="${map.getIdcard()}" maxlength="20">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
		
			<tr>
				<td><label class="control-label">期望职位：</label></td>
				<td>
					<input id="jobintension" name="jobintension" class="input-xlarge required" type="text" value="${map.getJobintension()}" maxlength="256">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td>
					<label class="control-label">期望月薪：</label>
				</td><td>
					<input id="forprice" name="forprice" class="input-xlarge required" type="text" value="${map.getForprice()}" maxlength="256">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
				<td><label class="control-label">期望工作地：</label></td>
				<td>
					<input id="foraddress" name="foraddress" class="input-xlarge " type="text" value="${map.getForaddress()}" maxlength="20">
				</td>
			</tr>
			<tr>
				<td><label class="control-label">个人简历URL：</label></td>
				<td ><input id="resumeurl" name="resumeurl" maxlength="256" class="input-xxlarge " value="${map.getResumeurl()}"></td>
			</tr>
						
			<tr>
				<td><label class="control-label">状态：</label></td>
				<td>
					 <select id="status"  name="status" style="width:200px;height: 30px">
	                    <c:choose>
	                        <c:when test="${map.status==0}">
	                            <option value="0" selected="selected">有效</option>   
	                            <option value="1">无效</option>                        
	                        </c:when>
	                        <c:otherwise>
	                         	 <option value="0">有效</option>   
	                            <option value="1" selected="selected">无效</option> 
	                        </c:otherwise>
	                    </c:choose> 
	                 </select>
                 </td>
                 
	</table>
		
	 <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="button" value="保 存">&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="closeTopWindow()">
     </div>	
</form>

<script type="text/javascript">
//关闭窗口
function closeTopWindow(){
	parent.$("#topWindow").window("close");
}

//提交修改
$("#btnSubmit").on("click", function search(event){

    $.post("TalentService/updateTalent",
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

</body>
</html>