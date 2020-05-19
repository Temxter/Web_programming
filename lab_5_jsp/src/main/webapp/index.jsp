<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main page</title>
</head>
<body>
<%--For first execute: insert data from file "insert_data.sql"--%>
<h1 align="center">Main page</h1>

<form action="${pageContext.request.contextPath}/singleServlet" method="post" style="text-align: left;">
    <input type="hidden" name="type" value="insertAllData">
    <input type="submit" value="Insert all data" style="background-color: green;">
</form>
<jsp:include page="/add/header.jsp" flush="true"/>
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
</div>
<jsp:include page="/add/footer.jsp" flush="true"/>
</body>
</html>