<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="f.jsp" method="post" name="form1">
	<input type="hidden" name="action" value="login">
	<table border="1">
		<tr>
			<td>id</td>
			<td><input type="text" name="id" required></td>
		</tr>
		<tr>
			<td>password</td>
			<td><input type="password" name="passwd" required></td>
		</tr>
		<tr>
			<td colspan='2'>
			<mytag:login/>
			</td>
		</tr>
	</table>
	
	<h1>${memberBean.id}님 환영합니다.!</h1>
</form>
</body>
</html>