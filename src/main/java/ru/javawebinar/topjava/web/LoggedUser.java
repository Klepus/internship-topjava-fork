package ru.javawebinar.topjava.web;

public class LoggedUser {
    private static int id;


    public static int getId() {
        return id;
    }

    public static void setId(int userId) {
        id = userId;
    }
}
