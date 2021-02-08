package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

public class MealDaoImpl implements MealDao {
    private List<Meal> meals = MealsUtil.createMeals();

    @Override
    public List<Meal> getAll() {
        return meals;
    }

    @Override
    public void add(Meal meal) {
        meals.add(meal);
    }

    @Override
    public void delete(Meal meal) {
    }

    @Override
    public void update(Meal meal) {
    }

    @Override
    public Meal getById(int id) {
        return null;
    }
}
