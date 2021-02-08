package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    List<Meal> getAll();
    Meal getById(int id);
    void add(Meal meal);
    void delete(int id);
    void update(Meal meal);
}
