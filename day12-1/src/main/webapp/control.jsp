<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="memberDAO" class="model.member.MemberDAO" />
<jsp:useBean id="memberVO" class="model.member.MemberVO" scope="session" />
<jsp:setProperty property="*" name="memberVO" />


<%
String action = request.getParameter("action");
System.out.println(action); //액션 출력

if (action.equals("main")) {
	if (memberDAO.selectAll() != null) {
		session.setAttribute("memal", memberDAO.selectAll());
	}
	pageContext.forward("main.jsp");
}

else if (action.equals("login")) {

	if (memberDAO.selectOne(memberVO) != null) {
		memberVO=memberDAO.selectOne(memberVO);
		
		session.setAttribute("mem", memberVO);
		System.out.println(memberVO);
		System.out.println("로그인 성공");
		response.sendRedirect("control.jsp?action=main");
	} else {
		out.println("<script>alert('로그인 실패!');history.go(-1)</script>");
	}
}

else if (action.equals("logout")) {
	session.invalidate();
	response.sendRedirect("control.jsp?action=main");
}

else if (action.equals("signup")) {

	if (memberDAO.insert(memberVO)) {
		System.out.println("회원가입 성공");
		response.sendRedirect("control.jsp?action=main");
	} else {
		out.println("<script>alert('회원가입 실패!');history.go(-1)</script>");
	}
}

else if (action.equals("signout")) {

	if (memberDAO.delete(memberVO)) {
		System.out.println("회원탈퇴 성공");
		session.invalidate();
		response.sendRedirect("control.jsp?action=main");
	} else {
		out.println("<script>alert('회원탈퇴 실패!');history.go(-1)</script>");
	}
}

else if (action.equals("update")) {

	if (memberDAO.update(memberVO)) {
		session.setAttribute("mem", memberVO);
		System.out.println("정보변경 성공");
		response.sendRedirect("control.jsp?action=main");
	} else {
		out.println("<script>alert('회원변경 실패!');history.go(-1)</script>");
	}
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