<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    
<!DOCTYPE html>
<html  style="overflow-x:auto;overflow-y:auto;">
<head>
<meta charset="utf-8">
<title>公积金管理 - Powered By JeeSite</title>
<base href="<%=basePath%>">
<%@include file="/script.html" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" /><meta name="author" content="http://jeesite.com/"/>
<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
<link href="static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
<!--[if lte IE 7]><link href="../static/bootstrap/2.3.1/awesome/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
<!--[if lte IE 6]><link href="../static/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
<script src="../static/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->
<link href="static/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
<link href="static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
<link href="static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<link href="static/common/jeesite.css" type="text/css" rel="stylesheet" />
<meta name="decorator" content="default">
</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active"><a href="company/accumulationFund">公积金列表</a></li>
		<li><a href="company/accumulationFund/form">公积金缴费</a></li>
	</ul>
	
	<form id="searchForm" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="1">
		<input id="pageSize" name="pageSize" type="hidden" value="10">
		<ul class="ul-form">
			<li><label>身份证号：</label>
				<input id="idcard" name="idcard" class="input-medium" type="text" value="" maxlength="20">
			</li>
			<li><label>公积金号：</label>
				<input id="accountno" name="accountno" class="input-medium" type="text" value="" maxlength="20">
			</li>
			<li class="btns"><input id="search" class="btn btn-primary" type="button" value="查询"></li>
			<li>
				<a class="btn btn-primary" href="export?num=exportModel" style="color: white;">模板下载</a>
			</li>		
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" onclick="download()" type="hidden" value="导出">
			 <a class="btn btn-primary" href="export?num=export" style="color: white;">导出</a>
			 </li>
			<li class="clearfix"></li>
		</ul>
	</form>
	
	<form action="import" id="uploadForm" enctype="multipart/form-data" method="post" style="margin-top: -30px">   
		<input id="upfile" type="file" name="upfile" style=" display: none">  
		<a class ="btn btn-primary" style="margin-left: 820px;margin-top: -26px" onclick= "$('#upfile').click();" > 添加文件</a>
  		<input type="submit" value="导入" id="upLoadPayerCreditInfoExcel" style="margin-top: -26px" class="btn btn-primary" name="btn">  
	</form>
 
	
    <div id="toolbar"></div>
	<table id="datagrid" class="table table-striped table-bordered table-condensed"></table>


<script type="text/javascript">

	$("#datagrid").datagrid({
		url:"ProvidentFund",
		method:"post",
		toolbar:"#toolbar",
		fitColumns:true, //自动调整列宽
		idField:"id", // 指示服务器传递的数据中，哪个字段是主键
		rownumbers:true, // 显示行号
		pagination:true, // 使用分页
		columns:[[
			{field:"id",title:"编号",width:40,hidden:'true'},
			{field:"name",title:"客户名称",width:40},			
			{field:"idcard",title:"身份证号",width:120},
			{field:"accountno",title:"公积金号",width:120},
			{field:"paydate",title:"缴费期间",width:130,align:'center'},
			{field:"paymoney",title:"缴费金额",width:60,align:'center'},
			{field:"proxyfee",title:"代理费用",width:60,align:'center'},
			
			{field:"status",title:"状态",width:60,align:'center',formatter:function(value,rowValue,index){
				if(value==0){
					return "可用";
				}else {
					return "禁用";
				}
			}},
			{field:'option',title:'操作',formatter:function(value,rowData,rowIndex){
                var e = '<a href="javascript:void(0)" onclick="PorEdit(' + rowData.id + ')"> 修改</a> ';
                var d = '<a href="javascript:void(0)" onclick="Delete(' + rowData.id + ')"> 删除</a>';
                return e + d;
                },width:120}
		]],
		loadFilter:function(data){
			if(data && data.success){
				return {"total":data.result.total,"rows":data.result.rows};
			}
		}
	})
 
	
function doSearch(idcard,accountno){
	$("#datagrid").datagrid("load",{            
        "idcard":idcard,
        "accountno":accountno
    })
}	
	
		
	//修改用户信息
	function PorEdit(id){
		var options={title:"用户信息修改",url:"ProvidentFund/changeFundUser?id="+id};
		openTopWindow(options);
	}
	
	//删除用户
	function Delete(id){
		$.messager.confirm("操作提示", "您确定要删除该用户吗？", function (data) {
			if(data) {
				$.post("ProvidentFund/deleteFundUser",
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
	
	//查询用户	
	$("#search").on("click", function search(){
	    var idcard = $("#idcard").val();
	    var accountno = $("#accountno").val();
	    doSearch(idcard,accountno);
	})

	//添加用户
	$("#add").on("click", function search(){
		var options={title:"添加用户信息",url:"changeUser?id=0"};
	    openTopWindow(options);
	})

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
	
	function send(num){
		$.ajax({
            type: "post",
            url: "export",
            data: {num:num},
            dataType: "json",
            success: function(data){
                        alter(data.message)
                     }
        });
	}
	//导出的方法
	function download() {
		var url = 'export';
		var xhr = new XMLHttpRequest();
		xhr.open('GET', url, true); // 也可以使用POST方式，根据接口
		xhr.responseType = "blob"; // 返回类型blob
		// 定义请求完成的处理函数，请求前也可以增加加载框/禁用下载按钮逻辑
		xhr.onload = function() {
			// 请求完成
			if (this.status === 200) {
			    // 返回200
				var blob = this.response;
				var reader = new FileReader();
				reader.readAsDataURL(blob); // 转换为base64，可以直接放入a标签 href
				reader.onload = function(e) {
					// 转换完成，创建一个a标签用于下载
					var a = document.createElement('a');
					a.download = 'data.xlsx';
					a.href = e.target.result;
					$("body").append(a); // 修复firefox中无法触发click
					a.click();
					$(a).remove();
			  	}
		 	}
		};
		// 发送ajax请求
		xhr.send()
	}

</script>

</body>
</html>