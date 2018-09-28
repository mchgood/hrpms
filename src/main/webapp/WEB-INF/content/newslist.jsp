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
<div id="news" class="easyui-tabs" style="width:100%;height:500px;" >
    <div title="新闻列表" style="padding:20px;display:none;" >
	<div id="email" >
			<span>标题：</span>
			<input type="text" class="easyui-textbox" id="newstitle" name="newstitle">			
		
			<a href="javascript:void(0)" class="easyui-linkbutton"  id="search-news" >查询</a>
			
		</div>
		<table id="datagrid-news" style="width: 100%"></table>
	
	
    </div>
    <div id="add" title="新闻添加" data-options="closable:false" style="overflow:auto;padding:20px;display:none;" >
		<!-- 在jsp页面中引入实体类  -->
	<jsp:useBean id="News" class="com.zhidisoft.contentmanagement.entity.News" scope="request"/>
 
		<f:form action="news/newsAdd"  method="post" commandName="News" >
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

$("#add").on("click",function(){
	window.lcation.href="news/tonewsedit";
})

//新闻分页 
$("#datagrid-news").datagrid({
	url:"news/newsInfo",
	method:"post",
	toolbar:"#toolbar",
	fitColumns:true, //自动调整列宽
	
	rownumbers:true, // 显示行号
	pagination:true, // 使用分页
	columns:[[
		{field:"id",title:"编号"},
		{field:"newstitle",title:"标题",width:80},
		{field:"status",title:"状态",formatter:function(value,rowValue,index){
			if(value==1){
				return "隐藏";
			}else {
				return "显示";
			}
		},width:80},
	
		{field:'option',title:'操作',formatter:function(value,rowData,rowIndex){
			
          var e = '<a href="news/tonewsedit?id=' + rowData.id + '" style="color: blue">修改</a> ';
          var d = '<a href="javascript:void(0)" onclick="emailDelete(' + rowData.id + ')"><img src="static/easyui/themes/icons/cancel.png"></a>';
          return e + d;
          },width:120}
	
	]],
	loadFilter:function(data){
		if(data && data.success){
			return {"total":data.result.total,"rows":data.result.rows};
		}
	}
})	

//新闻查询
$("#search-news").on("click", function search(){
    var newstitle = $("#newstitle").val();
  
  
    $("#datagrid-news").datagrid("load",{            
        "newstitle":newstitle
        
        
    })
})


//新闻删除
function emailDelete(id){
	
	$.messager.confirm('警告','确定要删除吗',function(r){
	    if (r){
	    	// 将id传递到后台
	    	$.ajax({
	    		url:"news/newsdelete",
	    		data:{"id":id},
	    		success:function(data){
	    		
	    			if(data && data.success){
	    				$.messager.alert("提示",data.message);
	    			
	    			}else{
	    				$.messager.alert("提示",data.message);
	    			}
	    		},
	    		datatype:"json"
	    	})
	    	
	    }
	});
	

}
</script>
</html>