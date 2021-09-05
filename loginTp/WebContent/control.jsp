<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id = "memberVO" class = "model.member.MemberVO"/>
<jsp:useBean id = "memberDAO" class = "model.member.MemberDAO"/>
<jsp:useBean id = "arrayList" class = "java.util.ArrayList"/>
<jsp:setProperty property="*" name="memberVO" />


<%
String action = request.getParameter("action");	//action을 받아오는곳
System.out.println(action); //action 무엇인지 로깅

if(action.equals("main")){
	arrayList = memberDAO.selectAll();
	System.out.println(arrayList);
	request.setAttribute("memal", arrayList);
	pageContext.forward("main.jsp");
}

else if(action.equals("login")){
	pageContext.forward("login.jsp");
}

else if(action.equals("signup")){
	pageContext.forward("signup.jsp");
}


else if(action.equals("login_reg")){
	System.out.println("로그인id"+memberVO.getId());
	System.out.println("로그인pw"+memberVO.getPw());
	memberVO = memberDAO.login(memberVO);
	if(memberVO != null){
		System.out.println("로그인성공");
		session.setAttribute("mem", memberVO);
		response.sendRedirect("control.jsp?action=main");
	}
	else{
		out.println("<script>alert('로그인 실패!');history.go(-1)</script>");
	}	
}

else if(action.equals("signup_reg")){
	System.out.println(memberVO);
	if(memberDAO.insertOne(memberVO)){
		System.out.println("회원가입성공");
		pageContext.forward("login.jsp");
	}
	else{
		out.println("<script>alert('회원가입실패!');history.go(-1)</script>");
	}	
	
}

else if(action.equals("signout")){
	if(memberDAO.delete(memberVO)){
		System.out.println("탈퇴완료");
		response.sendRedirect("control.jsp?action=main");
	}
	else{
		out.println("<script>alert('탈퇴실패!');history.go(-1)</script>");
	}	
	
	
}

















%>

