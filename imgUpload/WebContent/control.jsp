<%@ page language="java" contentType="text/html; charset=UTF-8 "
	pageEncoding="UTF-8"
	import="java.util.*, model.img.*, com.oreilly.servlet.MultipartRequest, com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>

<jsp:useBean id="imgVO" class="model.img.ImgVO" scope = "session"  />
<jsp:useBean id="imgDAO" class="model.img.ImgDAO" />
<jsp:setProperty property="*" name="imgVO"/>

<%
	request.setCharacterEncoding("UTF-8");
%>


<%
	String action = request.getParameter("action");
	System.out.println(action);

	if (action.equals("main")) {
		ArrayList<ImgVO> datas = imgDAO.selectDB_all();
		session.setAttribute("datas", datas);
		pageContext.forward("main.jsp");
	}

	else if (action.equals("form")) {
		pageContext.forward("form.jsp");
	}

	else if (action.equals("upload")) {
		
		String saveDir = request.getRealPath("images"); //서버 이미지 경로
		String encoding = "UTF-8";
		System.out.println("imgVO  :" + imgVO);
		System.out.println("절대경로  :" + saveDir);


		
			int maxSize = 16 * 1024 * 1024; //
			MultipartRequest multi = new MultipartRequest(request, saveDir, maxSize, encoding,
					new DefaultFileRenamePolicy());
			imgVO.setTitle( multi.getParameter("title"));
			imgVO.setFilename( (String)multi.getFilesystemName("filename"));
			System.out.println(multi.getFilesystemName("filename"));
		
		
			if (imgDAO.m_insertDB(imgVO)) {
				System.out.println("업로드 성공");
				response.sendRedirect("control.jsp?action=main");
			} else {
				System.out.println("업로드 실패");
				response.sendRedirect("control.jsp?action=form");
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