<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 18.05.2020
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit users</title>
</head>
<body>
<c:choose>
    <c:when test="${userLocale.language == 'ru'}">
        <h3>Редактирование пользователя</h3>
        <form method="post">
            <input type="hidden" name="type" value="editUser">
            <input id="id" name="id" type="hidden" value="${user.id}">
            <label for="name-change">Имя</label><br>
            <input id="name-change" name="name" value="${user.login}"><br><br>

<%--            <c:choose>--%>
<%--                <c:when test="${ user.type.isEqual(\"Student\") }"></c:when>--%>
<%--            </c:choose>--%>
            <label for="password-change">Пароль</label><br>
            <input id="password-change" type="password"><br><br>
            <label for="userType-change">Тип</label><br>
            <select id="userType-change" name="userType">
                <option value="Student">Студент</option>
                <option value="Teacher">Преподаватель</option>
            </select>
            <input type="submit" value="Изменить">
        </form>
    </c:when>
    <c:otherwise>
        <h3>Edit user</h3>
        <form method="post">
            <input type="hidden" name="type" value="editUser">
            <input id="id" name="id" type="hidden" value="${user.id}">
            <label for="name">Name</label><br>
            <input id="name" name="name" value="${user.login}"><br><br>

                <%--            <c:choose>--%>
                <%--                <c:when test="${ user.type.isEqual(\"Student\") }"></c:when>--%>
                <%--            </c:choose>--%>
            <label for="password">Password</label><br>
            <input id="password" type="password"><br><br>
            <label for="userType">Type</label><br>
            <select id="userType" name="userType">
                <option value="Student">Student</option>
                <option value="Teacher">Teacher</option>
            </select>
            <input type="submit" value="Submit">
        </form>
    </c:otherwise>
</c:choose>

</body>
</html>
