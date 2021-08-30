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
	messageDAO.insertDB(messageVO);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 등록</title>
</head>
<body>
	<h2>등록완료</h2>
	<script type="text/javascript">
		window.location = "index.jsp";
	</script>
</body>
</html>