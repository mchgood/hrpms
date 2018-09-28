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
	<jsp:useBean id="News" class="com.zhidisoft.contentmanagement.entity.News" scope="request"/>
<div id="tt" class="easyui-tabs" style="width:100%;height:500px;">
 <div title="新闻修改" data-options="closable:false" style="overflow:auto;padding:20px;display:none;">
  
  <span style="color: red">${message  }</span>
		<f:form action="news/newsEdit?id=${news.id }"  method="post" commandName="News" >
	    	<table >
	    		<tr>
	    			<td>标题:</td>
	    			<td><input class="easyui-textbox" type="text" name="newstitle" value="${news.newstitle }" /><span style="color: red">*<f:errors path="newstitle"></f:errors></span></td>
	    		</tr>
	    	
	    		<tr>
	    			<td>内容:</td>
	    			<td><input class="easyui-textbox" name="content" value="${news.content }" data-options="multiline:true" style="height:80px;width: 250px"/></td>
	    		</tr>
	    		<tr>
	    			<td>状态:</td>
	    			<td>
	    			<select class="combobox" name="status" id="status">
	    			<option value="0" >显示</option>
	    			<option value="1" >隐藏</option>
	    			</select>
	    			</td>
	    		</tr>
	    		
	    		<tr>
	    			<td>备注:</td>
	    			<td><input class="easyui-textbox" type="text" name="remark"  value="${news.remark }"/></td>
	    		</tr>
	    	</table>

	    	<button  id="send" class="easyui-linkbutton" style="background-color:#1E90FF;color: white;margin-left:100px" >保存</button>
	    	<a href="javascript:history.back();" class="easyui-linkbutton" style="background-color:#1E90FF;color: white;">返回</a>
	    </f:form>
    </div>
</div>
</body>
<script type="text/javascript">
//提交表单
$("#send").on("click",function(){
	$("#send").submit();
	
})
$(function(){
   
    var status =${news.status};
    $("#status").find("option[value = '"+status+"']").attr("selected","selected");
})

</script>
</html>