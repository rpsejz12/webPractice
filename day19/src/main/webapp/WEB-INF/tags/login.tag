<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<ul>

	<c:choose>
	<c:when test="${mem eq null}">
	<li><a href="#login">로그인</a></li>
	<li><a href="#signup">회원가입</a></li>
	</c:when>

	<c:when test="${mem ne null}">
	<li><a href="#edit">내정보</a></li>
	<li><a href="#minsert">새글 등록</a></li>
	<li><a href="./logout.do">로그아웃</a></li>
	</c:when>
	
	</c:choose>

</ul>