<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, model.product.*, model.buy.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="memberVO" class="model.member.MemberVO" scope="session" />
<jsp:useBean id="memberDAO" class="model.member.MemberDAO" />
<jsp:useBean id="productVO" class="model.product.ProductVO" />
<jsp:useBean id="productDAO" class="model.product.ProductDAO" />
<jsp:useBean id="buyVO" class="model.buy.BuyVO" />
<jsp:useBean id="buyDAO" class="model.buy.BuyDAO" />
<jsp:setProperty property="*" name="memberVO" />
<jsp:setProperty property="*" name="productVO" />
<jsp:setProperty property="*" name="buyVO" />


<%
	String action = request.getParameter("action"); //action을 받아오는곳
	System.out.println(action); //action 무엇인지 로깅

	if (action.equals("main")) {
		pageContext.forward("main.jsp");
	}

	else if (action.equals("loginForm")) {
		pageContext.forward("loginForm.jsp");
	}

	else if (action.equals("signupForm")) {
		pageContext.forward("signupForm.jsp");
	}

	else if (action.equals("login")) { //로그인
		System.out.println("로그인id" + memberVO.getMid());
		System.out.println("로그인pw" + memberVO.getMpw());
		memberVO = memberDAO.selectOne(memberVO);
		if (memberVO != null) {
			System.out.println("로그인성공");
			session.setAttribute("mem", memberVO);
			response.sendRedirect("control.jsp?action=main");
		} else {
			out.println("<script>alert('로그인 실패!');history.go(-1)</script>");
		}
	} else if (action.equals("logout")) {
		session.invalidate();
		System.out.println("로그아웃성공");
		pageContext.forward("control.jsp?action=main");

	}

	else if (action.equals("signup")) {
		System.out.println(memberVO);
		if (memberDAO.insert(memberVO)) {
			System.out.println("회원가입성공");
			pageContext.forward("loginForm.jsp");
		} else {
			out.println("<script>alert('회원가입실패!');history.go(-1)</script>");
		}

	}

	else if (action.equals("signout")) {
		if (memberDAO.delete(memberVO)) {
			System.out.println("탈퇴완료");
			response.sendRedirect("control.jsp?action=main");
		} else {
			out.println("<script>alert('탈퇴실패!');history.go(-1)</script>");
		}

	}

	else if (action.equals("list")) {
		ArrayList<ProductVO> pdlist = productDAO.selectAll();
		System.out.println(pdlist);
		if (pdlist != null) {
			session.setAttribute("pdlist", pdlist);
			System.out.println("리스트");
			pageContext.forward("list.jsp");
		}
	}

	/* request.setCharacterEncoding("UTF-8");
	   String product=request.getParameter("product");
	   // 세션에게 배열을 설정
	   // -> 배열 => 배열리스트(컬렉션)
	   ArrayList<String> list=(ArrayList)session.getAttribute("list");
	   if(list==null){
	      list=new ArrayList<String>();
	      session.setAttribute("list", list);
	   }
	   list.add(product);
	
	 */

	else if (action.equals("add")) {

		ArrayList<BuyVO> clist = (ArrayList<BuyVO>) session.getAttribute("clist");
		if (clist == null) {
			clist = new ArrayList<BuyVO>();
			session.setAttribute("clist", clist);
		}

		else {
			productVO = productDAO.selectOne(buyVO.getBname(), buyVO.getBnum());
			System.out.println(productVO);
			if (productVO != null) {
				buyVO.setBprice(buyDAO.price(productVO, buyVO));
				System.out.println(buyVO);
				clist.add(buyVO);
				response.sendRedirect("control.jsp?action=list");
			}
			else{
				out.println("<script>alert('개수초과!');history.go(-1)</script>");
			}

		}
	}

	else if (action.equals("buy")) {
		Boolean flag = false;
		ArrayList<BuyVO> clist = (ArrayList<BuyVO>) session.getAttribute("clist");
		for (BuyVO vo : clist) {
			productVO = productDAO.selectOne(buyVO.getBname(), buyVO.getBnum());
			if (productVO != null) {
				flag= true;
				productDAO.update(vo);
				session.setAttribute("clist", null);
				pageContext.forward("list.jsp");
			}
		}
		if(!flag){
			out.println("<script>alert('개수초과!');history.go(-1)</script>");
		}
	}
	   
	else if(action.equals("delete")){
		session.setAttribute("clist", null);
		pageContext.forward("control.jsp?action=list");
	}
%>

