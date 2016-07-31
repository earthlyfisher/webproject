<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
    <title></title>
    <link href="<%=basePath%>pageres/css/StyleSheet.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <!--头部-->
    <div class="bk_top">
        <div class="bk_logo">
        </div>
        <div class="bk_home">
            <img src="<%=basePath%>pageres/images/home.png" />
            <p>您的位置：首页 >> 部门规章草案意见征集列表</p>
        </div>
    </div>

    <!--主要内容部分-->
	<div class="bk_main">
    	<p class="xx_title">账号管理</p>
        <div class="xx_con">
        	<div class="xx_con_left">
            	<ul>
                	<li>修改密码</li>
                    <li class="act">账号分配</li>
                    <li>已报信息</li>
                </ul>	
            </div>
            <div class="xx_con_right">
            	<table width="580px" border="0" cellspacing="0" cellpadding="0" class="xx">
                  <tr>
                    <td width="120px">用 户 名：</td>
                    <td><input style="width:400px;" type="text" class="check_input" /></td>
                  </tr>
                  <tr>
                    <td>报送名称：</td>
                    <td><input style="width:400px;" type="text" class="check_input" /></td>
                  </tr>
                  <tr>
                    <td>简　　称：</td>
                    <td><input style="width:400px;" type="text" class="check_input" /></td>
                  </tr>
                  <tr>
                    <td>备　　注：</td>
                    <td><input style="width:400px;" type="text" class="check_input" /></td>
                  </tr>
                  <tr>
                  	<td colspan="2">
                  	<a href="<%=basePath %>license/generatelicense.do"><button class="check_cx" style="background:url(<%=basePath%>pageres/images/fp.png); margin:30px 50px 0px 0px; float:right;"></button></a>
                    </td>
                  </tr>
                </table>

            </div>
            <div class="clear"></div>
        </div>
    </div>

    <!--底部-->
    <div class="bk_foot">
        <p style=" margin:20px auto 0px;">版权所有：山东省人民政府法制办公室  运行维护：山东省人民政府法制办公室</p>
        <p>E-MAIL：sdzffzw@163.com  电话：0531-82566969  地址：济南市省府前街1号  鲁ICP备06002095</p>
    </div>
</body>
</html>
