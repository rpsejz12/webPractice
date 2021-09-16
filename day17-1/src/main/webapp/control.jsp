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
	String mcntt=request.getParameter("mcnt");
	String msgmid = request.getParameter("msgmid");
	String msgmidd = (String)session.getAttribute("msgmidd");
	String qid = request.getParameter("qid");
	String sid = (String)session.getAttribute("sid");
	String url="control.jsp?action=main";
	System.out.println();
	System.out.println("action :" + action);
	System.out.println("sid :" + sid);
	System.out.println("qid :" + qid);
	System.out.println(mcntt);
	int mcnt=2;
	if(mcntt!=null){
		mcnt=Integer.parseInt(mcntt);
	}
	
	
	url= url+ "&mcnt="+mcnt;
	if(msgmid!=null){
		url= url+ "&msgmid="+msgmid;
		url= url+ "&msgmidd="+msgmid;		
	}

	if(action.equals("main")){
		System.out.println("msgmid ="+msgmid);
		System.out.println("msgmidd ="+msgmidd);
		if(msgmid != null){
			if(msgmidd !=null){
				if(msgmidd.equals(msgmid)){
					System.out.println("msgmid == msgmidd");
				}
				else{
					System.out.println("msgmid != msgmidd");
					mcnt = 5;
				}
			}
			else{
				msgmidd = msgmid;
			}
			
		}
		else{
			if(msgmidd != null){
			msgmid = msgmidd;
			url= url+ "&msgmid=" + msgmidd;
			url= url+ "&msgmidd=" +msgmid;
			}
			else{
				msgmid="0";
			}
		}
		System.out.println("msgmid ="+msgmid);

		
		System.out.println(url);
		ArrayList<MessageSet> datas = new ArrayList<MessageSet>();
		
		if(qid == null){
		datas=mDAO.selectAll(sid, msgmid, mcnt);
		}else if(qid.equals("0")){
			session.setAttribute("sid", null);
			datas=mDAO.selectAll(null , msgmid, mcnt);
			
		}
		
		
		else{
			datas=mDAO.selectAll(qid, msgmid, mcnt);
			session.setAttribute("sid", qid);
		}
		

	
		request.setAttribute("mcnt", mcnt);
		session.setAttribute("msgmidd", msgmid);
		session.setAttribute("ulist", uDAO.selectAll());
		request.setAttribute("datas", datas);
		pageContext.forward("main.jsp");
	}
	
	
	else if (action.equals("login")) {

		if (uDAO.selectOne(uVO) != null) {
	uVO=uDAO.selectOne(uVO);
	System.out.println(url);
	session.setAttribute("mem", uVO);
	System.out.println(uVO);
	System.out.println("로그인 성공");
	response.sendRedirect(url);
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
	response.sendRedirect(url);
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
	response.sendRedirect(url);
		} else {
	out.println("<script>alert('회원변경 실패!');history.go(-1)</script>");
		}
	}
	
	else if (action.equals("minsert")) {
		if (mDAO.insert(mVO)) {
			pageContext.forward(url);
		} else {
			out.println("<script>alert('게시글 등록 실패!');history.go(-1)</script>");
		}
	}
	
	else if(action.equals("mdelete")){
		if(mDAO.delete(mVO)){
			pageContext.forward("control.jsp?action=main");
		}
		else {
			out.println("<script>alert('게시글 삭제 실패!');history.go(-1)</script>");
		}
		
	}
	else if(action.equals("rinsert")){
		MemberVO vo = (MemberVO)session.getAttribute("mem");		
		rVO.setId(vo.getId());
		if(rDAO.insert(rVO)){
			pageContext.forward(url);
		}
		else {
			out.println("<script>alert('리뷰 등록 실패!');history.go(-1)</script>");
		}
	}
	
	else if(action.equals("rdelete")){
		String mid = request.getParameter("mid");
		System.out.println(mid);
		int midd = Integer.parseInt(mid);
		rVO.setMid(midd);
		if(rDAO.delete(rVO)){
			pageContext.forward("control.jsp?action=main");
		}
		else {
			out.println("<script>alert('리뷰 삭제 실패!');history.go(-1)</script>");
		}
	}
	
	else if(action.equals("fcnt")){
		System.out.println("fcnt vo" + mVO);
		if(mDAO.update(mVO)){
			pageContext.forward(url);
		}
		else{
			out.println("<script>alert('좋아요 실패!');history.go(-1)</script>");
		}
	}

	
%>