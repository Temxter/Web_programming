<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main page</title>
</head>
<body>
<%--For first execute: insert data from file "insert_data.sql"--%>

<c:choose>
    <c:when test="${userLocale.language == 'ru'}">
        <h1 align="center">Главная страница</h1>
    </c:when>
    <c:otherwise>
        <h1 align="center">Main page</h1>
    </c:otherwise>
</c:choose>
<jsp:include page="/add/header.jsp" flush="true"/>
<c:choose>
    <c:when test="${userLocale.language == 'ru'}">
        <div style="text-align: center">
            <p>Это система для тестирования студентов.</p>
            <p>В системе есть два типа пользователей: студенты и учителя.
                Учителя могут создавать новые тесты и назначать тесты студентам.
                Студенты могут проходить тесты и видеть свои результаты.</p>
            <p>Вы можете зарегистрироваться только как студент.</p>
            <p>Если Вы хотите посмотреть, как выглядит функционал учителя, то Вы можете войти, как учитель, под логином "Teacher и
                паролем "test".</p>
            <a href="${pageContext.request.contextPath}/auth/login.jsp">Войти</a>
            <a href="${pageContext.request.contextPath}/auth/register.jsp">Регистрация</a>
            <b><a href="${pageContext.request.contextPath}/chat.html">Чат</a></b>
        </div>
    </c:when>
    <c:otherwise>
        <div style="text-align: center">
            <p>This is a system for testing students.</p>
            <p>System include two type of users: student and teachers.
                Teacher can creates new test and assign test to student.
                Student can pass the test and check result.</p>
            <p>You can only register as a student.</p>
            <p>If you want to see how look like teacher functional, you can log in using the login "Teacher" and the password
                "test".</p>

            <a href="${pageContext.request.contextPath}/auth/login.jsp">Login</a>
            <a href="${pageContext.request.contextPath}/auth/register.jsp">Register</a>
            <b><a href="${pageContext.request.contextPath}/chat.html">Chat</a></b>
        </div>
    </c:otherwise>
</c:choose>

<jsp:include page="/add/footer.jsp" flush="true"/>
</body>
</html>