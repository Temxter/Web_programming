<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main page</title>
    <style type="text/css">
        .logout-form {
            display:inline;
            margin:0px;
            padding:0px;
        }
        .logout-form input {
            background-color: transparent;
            text-decoration: underline;
            border: none;
            color: blue;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div style="text-align: center">
    <c:choose>
        <c:when test="${userLocale.language == 'ru'}">
            <h2>Система тестирования</h2>
            <a href="${pageContext.request.contextPath}/">Информация</a> |
            <a href="${pageContext.request.contextPath}/">Главная</a> |
            <form class="logout-form" id="login-form" action="${pageContext.request.contextPath}/singleServlet" method="post">
                <input type="hidden" name="type" value="logout">
                <input type="submit" value="Выход"/>
            </form>
        </c:when>
        <c:otherwise>
            <h2>Testing system</h2>
            <a href="${pageContext.request.contextPath}/">Info</a> |
            <a href="${pageContext.request.contextPath}/">Home</a> |
            <form class="logout-form" id="login-form" action="${pageContext.request.contextPath}/singleServlet" method="post">
                <input type="hidden" name="type" value="logout">
                <input type="submit" value="Logout"/>
            </form>
        </c:otherwise>
    </c:choose>

    <form>
        <select name="lang" onchange="this.form.submit()">
            <c:choose>
                <c:when test="${userLocale.language == 'ru'}">
                    <option value='ru' selected>Русский</option>
                    <option value='en'>English</option>
                </c:when>
                <c:otherwise>
                    <option value='ru'>Русский</option>
                    <option value='en' selected>English</option>
                </c:otherwise>
            </c:choose>
        </select>
    </form>
    <p align="right">
        <c:choose>
            <c:when test="${userLocale.language == 'ru'}">
            Привет, ${user != null ? user.getLogin() : "Гость"}!
            </c:when>
            <c:otherwise>
            Hello, ${user != null ? user.getLogin() : "Guest"}!
            </c:otherwise>
        </c:choose>
    </p>
    ${message}
</div>
</body>
</html>