<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main page</title>
</head>
<body>

<hr>

<div style="text-align: center">
    <c:choose>
        <c:when test="${userLocale.language == 'ru'}">
            <p>Система тестирования</p>
            <p>Андрей Скорина</p>
        </c:when>
        <c:otherwise>
            <p>Testing system</p>
            <p>Andrew Skorina</p>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>