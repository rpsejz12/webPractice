<%@ tag language="java" pageEncoding="UTF-8"%>

<%
if (session.getAttribute("mem") == null) {
%>
<a href="./loginForm.jsp"><span class="icon_profile"></span></a>
<%
}else{
%>
<a href="./control.jsp?action=logout"><span class="icon_profile"></span></a>
<%
}
%>
