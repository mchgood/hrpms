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
<title>劳务合作管理 - Powered By JeeSite</title>
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
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>

</head>
<body>
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="company/labor">劳务合作列表</a></li>
		<li><a href="LaborCooperationAdd">劳务合作添加</a></li>
	</ul>
	<form id="searchForm" class="breadcrumb form-search" >
		<ul class="ul-form">
			<li><label>客户名称：</label>
				<input id="name" name="name" class="input-medium" type="text" value="" maxlength="20">
			</li>
			<li><label>身份证号：</label>
				<input id="idcard" name="idcard" class="input-medium" type="text" value="" maxlength="20">
			</li>
			<li><label>合作公司：</label>
				<select id="companyid"  style="width: 180px;height: 30px">
					<option value="1">请选择</option>
					<c:forEach items="${companyList}" var="bean">
					<option value="${bean.getId()}">${bean.getName()}</option>
					</c:forEach>
				</select>
			</li>
			<li class="btns"><input id="searchBtn" class="btn btn-primary" type="button" value="查询"></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	
<div id="toolbar"></div>
<table id="datagrid" class="table table-striped table-bordered table-condensed"></table>
		
	<script  type="text/javascript">
	
		$("#datagrid").datagrid({
			url:"LaborCooperationList",
			method:"post",
			toolbar:"#toolbar",
			fitColumns:true, //自动调整列宽
			idField:"id", // 指示服务器传递的数据中，哪个字段是主键
			rownumbers:false, // 显示行号
			pagination:true, // 使用分页
			columns:[[
				{field:"id",title:"编号",width:30},
				{field:"name",title:"客户名称",width:45},			
				{field:"idcard",title:"身份证号",width:140},
				{field:"companyName",title:"合作公司",width:120},
				{field:"jobtype",title:"工作类别",width:60,formatter:function(value,rowValue,index){
					if(value==0){
						return "兼职";
					}else if(value==1) {
						return "全职";
					}else{
						return "外派";
					}
				}},
				{field:"companyprice",title:"公司单价",width:60},
				{field:"personprice",title:"个人单价",width:60},
				{field:"starttime",title:"开始时间",width:90},
				{field:"endtime",title:"结束时间",width:90},
				
				{field:"status",title:"状态",width:40,formatter:function(value,rowValue,index){
					if(value==0){
						return "有效";
					}else {
						return "无效";
					}
				}},
				{field:'option',title:'操作',formatter:function(value,rowData,rowIndex){
		            var e = '<a href="javascript:void(0)" onclick="PorEdit(' + rowData.id + ')"> 修改</a> ';
		            var d = '<a href="javascript:void(0)" onclick="Delete(' + rowData.id + ')"> 删除</a>';
		            return e + d;
		            },width:100}
			]],
			loadFilter:function(data){
				if(data && data.success){
					return {"total":data.result.total,"rows":data.result.rows};
				}
			}
		})
	
	
	function doSearch(idcard,name,companyid){
				$("#datagrid").datagrid("load",{            
			        "idcard":idcard,
			        "companyid":companyid,
			        "name":name
       
			    })
			}	
			
		//查询用户	
		$("#searchBtn").on("click", function search(){
		    var idcard = $("#idcard").val();
		    var companyid = $("#companyid").val();
		    var name = $("#name").val();
		    doSearch(idcard,name,companyid)
		})
		
		//删除用户
	function Delete(id){
		$.messager.confirm("操作提示", "您确定要删除该用户吗？", function (data) {
			if(data) {
				$.post("LaborCooperationList/delete",
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
</body>
</html>