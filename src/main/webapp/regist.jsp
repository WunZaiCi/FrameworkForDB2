<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Regist Page</h1>
	<form action="regist.do">
		<label>用户名</label>
		<input type="text" name="username" value="${param.name }">
		<br>
		<label>密码</label>
		<input type="password" name="password">
		<br>
		<label>重复密码</label>
		<input type="password" name="password2">
		<br>
		<label>真实姓名</label>
		<input type="password" name="nickname">
		<br>
		<label>手机号</label>
		<input type="password" name="phone">
		<br>
		<button>注册</button>		
	</form>
	<div style="color:red;">
		${error }
	</div>
</body>
</html>