<%@ tag language="java" pageEncoding="UTF-8"%>

<ul>
	<%
	if (session.getAttribute("mem") == null) {
	%>
	<li><a href="#login">로그인</a></li>
	<li><a href="#signup">회원가입</a></li>
	<%
	} else {
	%>
	<li><a href="#edit">내정보</a></li>
	<li><a href="#minsert">새글 등록</a></li>
	<li><a href="./control.jsp?action=logout">로그아웃</a></li>
	<%
	}
	%>
</ul>