<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

img{

 width :200px;
 height :200px;
}
</style>
</head>
<body>
	<div>
 <button onClick="location.href='form.do'">이미지 업로드</button>

		<table>

			<c:forEach items="${datas}" var="vo">

				<tr>
					<td>제목 :</td>
					<td>${vo.title}</td>
				</tr>
				
				<tr>
					<td>이미지 :</td>
					<td><img src="images/${vo.filename}"></td>
				</tr>
				<tr>
				<td><button onClick="location.href='delete.do?filename=${vo.filename}&pk=${vo.pk}'">이미지 삭제</button></td>
				</tr>
				
			</c:forEach>
		</table>


	</div>

</body>
</html>