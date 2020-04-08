<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
<h3 align="center">Menu</h3>
<h4 align="center">Tests</h4>
<ol>
    <c:forEach items="{unfilledMarks}" var="mark">
        <li><a href="test?id=${mark.test.id}">${mark.test.name} (${mark.date})</a></li>
    </c:forEach>
</ol>

<h4 align="center">Marks</h4>

<table>
    <tr>
        <th>Test name</th>
        <th>Date</th>
        <th>Mark</th>
    </tr>
    <ol>
    <c:forEach items="{filledMarks}" var="mark">
    <tr>
            <td><li>mark.testName</li></td>
            <td>mark.date</td>
            <td>mark.mark</td>
    </tr>
    </c:forEach>
    </ol>

</table>

</body>
</html>