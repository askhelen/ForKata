package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        User thor = new User("Thor", "Odinson", (byte) 42);
        User peter = new User("Peter", "Parker", (byte) 21);
        User loki = new User("Loki", "Odinson", (byte) 41);
        User iron = new User("Iron", "Man", (byte) 43);

        userService.saveUser(thor.getName(), thor.getLastName(), thor.getAge());
        System.out.println("User с именем " + thor.getName() + " добавлен в базу данных");

        userService.saveUser(peter.getName(), peter.getLastName(), peter.getAge());
        System.out.println("User с именем " + peter.getName() + " добавлен в базу данных");

        userService.saveUser(loki.getName(), loki.getLastName(), loki.getAge());
        System.out.println("User с именем " + loki.getName() + " добавлен в базу данных");

        userService.saveUser(iron.getName(), iron.getLastName(), iron.getAge());
        System.out.println("User с именем " + iron.getName() + " добавлен в базу данных");
        System.out.println();
        for (User s : userService.getAllUsers()) {
            System.out.println(s);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}