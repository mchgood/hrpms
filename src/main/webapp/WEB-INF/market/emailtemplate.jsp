<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE html >
<html>

<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <%@include file="../../script.html" %>
</head>
<body>
<table id="emailtemplate" style="width: 100%" ></table>


</body>
<script type="text/javascript">
//邮件模板分页 
$("#emailtemplate").datagrid({
	url:"market-email/emailtemplate",
	method:"post",
	toolbar:"#toolbar",
	fitColumns:true, //自动调整列宽
	singleSelect:true,
	rownumbers:true, // 显示行号
	pagination:true, // 使用分页
	columns:[[
		{field:"orderId",title:"模板编号",width:20},
		{field:"subject",title:"标题"},
		{field:"content",title:"内容",width:200},
	
	
	]],
	loadFilter:function(data){
		if(data && data.success){
			return {"total":data.result.total,"rows":data.result.rows};
		}
	},
	
     onSelect:function(rowIndex, rowData){
    	 $.messager.confirm('警告','确定选择该模板吗？',function(r){
    		    if (r){
    		    	
    		    	window.location.href="market-email/company/emailRecord/form?orderId="+rowData.orderId;
    		    	
    		    	}
    			
    		    })
     }
})	




</script>
</html>