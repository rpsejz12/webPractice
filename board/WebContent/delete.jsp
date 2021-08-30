<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,model.message.*"
	errorPage="error.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="messageDAO" class="model.message.MessageDAO" />
<jsp:useBean id="messageVO" class="model.message.MessageVO" />
<jsp:setProperty property="*" name="messageVO" />

<%
	messageDAO.deleteDB(messageVO);
%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>삭제완료</h2>
	<script type="text/javascript">
			window.location = "index.jsp";
	</script>
</body>
</html>