<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% 
response.setHeader("Pragma","No-Cache"); 
response.setHeader("Cache-Control","No-Cache"); 
response.setDateHeader("Expires", 0); 
%> 
<%@ tagliburi="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html>
<html>
<head>
  <base href="<%=basePath%>">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>安思惠普人事代理系统</title>
  <%@include file="/script.html" %>
</head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body class="easyui-layout">
<!-- 顶部div开始 -->
<div  data-options="region:'north'" style="height: 50px">
    <div class="theme-navigate" style="background-color: #0c80d7">
        <div class="left" >
            <h3>安思惠普人事代理系统</h3>
        </div>
        <div class="right">
            <a href="#" class="easyui-menubutton theme-navigate-user-button" data-options="menu:'.theme-navigate-user-panel'">
                <p>${root.username}</p>
            </a>
            <div class="theme-navigate-user-panel">
                <dl>
                    <dd>
                        <img src="static/easyui/themes/insdep/images/portrait86x86.png" width="86" height="86">
                        <b class="badge-prompt"><acl:user property="userName"/></b>
                    </dd>
                    <dt>
                        <a class="theme-navigate-user-modify">修改资料</a>
                        <a id="logout" class="theme-navigate-user-logout" href="logout">注销</a>
                    </dt>
                </dl>
            </div>
        </div>
    </div>
</div>
<!-- 顶部div结束 -->

<!-- 菜单div开始 -->
<div data-options="region:'west'" style="width: 200px">
    <div id="aa" class="easyui-accordion" style="width:300px;height:40px;">
	    <p style="color:#0099FF;font-size: 18px;">业务菜单</p>
	</div>
    <!-- 树形结构 -->
    <ul id="menu"></ul>
</div>
<!-- 菜单div结束 -->

<!-- 中间tab开始 -->
<div data-options="region:'center'">
    <!-- 选项卡面板效果 -->
    <div id="tt" class="easyui-tabs" data-options="fit:true"></div>
</div>
<!-- 中间tab结束 -->

<!-- 以后打开弹窗需要依据的div -->
<div id="topWindow" style="overflow: hidden;"></div>

<!-- 底部版权开始 -->
<div data-options="region:'south'" style="height: 30px">
    <div id="footer" class="row-fluid" style="text-align: center">
        Copyright © 2012-2018 安思普惠人事代理系统 - Powered By <a href="http://jeesite.com/" target="_blank">JeeSite</a> V1.2.7
    </div>
</div>
<!-- 底部版权结束 -->
</body>
<script type="text/javascript">
	$("#menu").tree({
	    url:"menu" ,
	    loadFilter:function(data){
	        if(data.success==true){
	            return data.result;
	        } else{
	            return null;
	        }
	    } ,
	    onClick:function(node){
	    	doAdd(node.text,node.attributes.url);
	    }
	})  
	
	//添加tab
    function doAdd(title,url) {
        if(url != null && url!=''){
        	var exists = $("#tt").tabs("exists", title);
            if (exists) {
                // 当前页面中存在要添加的tab标签页
                $("#tt").tabs("select", title);
            }else {
                //动态添加一个选项卡面板
                $("#tt")
                    .tabs(
                        "add",
                        {
                            title : title,
                            content : '<iframe frameborder="0" width="100%" height="100%" src="'+url+'"></iframe>',
                            closable : true,
                        });
            }
        }
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

        $("#topWindow").window({
            width: options.width,
            height: options.height,
            title: options.title,
            modal: true, // 设置弹出的窗口为 模式窗口
            content:"<iframe width='100%' height='100%' src='"+options.url+"'>",
            onClose:options.onClose   // 窗口关闭时的回调函数
        })
    }

    // 关闭窗口
    function closeTopWindow(){
        $("#topWindow").window("close");
    }
    

    history.go(1);  
</script>
</html>