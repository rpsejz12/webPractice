<%@page import="com.mysql.cj.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,model.message.*,model.member.*"
	errorPage="error.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="messageDAO" class="model.message.MessageDAO" />
<jsp:useBean id="messageVO" class="model.message.MessageVO" />
<jsp:useBean id="memberDAO" class="model.member.MemberDAO" />
<jsp:useBean id="memberVO" class="model.member.MemberVO" scope="session" />
<jsp:setProperty property="*" name="messageVO" />
<jsp:setProperty property="*" name="memberVO" />
<%
// 컨트롤러를 호출했을때의 요청 파라미터를 분석
System.out.println(memberVO);
String action = request.getParameter("action");
System.out.println(action);

if (action.equals("list")) {
	ArrayList<MessageVO> datas = messageDAO.getDBList();
	request.setAttribute("datas", datas);
	pageContext.forward("list.jsp");
}

else if (action.equals("search")) {
	ArrayList<MessageVO> datas = messageDAO.searchDBList(messageVO, request.getParameter("type"));
	request.setAttribute("datas", datas);
	pageContext.forward("list.jsp");
}

else if (action.equals("myList")) {
	ArrayList<MessageVO> datas = messageDAO.myList(memberVO);
	request.setAttribute("datas", datas);
	pageContext.forward("list.jsp");
}

else if (action.equals("loginForm")) {
	pageContext.forward("loginForm.jsp");
} else if (action.equals("form")) {
	// dao.insert()
	pageContext.forward("form.jsp");
} else if (action.equals("signupForm")) {
	pageContext.forward("signupForm.jsp");
} else if (action.equals("insert")) {
	// dao.insert()
	if (messageDAO.insertDB(messageVO)) {
		response.sendRedirect("control.jsp?action=list");
	} else {
		throw new Exception("DB 추가 오류 발생!");
	}
} else if (action.equals("delete")) {
	if (messageDAO.deleteDB(messageVO)) {
		response.sendRedirect("control.jsp?action=list");
	} else {
		throw new Exception("DB 삭제 오류 발생!");
	}
} else if (action.equals("update")) {
	if (messageDAO.updateDB(messageVO)) {
		response.sendRedirect("control.jsp?action=list");
	} else {
		throw new Exception("DB 변경 오류 발생!");
	}
} else if (action.equals("edit")) {
	MessageVO data = messageDAO.getDBData(messageVO);
	request.setAttribute("data", data);
	pageContext.forward("edit.jsp");
	// 사용자가 잘못된 mnum를 건네는 경우는 없다!
	// -> 오류페이지로 처리!
}

else if (action.equals("login")) {
	MemberVO mem = memberDAO.check(memberVO);
	if (mem != null) {
		session.setAttribute("mem", mem);
		response.sendRedirect("control.jsp?action=list");
	} else {
		out.println("<script>alert('로그인 실패!');history.go(-1)</script>");
	}
}

else if (action.equals("logout")) {
	session.invalidate();
	pageContext.forward("index.jsp");
}

else if (action.equals("signup")) {
	if (memberDAO.insert(memberVO)) {
		response.sendRedirect("control.jsp?action=loginForm");
	} else {
		throw new Exception("DB 삭제 오류 발생!");
	}
}

else if (action.equals("signout")) {
	
	if (memberDAO.delete(memberVO)) {
		session.invalidate();
		response.sendRedirect("control.jsp?action=list");
	} else {
		throw new Exception("DB 삭제 오류 발생!");
	}
}

else {
	out.println("파라미터 확인!");
}
%>













<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>컨트롤러</title>
</head>
<body>



</body>
</html>