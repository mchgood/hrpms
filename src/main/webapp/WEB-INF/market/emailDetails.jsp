<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE html >
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <%@include file="../../script.html" %>

</head>

<body>

<div  class="easyui-tabs" style="width:100%;height:500px;">
 <div title="邮件详情" data-options="closable:false" style="overflow:auto;padding:20px;display:none;">

	    	<table >
	    		<tr>
	    			<td>发信人:</td>
	    			<td><input class="easyui-textbox" type="text" value="${email.sendName }" readonly="readonly"/></td>
	    		</tr>
	    		<tr>
	    			<td>收信人:</td>
	    			<td><input class="easyui-textbox" type="text" value=${email.toAddr } readonly="readonly"/></td>
	    		</tr>
	    		
	    		<tr>
	    			<td>主题:</td>
	    			<td><input class="easyui-textbox" type="text" value=${email.subject } readonly="readonly"/></td>
	    		</tr>
	    		<tr>
	    			<td>内容:</td>
	    			<td><input class="easyui-textbox" value=${email.content } data-options="multiline:true" style="height:80px;width: 250px" readonly="readonly"/></td>
	    		</tr>
	    	
	    	</table>
	    	
	    	
	    	<a href="javascript:history.back();" class="easyui-linkbutton" style="background-color:#1E90FF;color: white;">返回</a>
	    
    </div>
</div>
</body>

</html>