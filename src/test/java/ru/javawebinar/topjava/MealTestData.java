package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {

    public static final int[] USER_MEAL_ID_LIST = {100_002, 100_003, 100_004, 100_005, 100_006, 100_007, 100_008};
    public static final int[] ADMIN_MEAL_ID_LIST = {100_009, 100_010};

    public static final Meal[] USER_MEALS = {
            new Meal(USER_MEAL_ID_LIST[0], LocalDateTime.of(2021, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(USER_MEAL_ID_LIST[1], LocalDateTime.of(2021, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(USER_MEAL_ID_LIST[2], LocalDateTime.of(2021, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(USER_MEAL_ID_LIST[3], LocalDateTime.of(2021, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(USER_MEAL_ID_LIST[4], LocalDateTime.of(2021, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(USER_MEAL_ID_LIST[5], LocalDateTime.of(2021, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(USER_MEAL_ID_LIST[6], LocalDateTime.of(2021, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    };

    public static final Meal[] ADMIN_MEALS = {
            new Meal(ADMIN_MEAL_ID_LIST[0], LocalDateTime.of(2021, Month.FEBRUARY, 10, 10, 0), "Завтрак Админа", 500),
            new Meal(ADMIN_MEAL_ID_LIST[1], LocalDateTime.of(2021, Month.FEBRUARY, 10, 20, 0), "Ужин Админа", 1000),
    };

    public static final Meal DUPLICATE_MEAL = new Meal(null, LocalDateTime.of(2021, Month.JANUARY, 30, 10, 0), "Duplicate", 666);


    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2021, Month.MAY, 22, 11, 0), "new_meal", 500);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(USER_MEALS[0]);
        updated.setId(USER_MEALS[0].getId());
        updated.setDateTime(LocalDateTime.of(2019, Month.JANUARY, 30, 10, 0));
        updated.setCalories(666);
        updated.setDescription("updated");
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}