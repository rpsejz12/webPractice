<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<form action="login.do" method="post" name="form1">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pw"></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="로그인"></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="button" value="회원가입하기"  onclick="location.href='signup.jsp'"></td>
			</tr>
		</table>
	</form>

</body>
</html>