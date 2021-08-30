<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.message.*" errorPage="error.jsp"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="messageDAO" class="model.message.MessageDAO" />
<jsp:useBean id="messageVO" class="model.message.MessageVO" />
<jsp:setProperty property="*" name="messageVO"/>
<%
	// 컨트롤러를 호출했을때의 요청 파라미터를 분석
	String action=request.getParameter("action");
	System.out.println(action);

	if(action.equals("list")){
		ArrayList<MessageVO> datas=messageDAO.getDBList();
		request.setAttribute("datas", datas);
		pageContext.forward("list.jsp");
	}
	else if(action.equals("form")){
		// dao.insert()
		pageContext.forward("form.jsp");
	}
	else if(action.equals("insert")){
		// dao.insert()
		ArrayList<MessageVO> datas=messageDAO.getDBList();
		request.setAttribute("datas", datas);
		pageContext.forward("insert.jsp");
	}
	else if(action.equals("delete")){
		MessageVO data=messageDAO.getDBData(messageVO);
		request.setAttribute("data", data);
		pageContext.forward("delete.jsp");	
	}
	else if(action.equals("update")){
		MessageVO data=messageDAO.getDBData(messageVO);
		request.setAttribute("data", data);
		pageContext.forward("update.jsp");	
	}
	else if(action.equals("edit")){
		MessageVO data=messageDAO.getDBData(messageVO);
		request.setAttribute("data", data);
		pageContext.forward("edit.jsp");
		// 사용자가 잘못된 mnum를 건네는 경우는 없다!
		// -> 오류페이지로 처리!
	}
	else{
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