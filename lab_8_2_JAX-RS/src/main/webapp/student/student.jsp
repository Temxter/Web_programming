<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>

<jsp:include page="/add/header.jsp"/>
<c:choose>
    <c:when test="${userLocale.language == 'ru'}">
        <h2 align="center">Меню студента</h2>
        <h3 align="center">Тесты</h3>
        <ol>
            <c:forEach items="${unfilledMarks}" var="mark">
                <li>
                    <form action="${pageContext.request.contextPath}/singleServlet" method="post">
                        <input type="hidden" name="type" value="test">
                        <input type="hidden" name="testId" value="${mark.testId}">
                        <p>${mark.testName} (${mark.dateWithoutTime}) <input type="submit" value="Старт"></p>
                    </form>
                </li>
            </c:forEach>
        </ol>

        <h3 align="center">Отметки</h3>

        <table border=1 width="100%">
            <tr>
                <th>Имя теста</th>
                <th>Дата</th>
                <th>Отметка</th>
            </tr>
            <c:forEach items="${filledMarks}" var="mark">
                <tr>
                    <td>${mark.testName}</td>
                    <td>${mark.dateWithoutTime}</td>
                    <td>${mark.mark}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <h2 align="center">Student Menu</h2>
        <h3 align="center">Tests</h3>
        <ol>
            <c:forEach items="${unfilledMarks}" var="mark">
                <li>
                    <form action="${pageContext.request.contextPath}/singleServlet" method="post">
                        <input type="hidden" name="type" value="test">
                        <input type="hidden" name="testId" value="${mark.testId}">
                        <p>${mark.testName} (${mark.dateWithoutTime}) <input type="submit" value="Start"></p>
                    </form>
                </li>
            </c:forEach>
        </ol>

        <h3 align="center">Marks</h3>

        <table border=1 width="100%">
            <tr>
                <th>Test name</th>
                <th>Date</th>
                <th>Mark</th>
            </tr>
            <c:forEach items="${filledMarks}" var="mark">
                <tr>
                    <td>${mark.testName}</td>
                    <td>${mark.dateWithoutTime}</td>
                    <td>${mark.mark}</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>


<jsp:include page="/add/footer.jsp"/>

</body>
</html>