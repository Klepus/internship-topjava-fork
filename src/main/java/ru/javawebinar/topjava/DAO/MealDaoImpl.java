package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MealDaoImpl implements MealDao {
    private static  MealDaoImpl instance;
    private List<Meal> meals;
    private static int MEALS_COUNT;

    {
        meals = new CopyOnWriteArrayList();
        meals.add(new Meal(++MEALS_COUNT, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(++MEALS_COUNT, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(++MEALS_COUNT, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(++MEALS_COUNT, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        meals.add(new Meal(++MEALS_COUNT, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(++MEALS_COUNT, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(++MEALS_COUNT, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    private MealDaoImpl() {
    }

    public static synchronized MealDaoImpl getInstance() {
        if (instance == null) {
            instance = new MealDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Meal> getAll() {
        return meals;
    }

    @Override
    public Meal getById(int id) {
        return meals.stream().filter(meal -> meal.getId() == id).findAny().orElse(null);
    }

    @Override
    public void add(Meal meal) {
        meal.setId(++MEALS_COUNT);
        meals.add(meal);
    }

    @Override
    public void delete(int id) {
        meals.removeIf(meal -> meal.getId() == id);
    }

    @Override
    public void update(Meal meal) {
        Meal current = getById(meal.getId());
        current.setCalories(meal.getCalories());
        current.setDescription(meal.getDescription());
        current.setDateTime(meal.getDateTime());
    }
}
