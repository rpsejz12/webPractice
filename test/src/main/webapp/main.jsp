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
	<table border="1">
		<thead>
			<tr>
				<td>no.</td>
				<td>제목</td>
				<td>작성자</td>
				<td>내용</td>
				<td>날짜</td>
			</tr>
		</thead>
		<c:forEach var="v" items="${datas}">
			<tr>
				<td>${v.pk}</td>
				<td>${v.title}</td>
				<td>${v.id}</td>
				<td>${v.content}</td>
				<td>${v.wdate}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="2"><Button type="button"
					onclick="location.href='insert.jsp'">글 작성하기</Button></td>
		</tr>
	</table>


	<a href="main.do?page=1">처음으로</a>
	<c:if test="${paging.startPage != 1 }">
		<a href="main.do?page=${(page-1)-(page-1)%paging.perPageSet - paging.perPageSet + 1}">&lt;</a>
	</c:if>
	<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
		<c:if test="${paging.curPage == p}">
			<a href="main.do?page=${p}" style ="font-weight:bolder;">${p}</a>
		</c:if>
		<c:if test="${paging.curPage != p}">
			<a href="main.do?page=${p}">${p}</a>
		</c:if>
	</c:forEach>
	<c:if test="${paging.endPage != paging.lastPage}">
		<a href="main.do?page=${(page-1)-(page-1)%paging.perPageSet + paging.perPageSet + 1}">&gt;</a>
	</c:if>
	<a href="main.do?page=${paging.lastPage}">마지막으로</a>
</body>
</html>