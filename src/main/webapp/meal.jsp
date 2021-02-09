<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="ru">
<head>
    <title>New meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>

<h3>${action} meal</h3>

<form action="meals" method="POST">
    <p>Meal ID:
        <input type="text"
               readonly="readonly"
               name="mealId"
               value="${meal.getId()}" /></p>
    <p>Date and time:
        <input type="datetime-local"
               name="datetime"
               value="${meal.getDateTime()}"/></p>
    <p>Description:
        <input type="text"
               name="description"
               value="${meal.getDescription()}" /></p>
    <p>Calories:
        <input type="text"
               name="calories"
               value="${meal.getCalories()}"/></p>
    </p>
    <input type="submit" value="Save" />
    <button type="button"  onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>
