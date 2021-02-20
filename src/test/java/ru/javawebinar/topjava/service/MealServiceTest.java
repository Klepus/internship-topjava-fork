package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void create() {
        Meal created = service.create(getNew(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test
    public void duplicateDateCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(DUPLICATE_MEAL, USER_ID));
    }

    @Test
    public void delete() {
        service.delete(USER_MEAL_ID_LIST[0], USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(USER_MEAL_ID_LIST[0],USER_ID));
    }

    @Test
    public void deleteForAnotherUser() {
        assertThrows(NotFoundException.class, () -> service.delete(USER_MEAL_ID_LIST[0], ADMIN_ID));
    }

    @Test
    public void get() {
        Meal meal = service.get(USER_MEAL_ID_LIST[0], USER_ID);
        assertMatch(USER_MEALS[0], meal);
    }

    @Test
    public void getForAnotherUser() {
        assertThrows(NotFoundException.class, () -> service.get(USER_MEAL_ID_LIST[0], ADMIN_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> all = service.getBetweenInclusive(null,null,USER_ID);
        assertMatch(all, Arrays.asList(USER_MEALS));

        List<Meal> mealsBeforeMidnight = service.getBetweenInclusive(null, LocalDate.of(2021, Month.JANUARY, 30),USER_ID);
        assertMatch(mealsBeforeMidnight, Arrays.asList(USER_MEALS).subList(0,4));

        List<Meal> mealsAfterMidnight = service.getBetweenInclusive(LocalDate.of(2021, Month.JANUARY, 31), null,USER_ID);
        assertMatch(mealsAfterMidnight, Arrays.asList(USER_MEALS).subList(3,7));
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(USER_MEAL_ID_LIST[0], USER_ID), getUpdated());
    }

    @Test
    public void updateForAnotherUser() {
        Meal updated = getUpdated();
        assertThrows(NotFoundException.class, () -> service.update(updated, ADMIN_ID));
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, Arrays.asList(USER_MEALS));
    }
}