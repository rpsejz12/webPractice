<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table>

		<c:forEach var="v" items="${datas}">
			<tr>
				<td>${v.tpk}</td>
				<td>${v.content}</td>
			</tr>
		</c:forEach>


			<!-- 	
			
	
				7-1 - (7-1)%5 - 5 + 1
				10-1- (10-1)%5 -5 + 1
				11-1 - (11-1)%- 5 + 1
				
				11-1 - (11-1)% + 5 + 1
				  
				
				
 -->
		<tr>
			<td><a href="main.do?page=1">처음으로</a></td>
			<c:if test="${paging.startPage != 1}">
			<td><a href="main.do?page=${(page-1)-(page-1)%paging.perPageSet - paging.perPageSet + 1}">이전</a></td>
			</c:if>
			<c:forEach begin="${paging.startPage}" end="${paging.endPage}"
				var="p">
				<td><a href="main.do?page=${p}">${p}</a></td>
			</c:forEach>
			<c:if test="${paging.endPage != paging.lastPage}">
			<td><a href="main.do?page=${(page-1)-(page-1)%paging.perPageSet + paging.perPageSet + 1}">다음</a></td>
			</c:if>
			<td><a href="main.do?page=${paging.lastPage}">마지막으로</a></td>
		</tr>



	</table>

</body>
</html>

