<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<ul>
<%
	ArrayList<MemberVO> datas=(ArrayList<MemberVO>)application.getAttribute("datas");
	if(datas!=null){
		for(MemberVO v: datas){
			out.println("<li>"+v+"</li>");
		}
	}
%>
</ul>

<form action="signUpForm.jsp" method="post" name="form1">
<input type = "submit" value = "처음으로">
</form>



</body>
</html>