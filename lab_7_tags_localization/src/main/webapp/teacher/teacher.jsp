<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/mytaglib.tld" prefix="mytag" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style type="text/css">
       table, th, td {
           width:100%;
           border:black solid 1px;
       }
    </style>
</head>
<body>


<jsp:include page="/add/header.jsp"/>
<c:choose>
    <c:when test="${userLocale.language == 'ru'}">
        <div align="center">

            <h2>Меню учителя</h2>

            <h3>Назначить тест студенту</h3>
            <form action="${pageContext.request.contextPath}/singleServlet" method="post">
                <input type="hidden" name="type" value="assignTest">
                <select name="student">
                    <c:forEach var="student" items="${students}">
                        <option value="${student.id}">${student.name}</option>
                    </c:forEach>
                </select>

                <select name="test">
                    <c:forEach var="test" items="${tests}">
                        <option value="${test.id}">${test.name}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Назначить">
            </form>

            <h3>Все отметки студентов:</h3>
            <table width="100%" border="1px">
                <tr>
                    <th>Студент</th>
                    <th>Тест</th>
                    <th>Правильные ответы</th>
                    <th>Всего вопросов</th>
                    <th>Дата</th>
                </tr>
                <c:forEach var="mark" items="${marks}">
                    <tr>
                        <td>${mark.studentName}</td>
                        <td>${mark.testName}</td>
                        <td>${mark.passed == true ? mark.rightAnswers : "Назначен"}</td>
                        <td>${mark.questionsSize}</td>
                        <td>${mark.dateWithoutTime}</td>
                    </tr>
                </c:forEach>
            </table>

        </div>
    </c:when>
    <c:otherwise>
        <div align="center">

            <h2>Teacher menu</h2>

            <h3>Assign a test to a student:</h3>
            <form action="${pageContext.request.contextPath}/singleServlet" method="post">
                <input type="hidden" name="type" value="assignTest">
                <select name="student">
                    <c:forEach var="student" items="${students}">
                        <option value="${student.id}">${student.name}</option>
                    </c:forEach>
                </select>

                <select name="test">
                    <c:forEach var="test" items="${tests}">
                        <option value="${test.id}">${test.name}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Assign">
            </form>

            <h3>All marks of students:</h3>
            <table width="100%" border="1px">
                <tr>
                    <th>Student</th>
                    <th>Test</th>
                    <th>Right Answers</th>
                    <th>Total Questions</th>
                    <th>Date</th>
                </tr>
                <c:forEach var="mark" items="${marks}">
                    <tr>
                        <td>${mark.studentName}</td>
                        <td>${mark.testName}</td>
                        <td>${mark.passed == true ? mark.rightAnswers : "Assigned"}</td>
                        <td>${mark.questionsSize}</td>
                        <td>${mark.dateWithoutTime}</td>
                    </tr>
                </c:forEach>
            </table>

        </div>
    </c:otherwise>
</c:choose>

<mytag:getinfo/>


<jsp:include page="/add/footer.jsp"/>

</body>
</html>