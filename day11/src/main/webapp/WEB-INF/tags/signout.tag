<%@ tag language="java" pageEncoding="UTF-8"%>

<%
	if (session.getAttribute("mem") != null) {
%>
<a href="control.jsp?action=signout"><span>회원탈퇴</span><i
	class="fa fa-angle-right"></i></a>
<%
	}
%>
