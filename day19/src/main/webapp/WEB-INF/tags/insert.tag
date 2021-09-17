<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="mid" %>
<%@ attribute name="rmid" %>
<%@ attribute name="msgmid" %>



	<c:if test="${mem ne null}">
		<a href="main.do?rmid=${mid}&mcnt=${mcnt}">댓글 등록</a>
	</c:if>
	
	<c:if test="${mid eq rmid}">
		<form action="rinsert.do" method="post" name="rinsert">
			<input	type="hidden" name="msgmid" value="${msgmid}">
			<input	type="hidden" name="mid" value="${mid}">
			<input	type="hidden" name="mcnt" value="${mcnt}">
			<table>
				<tr>
					<td><textarea name="rmsg" cols="30" rows="1" required></textarea></td>
					<td><input type="submit" value="등록">
				</tr>
			</table>
		</form>
	</c:if>


