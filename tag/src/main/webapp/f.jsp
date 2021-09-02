<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>


<jsp:useBean id="memberBean" class="model.MemberBean" scope="session" />
<jsp:setProperty property="*" name="memberBean" />

<%
String action = request.getParameter("action");
if (action.equals("login")) {
	if (memberBean.check(memberBean)) {
		out.println("<script>alert('로그인 성공!');</script>");
		session.setAttribute("mem", memberBean);
		pageContext.forward("e.jsp");
	}

	else {
		out.println("<script>alert('로그인 실패!');history.go(-1)</script>");
	}
}
else if(action.equals("logout")){
	out.println("<script>alert('로그아웃 성공!');</script>");
	session.invalidate();
	pageContext.forward("e.jsp");	
}
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>