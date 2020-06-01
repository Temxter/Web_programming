<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tests</title>
</head>
<body>
<ol>
<c:forEach items="${tests}" var="test">
<li>${test.name}</li>
</c:forEach>
</ol>

</body>
</html>
