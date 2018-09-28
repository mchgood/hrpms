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
<div id="smsedit" class="easyui-tabs" style="width:100%;height:500px;">
 <div title="邮件修改" data-options="closable:false" style="overflow:auto;padding:20px;display:none;">
 
  
   
	    	<table >
	    		<tr>
	    			<td>发送人:</td>
	    			<td><input class="easyui-textbox" type="text" value="${sms.sendName }" readonly="readonly"></input></td>
	    		</tr>
	    		<tr>
	    			<td>接收人电话:</td>
	    			<td><input class="easyui-textbox" type="text" value="${sms.telephone }" readonly="readonly"></input></td>
	    		</tr>
	    	
	    		<tr>
	    			<td>短信内容:</td>
	    			<td><input class="easyui-textbox" value="${sms.content }" data-options="multiline:true" style="height:200px;width: 300px" readonly="readonly"></input></td>
	    		</tr>
	    	
	    	</table>
	    	
	    	    	<a href="javascript:history.back();" class="easyui-linkbutton" style="background-color:#1E90FF;color: white;">返回</a>
	   
    </div>
</div>
</body>


</html>