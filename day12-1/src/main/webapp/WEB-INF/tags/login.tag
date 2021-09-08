<%@ tag language="java" pageEncoding="UTF-8"%>

<ul>
	<%
	if (session.getAttribute("mem") == null) {
	%>
	<li><a href="#intro">로그인</a></li>
	<li><a href="#contact">회원가입</a></li>
	<%
	} else {
	%>
	<li><a href="#work">내정보</a></li>
	<li><a href="./control.jsp?action=logout">로그아웃</a></li>
	<%
	}
	%>
</ul>