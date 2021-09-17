<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="mid" %>
<%@ attribute name="favcount" %>



<c:choose>


<c:when test="${mem eq null}">
	[♥ ${favcount}
</c:when>


<c:when test="${mem ne null}">
	<a href="control.jsp?action=fcnt&mid=${mid}&mcnt=${mcnt}">[♥ ${favcount}</a>
</c:when>
</c:choose>