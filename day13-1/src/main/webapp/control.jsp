<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import = "java.util.*, model.bank.*"%>
<jsp:useBean id="bankDAO" class="model.bank.BankDAO" />



<%
String action = request.getParameter("action");
System.out.println(action);

if (action.equals("main")) {
	if (bankDAO.getBankAll() != null) {
		session.setAttribute("bankAl", bankDAO.getBankAll());
	}
	
	pageContext.forward("main.jsp");
}

else if (action.equals("trans")) {
	String transOut = (String)request.getParameter("out");
	String transIn = (String)request.getParameter("in");
	int balance = Integer.parseInt(request.getParameter("balance"));
	
	System.out.println(transOut);
	System.out.println(transIn);
	
	BankVO vo1 = bankDAO.getBankOne(transOut);
	BankVO vo2 = bankDAO.getBankOne(transIn);
	
	System.out.println(vo1);
	System.out.println(vo2);
	
	
	if (bankDAO.trans(vo1, vo2, balance)) { // 수행결과가 실행가능상태라면,
		out.println("<script>alert('가능!');</script>");
		out.println("<script>location.href='control.jsp?action=main';</script>");
	} else {
		out.println("<script>alert('불가능!');</script>");
		out.println("<script>location.href='control.jsp?action=main';</script>");
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