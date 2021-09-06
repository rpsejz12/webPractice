<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="control.jsp" method="post" name="form1">
		<input type="hidden" name="action" value="add">
		<div class="col-lg-6 col-md-6">
			<select name="bname">
				<c:forEach var="v" items="${pdlist}">
					<option>
						<c:out value="${v.pname}"></c:out>
					</option>
					</c:forEach>
			</select> 
			<input type="number" name="bnum" min="1">
		</div>


		<input type="submit" class="site-btn" value="장바구니 추가">

		<c:forEach var="v" items="${clist}">

			<p>
				<c:out value="${v}"></c:out>
			</p>

		</c:forEach>
		<a href="./control.jsp?action=buy" class="primary-btn">구매</a>
		<a href="./control.jsp?action=delete" class="primary-btn">삭제</a>
	</form>

</body>
</html>