<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 컨트롤러 페이지에게
	// list.jsp를 보여달라고 요청
	// -> action=list
	pageContext.forward("control.jsp?action=list");
%>