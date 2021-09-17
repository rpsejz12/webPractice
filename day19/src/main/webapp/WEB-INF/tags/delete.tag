<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="id" %>
<%@ attribute name="mid" %>
<%@ attribute name="rid" %>
<%@ attribute name="type" %>



<c:choose>

<c:when test="${type=='mdel'}">

<c:if test="${id eq mem.id}">
	<a href="control.jsp?action=mdelete&mid=${mid}&mcnt=${mcnt}">&nbsp;&nbsp;게시글
		삭제</a>
</c:if>
</c:when>


<c:when test="${type=='rdel'}">
<c:if test="${id eq mem.id}">
	<a href="control.jsp?action=rdelete&rid=${rid}&mcnt=${mcnt}&mid=${mid}">&nbsp;&nbsp;삭제</a>
</c:if>
</c:when>
</c:choose>