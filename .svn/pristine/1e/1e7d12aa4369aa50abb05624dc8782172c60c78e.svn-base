<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<base href="<%=basePath%>">
<%@include file="/script.html" %>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>菜单管理</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" /><meta name="author" content="http://jeesite.com/"/>
    <meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
    <meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
    <link href="static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
    <link href="static/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
    <link href="static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
    <link href="static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
    <link href="static/common/jeesite.css" type="text/css" rel="stylesheet" />
    <meta name="decorator" content="default">
    <script type="text/javascript" src="static/js/checkType.js"></script>
</head>
<body>
    <br>
    <center>
        <h4>菜单管理</h4>
    </center><br>
    <form id="inputForm" class="form-horizontal" action="#" method="post" novalidate="novalidate">
        <input id="id" name="id" type="hidden" value="${func.id}">  
        
        <div class="control-group">
            <label class="control-label">上级菜单：</label>
            <div class="controls">

                <select id="parentid"  name="parentid" style="width:200px;height: 27px">
                    <c:set var="parId" value="${func.parentid}"/>
                    <c:forEach var="item" items="${parentMenu}">
                           <c:choose>
								 <c:when test="${item.id == parId}">
								    <option value="${item.id}" selected>${item.funcname}</option>
								 </c:when>
								 <c:otherwise>
								    <option value="${item.id}">${item.funcname}</option>
								 </c:otherwise>
							</c:choose>
					</c:forEach> 
					
                </select><span class="help-inline"><font color="red">*</font> </span>
            </div>
         </div>
               
        <div class="control-group">
            <label class="control-label">权限名称：</label>
            <div class="controls">
                <input id="funcname" name="funcname" class="input-xlarge required" type="text" value="${func.funcname}" maxlength="50">
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        
        <div class="control-group">
            <label class="control-label">权限链接：</label>
            <div class="controls">
                <input id="funcurl" name="funcurl" class="input-xlarge required" type="text" value="${func.funcurl}" maxlength="50">
            </div>
        </div>
              
        <div class="control-group">
            <label class="control-label">权限类型：</label>
            <div class="controls">

                <select id="functype"  name="functype" style="width:200px;height: 27px">
                    <c:forEach var="item" items="${DictList}">
                        <c:if test="${item.name=='fun_flag'}">
                            <c:choose>
							    <c:when test="${item.value==func.functype}">
							      <option value="${item.value}" selected>${item.label}</option>
							    </c:when>
							    <c:otherwise>
							      <option value="${item.value}">${item.label}</option>
							    </c:otherwise>
							</c:choose>					    
						</c:if>
                    </c:forEach> 
                </select><span class="help-inline"><font color="red">*</font> </span>
            </div>
         </div>
         
         <div class="control-group">
            <label class="control-label">状态：</label>
            <div class="controls">

                <select id="status"  name="status" style="width:200px;height: 27px">
                    <c:forEach var="item" items="${DictList}">
                        <c:if test="${item.name=='del_flag'}">
                            <c:choose>
                                <c:when test="${item.value==func.status}">
                                  <option value="${item.value}" selected>${item.label}</option>
                                </c:when>
                                <c:otherwise>
                                  <option value="${item.value}">${item.label}</option>
                                </c:otherwise>
                            </c:choose>                     
                        </c:if>
                    </c:forEach>
                </select><span class="help-inline"><font color="red">*</font> </span>
            </div>
         </div>
        
        <div class="control-group">
            <label class="control-label">备注信息：</label>
            <div class="controls">
                <input id="funcnote" name="funcnote" class="input-xlarge " type="text" value="${func.funcnote}" maxlength="255">
            </div>
        </div>
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="button" value="保 存">&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="closeTopWindow()">
        </div>
    </form>
</body>
<script type="text/javascript">
//关闭窗口
function closeTopWindow(){
    parent.$("#topWindow").window("close");
}

//提交修改
$("#btnSubmit").on("click", function search(event){
    $.post("func/updateFunc",
    		$("#inputForm").serialize(),
            function(data){
                if (data.success==true) {
                    closeTopWindow();
                }else{
                    $.messager.alert('Warning',"失败");
                }
            },
            "json");
})

</script>
</html>