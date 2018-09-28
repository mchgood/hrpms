<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html >
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <%@include file="../../script.html" %>

</head>
<body>

   <!-- 在jsp页面中引入实体类  -->
	<jsp:useBean id="email" class="com.zhidisoft.marketingmanagement.entity.Email" scope="request"/>
<div  class="easyui-tabs" style="width:100%;height:500px;">
 <div title="邮件发送" data-options="closable:false" style="overflow:auto;padding:20px;display:none;">
<f:form action="market-email/sendemail" method="post" commandName="email" id="ff" >
	    	<table >
	    		<tr>
	    			<td>发信人:</td>
	    			<td><input class="easyui-textbox" type="text" value="${root.username }" readonly="readonly"/></td>
	    		</tr>
	    		<tr>
	    			<td>收信人:</td>
	    			<td><input class="easyui-textbox" type="text" name="toAddr" /><span style="color: red">*<f:errors path="toAddr"></f:errors></span></td>
	    		</tr>
	    		<tr>
	    			<td>模板:</td>
	    			<td><input class="easyui-textbox" type="text" value="${emailTemplate.orderId }" readonly="readonly" /><button type="button" id="template" class="easyui-linkbutton" style="background-color:#1E90FF;color: white;">选择</button></td>
	    		</tr>
	    		
	    		<tr>
	    			<td>主题:</td>
	    			<td><input class="easyui-textbox" type="text" value="${emailTemplate.subject }" name="subject" /><span style="color: red">*<f:errors path="subject"></f:errors></span></td>
	    		</tr>
	    		<tr>
	    			<td>内容:</td>
	    			<td><input class="easyui-textbox" name="content" value="${emailTemplate.content}" data-options="multiline:true" style="height:100px;width: 250px" /><span style="color: red">*<f:errors path="content"></f:errors></span></td>
	    		</tr>
	    	
	    	</table>
	    
	    	 <button   id="send" class="easyui-linkbutton" style="background-color:#1E90FF;color: white;margin-left:100px" >发送</button> 
	    	
	</f:form>    
    </div>
</div>

</body>
<script type="text/javascript">
//提交表单
 $("#send").on("click",function(){
	
	$("#send").submit(); 
	
})

 
 
//选择模板
$("#template").on("click",function(){
	
	 parent.openTopWindow({
		
		
		title:"选择模板",
		url:"market-email/toemailtemplate" ,
		
		 
			
});

	
})
	
</script>
</html>