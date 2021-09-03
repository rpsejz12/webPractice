<%@ tag language="java" pageEncoding="UTF-8"%>


<%
if (session.getAttribute("mem") != null) {
%>
<input type="button" value="로그아웃" onClick="logout()">
<%
}
%>
