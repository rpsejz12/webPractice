<%@ tag language="java" pageEncoding="UTF-8"%>

<%
if (session.getAttribute("mem") == null) {
%>
<div class="content">
	<%
	} else {
	%>
	<div class="content" style="display: none">
		<%
		}
		%>