<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Test</title>
</head>
<body>
<h3 align="center">Test ${testname}</h3>
<form id="test-form" method="post">
    <p> ${question.name} </p>
    <% int counter = 0; %>
    <div>
        <c:forEach items="${question.answers}" var="answer">
            <input type="radio" id="answer<%= counter++ %>" />
            <label for="answer<%= counter %>">${answer.answer}</label>
        </c:forEach>
    </div>
    <button type="submit">Send answer</button>
</form>


</body>
</html>