<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"%>
<%@ attribute name = "color"%>
<%@ attribute name = "size"%>

<p><font size = "${size}" color ="${color}">
<jsp:doBody/>
</font></p>
