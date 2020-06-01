<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<ol>
<c:forEach items="${users}" var="user">
<li>${user.login}</li>
</c:forEach>
</ol>

</body>
</html>
