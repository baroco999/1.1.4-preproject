package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        UserService srv = new UserServiceImpl();
        srv.createUsersTable();

        User[] users = new User[] {new User("Михаил", "Иванов", (byte) 27),
                new User("Дмитрий", "Морозов", (byte) 30),
                new User("Елена", "Кузнецова", (byte) 19),
                new User("Наталья", "Петрова", (byte) 24)
        };

        for (User u : users) {
            srv.saveUser(u.getName(), u.getLastName(), u.getAge());
        }

        System.out.println(srv.getAllUsers().toString());
        srv.cleanUsersTable();
        srv.dropUsersTable();

        Util.closeSessionFactory();
    }
}
