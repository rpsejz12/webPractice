<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="control.jsp" method="post" name="form1">
<input type = "hidden" name = "action" value = "signup">

<table>


<tr>
<td>id</td><td><input type = "text" name = "name"></td>
</tr>


<tr>
<td>id</td><td><input type = "text" name = "id"></td>
</tr>

<tr>
<td>pw</td><td><input type = "text" name = "pw"></td>
</tr>

</table>

<input type = "submit" value = "회원가입">

</form>

</body>
</html>