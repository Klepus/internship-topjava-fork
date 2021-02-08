package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.DAO.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet("/meals")
public class MealServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final MealDaoImpl dao = MealDaoImpl.getInstance();
    private static final Logger log = getLogger(MealServlet.class);
    private static String ADD_OR_UPDATE = "/meal.jsp";
    private static String MEAL_LIST = "/meals.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward;
        String action = request.getParameter("action");

        if (action.isEmpty()) {
            log.debug("redirect to meals");
            forward = MEAL_LIST;
        } else if (action.equalsIgnoreCase("delete")) {
            log.debug("delete meal");
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            dao.delete(mealId);
            forward = MEAL_LIST;
            request.setAttribute("meals", MealsUtil.filteredByStreams(dao.getAll(), LocalTime.of(0, 0), LocalTime.of(23, 0), 2000));
        } else if (action.equalsIgnoreCase("edit")) {
            log.debug("redirect to edit");
            forward = ADD_OR_UPDATE;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = dao.getById(mealId);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("meals")) {
            log.debug("redirect to meals");
            forward = MEAL_LIST;
            request.setAttribute("meals", MealsUtil.filteredByStreams(dao.getAll(), LocalTime.of(0, 0), LocalTime.of(23, 0), 2000));
        } else {
            log.debug("redirect to add meal");
            forward = ADD_OR_UPDATE;
        }

        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal();
        meal.setDescription(request.getParameter("description"));
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
        meal.setDateTimeByString(request.getParameter("datetime"));
        String mealId = request.getParameter("mealId");
        if (mealId == null || mealId.isEmpty()) {
            dao.add(meal);
            log.debug("meal added");
        } else {
            meal.setId(Integer.parseInt(mealId));
            dao.update(meal);
            log.debug("meal updated");
        }
        RequestDispatcher view = request.getRequestDispatcher(MEAL_LIST);
        request.setAttribute("meals", MealsUtil.filteredByStreams(dao.getAll(), LocalTime.of(0, 0), LocalTime.of(23, 0), 2000));
        view.forward(request, response);
    }
}
