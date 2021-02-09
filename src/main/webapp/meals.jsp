<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://klepinin.com/dateTimeFunction" prefix="dtf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h3>Meals</h3>

<table border="1" cellspacing="0">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <tr style="color:${meal.isExcess() ? 'red' : 'green'}">
            <td>${dtf:getStringByLocalDateTime(meal.getDateTime())}</td>
            <td>${meal.getDescription()}</td>
            <td>${meal.getCalories()}</td>

            <td><a href="meals?action=edit&mealId=<c:out value="${meal.getId()}"/>">Update</a></td>
            <td><a href="meals?action=delete&mealId=<c:out value="${meal.getId()}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<button onclick="window.location.href = 'meals?action=insert'">Add meal</button>
</body>
</html>