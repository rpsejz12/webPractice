<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri ="/WEB-INF/tld/test.tld" prefix="mytag"%>

<>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var = "v" items ="${memal}">

<p>"${v}"</p>

</c:forEach>

<mytag:test color="pink" size="20">"${mem}"</mytag:test>

</body>
</html>