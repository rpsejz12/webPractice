<%@ tag language="java" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="memberBean" class="model.MemberBean" scope="session" />
<jsp:setProperty property="*" name="memberBean" />


<%
if (session.getAttribute("mem") == null) {
%>
<input type="submit" value="로그인">
<%
} else {
%>
<input type="button" value="로그아웃" onClick="logout()">
<%
}
%>



<script type="text/javascript">
	function logout() {
		document.form1.action.value = "logout";
		document.form1.submit();
	}
</script>