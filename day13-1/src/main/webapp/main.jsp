<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



	<form action="control.jsp" method="post" name="form1">
		<input type="hidden" name="action" value="trans">
		<table border="1">
			<tr>
				<td>보내는 사람</td>
				<td><input type="text" name="out" placeholder="이름" required></td>
			</tr>
			<tr>
				<td>보낼 금액</td>
				<td><input type="text" name="balance" placeholder="금액" required></td>
			</tr>
			<tr>
				<td>받는 사람</td>
				<td><input type="text" name="in" placeholder="이름" required></td>
			</tr>
			<tr>
			<td colspan='2'>
			 <input type="submit" value="계좌이체">
			</td>
			</tr>
		</table>
	</form>



	<table border="1">
		<c:forEach var='v' items='${bankAl}'>
			<tr>
				<td>${v}</td>
			</tr>
		</c:forEach>
	</table>

	<hr>













</body>
</html>