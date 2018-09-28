<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
	<base href="<%=basePath%>">
	<%@include file="/script.html" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>安思普惠人事代理系统 登录</title>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta name="author" content="http://jeesite.com/"/>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
	<meta http-equiv="Expires" content="0">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-store">
	<link href="static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link href="static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
	<link href="static/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
	<link href="static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
	<link href="static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
	<link href="static/common/jeesite.css" type="text/css" rel="stylesheet" />
	
	<meta name="decorator" content="blank">
	<style type="text/css">
      html,body,table{background-color:#f5f5f5;width:100%;text-align:center;}.form-signin-heading{font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:36px;margin-bottom:20px;color:#0663a2;}
      .form-signin{position:relative;text-align:left;width:300px;padding:25px 29px 29px;margin:0 auto 20px;background-color:#fff;border:1px solid #e5e5e5;
        	-webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;-webkit-box-shadow:0 1px 2px rgba(0,0,0,.05);-moz-box-shadow:0 1px 2px rgba(0,0,0,.05);box-shadow:0 1px 2px rgba(0,0,0,.05);}
      .form-signin .checkbox{margin-bottom:10px;color:#0663a2;} .form-signin .input-label{font-size:16px;line-height:23px;color:#999;}
      .form-signin .input-block-level{font-size:16px;height:auto;margin-bottom:15px;padding:7px;*width:283px;*padding-bottom:0;_padding:7px 7px 9px 7px;}
      .form-signin .btn.btn-large{font-size:16px;} .form-signin #themeSwitch{position:absolute;right:15px;bottom:10px;}
      .form-signin div.validateCode {padding-bottom:15px;} .mid{vertical-align:middle;}
      .header{height:80px;padding-top:20px;} .alert{position:relative;width:300px;margin:0 auto;*padding-bottom:0px;}
      label.error{background:none;width:270px;font-weight:normal;color:inherit;margin:0;}
    </style>
    <style>
        #captcha1,
        #captcha2 {
            width: 300px;
            display: inline-block;
        }
       
        #notice1,
        #notice2 {
            color: red;
        }
        #wait1, #wait2 {
            text-align: left;
            color: #666;
            margin: 0;
        }
    </style>
</head>
<body >
<style type="text/css">.com-zoom-flash{position:absolute;bottom:0;right:0;height:10px;width:10px;}.com-zoom-tips-hack{height:2em;display:none;}.com-zoom-warn{display:none;position:fixed;top:0;left:0;width:100%;line-height:2em;text-align:center;background-color:#ffffcc;font-size:1.2em;color:#8e0000;z-index:9999;}.com-zoom-close{float:right;width:2em;height:1.7em;line-height:1.7em;font-weight:bold;font-family:"Tahoma";color:#8e0000;text-decoration:none;}.com-zoom-close:hover{color:#d8ab57;}.com-zoom-notip{color:#3e3e3e;text-decoration:underline;}.com-zoom-notip:hover{text-decoration:none;}.com-zoom-text{margin:0;}</style><div class="com-zoom-flash"><object codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="10" height="10"><param name="movie" value="/jeesite-master/static/flash/zoom.swf"><param name="quality" value="high"><param name="wmode" value="transparent"><embed src="/jeesite-master/static/flash/zoom.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="10" height="10" wmode="transparent"></object></div><div class="com-zoom-tips-hack"><div class="com-zoom-warn" id="com-zoom-warn"><a href="javascript:void(0);" class="com-zoom-close" title="关闭" hidefocus="true" target="_self">x</a><p class="com-zoom-text">您的浏览器目前处于缩放状态，会导致网页显示不正常，您可以键盘按“ctrl+数字0”组合键恢复初始状态。<a href="javascript:void(0);" class="com-zoom-notip" target="_self" hidefocus="true">不再提示</a></p></div></div>
	
	<div class="header">
		<div id="messageBox" class="alert alert-error hide"><button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error"></label>
		</div>
	</div>
	
	<h1 class="form-signin-heading">安思普惠人事代理系统</h1>
	<span id="info"></span>
	<form id="loginForm" class="form-signin" action="login" method="post" novalidate="novalidate">
		<label class="input-label" for="username">登录名</label>
		<input type="text" id="username" name="username" class="input-block-level required" >
		
		<label class="input-label" for="password">密码</label>
		<input type="password" id="password" name="password" class="input-block-level required">
		<!-- 验证码 -->
		<div>
	        <div id="captcha1">
	            <p id="wait1" class="show">正在加载验证码......</p>
	        </div>
	    </div>
	    <br>
	    <p id="notice1" class="hide">请先完成滑动验证</p>
	    
		<input class="btn btn-large btn-primary" type="submit" value="登 录">&nbsp;&nbsp;
		<label for="rememberMe" title="下次不需要再登录">
		<input type="checkbox" id="rememberMe" name="rememberMe" value="true"> 记住我（公共场所慎用）</label>	
	</form>
	<div class="footer">
		Copyright © 2012-2018 <a href="http://localhost:8080/jeesite-master/f">安思普惠人事代理系统</a> - Powered By <a href="http://www.zhidisoft.com/" target="_blank">zhidisoft</a> V1.2.7 
	</div>
</body>
<script type="text/javascript" src="static/js/gt.js"></script>
<script type="text/javascript">
$(function(){
	
	$.ajax({
        url: "gt/register1?t=" + (new Date()).getTime(), // 加随机数防止缓存
        type: "get",
        dataType: "json",
        success: function (data) {
            initGeetest({
                gt: data.gt,
                challenge: data.challenge,
                new_captcha: data.new_captcha, 
                offline: !data.success, 
                product: "float", 
                width: "100%"
            }, handler1);
        }
    });
	
	var handler1 = function (captchaObj) {
		$("#loginForm").on("submit", function(e) {
            var result = captchaObj.getValidate();
            if (!result) {
                $("#notice1").show();
                setTimeout(function () {
                    $("#notice1").hide();
                }, 2000);
                e.preventDefault();
                
            }else{
            	//阻止默认的提交行为:
                e.preventDefault();
                //获取用户名和密码
                var username = $("input[name=username]").val();
                var password = $("input[name=password]").val();
                var rememberMe = $("#rememberMe").is(":checked");
                //校验用户名
                if( !username ){
                    $("#info").text("请输入用户名").css({"color":"#f00"});
                    return;
                }
                
                //校验密码
                if( !password ){
                    $("#info").text("请输入密码").css({"color":"#f00"});
                    return;
                }

                //发送ajax请求给服务器
                
                $.post("login",
                        {"username":username,"password":password,"rememberMe":rememberMe},
                        function(data){
                            if(data && data.success){
                                window.location.href="toIndex";
                                return;
                            }else if(data && !data.success){
                                $("#info").text(data.message).css({"color":"#f00"});
                                return;
                            }
                        },
                        "json"
                );
            }
        });
        // 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
        captchaObj.appendTo("#captcha1");
        captchaObj.onReady(function () {
            $("#wait1").hide();
        });
    };
})

</script>
</html>