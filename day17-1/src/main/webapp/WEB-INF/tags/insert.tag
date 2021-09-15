<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="mid" %>
<%@ attribute name="rmid" %>



	<c:if test="${mem ne null}">
		<a href="control.jsp?action=main&rmid=${mid}&mcnt=${mcnt}">댓글 등록</a>
		<!-- requset.setAttribute("rmid" , m.mid) -->
	</c:if>
	
	<c:if test="${mid eq rmid}">
		<form action="control.jsp" method="post" name="form0">
			<input type="hidden" name="action" value="rinsert"> <input
				type="hidden" name="mid" value="${mid}">
				 <input
				type="hidden" name="mcnt" value="${mcnt}">
			<!--VO.setMid = requset.setAttribute("mid" , m.mid) -->
			<table>
				<tr>
					<td><textarea name="rmsg" cols="30" rows="1" required></textarea></td>
					<td><input type="submit" value="등록">
				</tr>

			</table>
		</form>
	</c:if>


