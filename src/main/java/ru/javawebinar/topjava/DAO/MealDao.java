package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    List<Meal> getAll();
    void add(Meal meal);
    void delete(Meal meal);
    void update(Meal meal);
    Meal getById(int id);
}
