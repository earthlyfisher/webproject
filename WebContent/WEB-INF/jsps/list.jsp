<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'index.jsp' starting page</title>
<link rel="stylesheet" type="text/css" href="skin/mm.css">
<script type="text/javascript" src="skin/jquery-1.10.1.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	This is users list.
	<br />
	<div align="center" style="margin: 35px">
		<table>
			<tr>
				<th id="th1"><a id="add">增加</a></th>
			</tr>
		</table>
		<table class="mytable" id="mytable">
			<tr>
				<th>编号</th>
				<th>姓名</th>
				<th>密码</th>
				<th>删除</th>
				<th>修改</th>
			</tr>
			<c:forEach items="${userlist}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.password}</td>
					<td><a href="del_${user.id}.do">删除</a></td>
					<td><a id="update" onclick="update(this);">修改</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div id="mydiv" style="display: none;">
		<form action="add.do" method="post" id="myform" class="myform">
			<p>创建用户</p>
			姓名：<input type="text" name="name"><br /> 密码：<input
				type="text" name="password"><br /> <input type="submit"
				value="提交"> <input type="button" value="取消" id="consel"
				onclick="_consel();" />
		</form>
	</div>
	<div id="updateDiv" style="display: none;">
		<form action="" method="post" id="updateForm" class="myform">
			<p>修改用户信息</p>
			<input type="hidden" name="id" /> 姓名：<input type="text" name="name"
				value="hhh"><br /> 密码：<input type="text" name="password"><br />
			<input type="button" value="提交" onclick="updateUser();" /> <input
				type="button" value="取消" id="consel" onclick="_consel();" />
		</form>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$("#add").click(function() {

			$("#mydiv").css("display", "block");
			$("#myform").css("display", "block");
			$("#myform").css("top", "120px");
			$("#myform").parent();
		});
		$(document).ready(function() {
			$("#mytable tr:nth-child(even)").css("background", "#fffff7");
		});

	});
	function _add() {
		document.getElementById("mydiv").style.display = "block";
		document.getElementById("mydiv").style.height = "10px";
		document.getElementById("myform").style.top = "120px";
		document.getElementById("myform").style.display = "block";
	}

	function _consel() {
		$("#mydiv").css("display", "none");
		$("#updateDiv").css("display", "none");
	}

	function update(id) {
		var ss = id.parentNode.parentNode;
		var ff = ss.getElementsByTagName("td");
		$("#updateDiv").find("input[name='id']").val(ff[0].innerHTML);
		$("#updateDiv").find("input[name='name']").val(ff[1].innerHTML);
		$("#updateDiv").find("input[name='password']").val(ff[2].innerHTML);
		$("#updateDiv").find("input[name='name']").val();
		$("#updateDiv").css("display", "block");
		$("#updateForm").css("display", "block");
		$("#updateForm").css("top", "50px");
	}

	function updateUser() {

		var id = $("#updateDiv").find("input[name='id']").val();
		var name = $("#updateDiv").find("input[name='name']").val();
		var pass = $("#updateDiv").find("input[name='password']").val();
		$.post("update.do", {
			id : id,
			name : name,
			pass : pass
		}, function(data) {
			$("#updateDiv").css("display", "none");
			window.location.reload();
		});
	}
</script>

</html>