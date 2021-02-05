<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h4>Meals</h4>

<table border="">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Excess</th>
        <th></th>
        <th></th>
    </tr>

    <c:forEach items="${mealsTo}" var="meals">
        <tr>
            <td>${meals.getDateTime()}</td>
            <td>${meals.getDescription()}</td>
            <td>${meals.getCalories()}</td>
            <td>${meals.isExcess()}</td>
            <td><h><a href="meals">Update</a></h></td>
            <td><h><a href="meals">Delete</a></h></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>