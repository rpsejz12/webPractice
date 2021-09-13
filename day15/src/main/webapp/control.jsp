<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.message.*,model.member.*,model.reply.*"%>
<jsp:useBean id="mDAO" class="model.message.MessageDAO" />
<jsp:useBean id="rDAO" class="model.reply.ReplyDAO" />
<jsp:useBean id="uDAO" class="model.member.MemberDAO" />
<jsp:useBean id="mVO" class="model.message.MessageVO" />
<jsp:setProperty property="*" name="mVO"/>
<jsp:useBean id="rVO" class="model.reply.ReplyVO" />
<jsp:setProperty property="*" name="rVO"/>
<jsp:useBean id="uVO" class="model.member.MemberVO" />
<jsp:setProperty property="*" name="uVO"/>
<%
	String action=request.getParameter("action");
	String url="control.jsp?action=main";	

	if(action.equals("main")){

	
		String msgmid = (String)request.getParameter("msgmid");
		if(msgmid == null){
			msgmid = "0";
		}
		
		
		ArrayList<MessageSet> datas=mDAO.selectAll(msgmid);
		request.setAttribute("datas", datas);
		pageContext.forward("main.jsp");
	}
	
	
	else if (action.equals("login")) {

		if (uDAO.selectOne(uVO) != null) {
	uVO=uDAO.selectOne(uVO);
	
	session.setAttribute("mem", uVO);
	System.out.println(uVO);
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

		if (uDAO.insert(uVO)) {
	System.out.println("회원가입 성공");
	response.sendRedirect("control.jsp?action=main");
		} else {
	out.println("<script>alert('회원가입 실패!');history.go(-1)</script>");
		}
	}

	else if (action.equals("signout")) {

		if (uDAO.delete((MemberVO)session.getAttribute("mem"))) {
	System.out.println("회원탈퇴 성공");
	session.invalidate();
	response.sendRedirect("control.jsp?action=main");
		} else {
	out.println("<script>alert('회원탈퇴 실패!');history.go(-1)</script>");
		}
	}

	else if (action.equals("update")) {

		if (uDAO.update(uVO)) {
	session.setAttribute("mem", uVO);
	System.out.println("정보변경 성공");
	response.sendRedirect("control.jsp?action=main");
		} else {
	out.println("<script>alert('회원변경 실패!');history.go(-1)</script>");
		}
	}
	
	else if (action.equals("minsert")) {

		if (mDAO.insert(mVO)) {
			response.sendRedirect("control.jsp?action=main");
		} else {
			out.println("<script>alert('게시글 등록 실패!');history.go(-1)</script>");
		}
	}
	
	else if(action.equals("mdelete")){
		if(mDAO.delete(mVO)){
			response.sendRedirect("control.jsp?action=main");
		}
		else {
			out.println("<script>alert('게시글 삭제 실패!');history.go(-1)</script>");
		}
		
	}
	else if(action.equals("rinsert")){
		MemberVO vo = (MemberVO)session.getAttribute("mem");		
		rVO.setId(vo.getId());
		if(rDAO.insert(rVO)){
			response.sendRedirect("control.jsp?action=main");
		}
		else {
			out.println("<script>alert('리뷰 등록 실패!');history.go(-1)</script>");
		}
	}
	
	else if(action.equals("rdelete")){
		if(rDAO.delete(rVO)){
			response.sendRedirect("control.jsp?action=main");
		}
		else {
			out.println("<script>alert('리뷰 삭제 실패!');history.go(-1)</script>");
		}
	}
	
	else if(action.equals("rinsertForm")){
		System.out.println(request.getParameter("mid"));
		
		session.setAttribute("rinsertForm", request.getParameter("mid"));
		
		response.sendRedirect("control.jsp?action=main");
	}
	
	else if(action.equals("viewadd")){
		
		
	}
%>