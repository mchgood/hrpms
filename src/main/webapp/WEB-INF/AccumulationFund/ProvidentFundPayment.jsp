<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>   
<html style="overflow-x:auto;overflow-y:auto;">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公积金管理 - Powered By JeeSite</title>
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

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="company/accumulationFund">公积金列表</a></li>
		<li class="active"><a href="saved_resource_unEdit.html">公积金修改</a></li>
	</ul><br>
	<form id="inputForm" class="form-horizontal" novalidate="novalidate">
		<!-- <input id="id" name="id" type="hidden" value="1">-->
		 
		<div class="control-group">
			<label class="control-label">客户名称：</label>
			<div class="controls">
				<input id="idcard" name="name" class="input-xlarge required" type="text" maxlength="20" style="height:30px">
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label" >身份证号：</label>
			<div class="controls">
				<input id="idcard" name="idcard" class="input-xlarge required" type="text" maxlength="20" style="height:30px">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公积金号：</label>
			<div class="controls">
				<input id="accountno" name="accountno" class="input-xlarge required" type="text"  maxlength="20" style="height:30px">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">缴费期间：</label>
			<div class="controls">
				<input id="paydate" name="paydate" class="input-xlarge required" type="text"  maxlength="20" style="height:30px">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">缴费金额：</label>
			<div class="controls">
				<input id="paymoney" name="paymoney" class="input-xlarge required" type="text" style="height:30px">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代理费：</label>
			<div class="controls">
				<input id="proxyfee" name="proxyfee" class="input-xlarge " type="text" style="height:30px">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">

					<select name="status" class="input-xlarge" style="height:30px">
						<option value="0">已交
						</option><option value="1">未交
					</option></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<input id="remark" name="remark" class="input-xlarge " type="text" maxlength="256" style="height:30px">
			</div>
		</div>
		<div class="form-actions">
			<input id="save" class="btn btn-primary" type="button" value="保 存">&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
		</div>
	</form>

	<script type="text/javascript">//<!-- 无框架时，左上角显示菜单图标按钮。
		if(!(self.frameElement && self.frameElement.tagName=="IFRAME")){
			$("body").prepend("<i id=\"btnMenu\" class=\"icon-th-list\" style=\"cursor:pointer;float:right;margin:10px;\"></i><div id=\"menuContent\"></div>");
			$("#btnMenu").click(function(){
				top.$.jBox('get:/jeesite-master/a/sys/menu/treeselect;JSESSIONID=b30d2fa0f4264f6288e0788088ab4b22', {title:'选择菜单', buttons:{'关闭':true}, width:300, height: 350, top:10});
				//if ($("#menuContent").html()==""){$.get("/jeesite-master/a/sys/menu/treeselect", function(data){$("#menuContent").html(data);});}else{$("#menuContent").toggle(100);}
			});
		}//-->
	</script>

<script type="text/javascript">
	$("#save").on("click",function(){
		$.post("FundPayment",
			$("#inputForm").serialize(),
			function(data){
	          	   if(data.result){          
	          			 alert(data.message);
	          			window.location.replace("ProvidentFund"); 
	          			 parent.$("topWindow").window("close");
	          			 
	              	}else{
	              		alert(data.message) ; 
	              		
	                }
	         }, "json");     
	});
		/* $(document).ready(function() {
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
		}); */
</script>

</body>
</html>