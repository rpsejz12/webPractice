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

		<table>
			<tr>
				<td><select name="bname">
						<c:forEach var="v" items="${pdlist}">
							<option>
								<c:out value="${v.pname}"></c:out>
							</option>
						</c:forEach>
				</select> <input type="number" name="bnum" min="1">
				<td>
			</tr>


			<tr>
				<td><input type="submit" class="site-btn" value="장바구니 추가">
				</td>
			</tr>

			<tr>
				<td><c:forEach var="v" items="${clist}">

						<p>
							<c:out value="${v}"></c:out>
						</p>

					</c:forEach></td>
			</tr>
		</table>

	</form>

	<a href="./control.jsp?action=buy" class="primary-btn">구매</a>
	<a href="./control.jsp?action=delete" class="primary-btn">삭제</a>


	<form action="control.jsp" method="post" name="form2">
		<input type="hidden" name="action" value="search">
		<table>
			<tr>
				<td><select name="type">
						<option value="1" selected>name</option>
						<option value="2">type</option>

				</select></td>
				<td><input type="text" name="content"
					placeholder="검색 하실 내용을 입력하세요."></td>
				<td><input type="submit" value="검색"></td>
			</tr>
		</table>
	</form>


	<table>

		<c:forEach var="v" items="${pdlist}">
			<tr>
				<td><c:out value="${v}"></c:out>
				<td>
			<tr>
		</c:forEach>

	</table>
</body>
</html>