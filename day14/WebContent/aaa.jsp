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

<form action="bbb.jsp" method="post">
	<select name = "coffee">
	<c:forEach var = "vo" items = "${coffeeAL}">
	<option>${vo.name}</option>
	</c:forEach>
	</select>
	<input type="submit" value="전송">
</form>

</body>
</html>