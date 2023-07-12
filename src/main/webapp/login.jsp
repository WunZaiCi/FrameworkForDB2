<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Login Page</h1>
	<form action="login.do">
		<label>用户名</label>
		<input type="text" name="name" value="${param.name }">
		<br>
		<label>密码</label>
		<input type="password" name="passwd">
		<br>
		<button>登陆</button>		
	</form>
	<div style="color:red;">
		${error }
	</div>
</body>
</html>