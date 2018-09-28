<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 

<%-- <%@ page language="java" import="com.zhuozhengsoft.pageoffice.*" %>

<%
PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
poCtrl1.setServerPage("poserver.zz"); //此行必须
poCtrl1.setSaveFilePage("savefile.jsp");//如要保存文件，此行必须
//打开文件
poCtrl1.webOpen("url/a.docx", OpenModeType.docNormalEdit, "张三");
%> --%>
  
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

<!-- PageOffice -->
<script type="text/javascript" src=" pageoffice.js" id="po_js_main"></script>

<link href="static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />

<link href="static/jquery-select2/3.4/select2.min.css" rel="stylesheet" />

<link href="static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />

<link href="static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<link href="static/common/jeesite.css" type="text/css" rel="stylesheet" />

<meta name="decorator" content="default">
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
<%-- <%=poCtrl1.getHtmlCode("PageOfficeCtrl1")%>	 --%>
	<ul class="nav nav-tabs">
		<li class="active"><a href="company/personInfo">人才信息列表</a></li>
		<li><a href="addTalentInformation">人才信息添加</a></li>
	</ul>
	<form id="searchForm" class="breadcrumb form-search" method="post">
		<ul class="ul-form">
			<li><label>客户名称：</label>
				<input id="name" name="name" class="input-medium" type="text"  maxlength="20" style="width: 85px">
			</li>
			<li><label>身份证号：</label>
				<input id="idcard" name="idcard" class="input-medium" type="text"  maxlength="20">
			</li>
			<li><label>求职意向：</label>
				<input id="jobintension" name="jobintension" class="input-medium" type="text"  >
			</li>
			<li><label>期望工作地：</label>
				<input id="foraddress" name="foraddress" class="input-medium" type="text">
			</li>
			<li class="btns"><input id="search" class="btn btn-primary" type="button" value="查询"></li>
			<li class="clearfix"></li>
		</ul>
	</form>

	<div id="toolbar"></div>
	<table id="datagrid" class="table table-striped table-bordered table-condensed"></table>
	
	<!--  <a href="javascript:POBrowser.openWindowModeless(/word.jsp','width=1200px;height=800px;');">在线打开Word</a>	 -->

	<script  type="text/javascript">
	
		$("#datagrid").datagrid({
			url:"TalentInformationList",
			method:"post",
			toolbar:"#toolbar",
			fitColumns:true, //自动调整列宽
			idField:"id", // 指示服务器传递的数据中，哪个字段是主键
			rownumbers:true, // 显示行号
			pagination:true, // 使用分页
			columns:[[
				{field:"id",title:"编号",width:30},
				{field:"name",title:"客户名称",width:45},			
				{field:"idcard",title:"身份证号",width:120},
				{field:"jobintension",title:"期望职位",width:100},
				{field:"forprice",title:"期望月薪",width:60},
				{field:"foraddress",title:"期望工作地",width:60},
				{field:"resumeurl",title:"个人简历URL",width:160,formatter:myFormatter},
				
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
		
		//给 “ 个人简历 ” 一列设置超链接 
		function myFormatter(value, row, index){
			var name ;
			if(value){//没有判断，容易出现substring()为定义
				name = value.substring(17);
			}
			return "<a href='url/"+name+"'>"+value+"</a>";
		}
		
	
		
		
		function doSearch(idcard,name,jobintension,foraddress){
			$("#datagrid").datagrid("load",{            
		        "idcard":idcard,
		        "jobintension":jobintension,
		        "name":name,
		        "foraddress":foraddress
		        
		    })
		}	
		
		//查询用户	
		$("#search").on("click", function search(){
		    var idcard = $("#idcard").val();
		    var jobintension = $("#jobintension").val();
		    var name = $("#name").val();
		    var foraddress = $("#foraddress").val();
		    //alert(foraddress)
		    doSearch(idcard,name,jobintension,foraddress)
		})

	//修改用户信息
	function PorEdit(id){
		var options={title:"用户信息修改",url:"Talent/changTalentUser?id="+id};
		openTopWindow(options);
	}
	
	//删除用户
	function Delete(id){
		$.messager.confirm("操作提示", "您确定要删除该用户吗？", function (data) {
			if(data) {
				$.post("TalentService/deleteTalentUser",
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
	
	// 打开窗口
	function openTopWindow(options){            
	    options.width  = options.width ? options.width : 600;
	    options.height = options.height ? options.height : 500;
	    options.title =  options.title ?  options.title : " ";
	    options.onClose = options.onClose ? options.onClose : function(){};
	    if(!options.url){
	        // 如果url不存在，抛出异常，下面的代码就不再执行
	        throw "新窗口必须设置url属性";
	    }
	     
	    parent.$("#topWindow").window({
	        width: options.width,
	        height: options.height,
	        title: options.title,
	        modal: true, // 设置弹出的窗口为 模式窗口  
	        content:"<iframe width='100%' height='100%' src='"+options.url+"'>",
	        onClose:function(){ 
	            doSearch();
	        }   // 窗口关闭时的回调函数             
	    })
	}
</script>	

	<script type="text/javascript">//<!-- 无框架时，左上角显示菜单图标按钮。
		if(!(self.frameElement && self.frameElement.tagName=="IFRAME")){
			$("body").prepend("<i id=\"btnMenu\" class=\"icon-th-list\" style=\"cursor:pointer;float:right;margin:10px;\"></i><div id=\"menuContent\"></div>");
			$("#btnMenu").click(function(){
				top.$.jBox('get:/jeesite/a/sys/menu/treeselect;JSESSIONID=3baf732ded45486a8f110d13601a4c9b', {title:'选择菜单', buttons:{'关闭':true}, width:300, height: 350, top:10});
				//if ($("#menuContent").html()==""){$.get("/jeesite/a/sys/menu/treeselect", function(data){$("#menuContent").html(data);});}else{$("#menuContent").toggle(100);}
			});
		}//-->
	</script>

</body>
</html>