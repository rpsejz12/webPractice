<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="signup.do" method="post">
		<table border="1">
			<tr>
				<td>ID</td>
				<td><input type="text" name="id" id="id" required></td>
			</tr>
			<tr>
				<td>PW</td>
				<td><input type="password" name="pw" required></td>
			</tr>
			<tr>
				<td>NAME</td>
				<td><input type="text" name="name" required></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="회원가입"></td>
			</tr>
		</table>
	</form>
</body>
</html>