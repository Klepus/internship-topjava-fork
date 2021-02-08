<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <title>New meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h3><a href="meals?action=meals">Back to meals</a></h3>
<hr>
<h2>Add meal</h2>

<form action="meals" method="POST">
    <p>Meal ID:
        <input type="text"
               readonly="readonly"
               name="mealId"
               value="${meal.getId()}" /></p>
    <p>Date and time (30.01.2020 10:00:00):
        <input type="text"
               name="datetime"
               value="${meal.getFormattedDateTime()}" /></p>
    <p>Description:
        <input type="text"
               name="description"
               value="${meal.getDescription()}" /></p>
    <p>Calories:
        <input type="text"
               name="calories"
               value="${meal.getCalories()}"/></p>
    <input type="submit" value="Submit" />
</form>
</body>
</html>
