<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div style="text-align: center">
    <c:choose>
    <c:when test="${userLocale.language == 'ru'}">
        <p>Извините, Ваша страница не найдена!</p>
        <a href="${pageContext.request.contextPath}/index.jsp" align="center">Вернуться на главную страницу</a>
    </c:when>
    <c:otherwise>
        <p>Sorry, your request not founded!</p>
        <a href="${pageContext.request.contextPath}/index.jsp" align="center">Return to main page</a>
    </c:otherwise>
</c:choose>

</div>
</body>
</html>