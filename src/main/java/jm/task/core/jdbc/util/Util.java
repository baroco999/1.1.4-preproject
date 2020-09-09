package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mybase";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static Connection getConnection() throws SQLException {
        return  DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    private static SessionFactory buildSessionFactory() {
        Configuration configuration = null;
        StandardServiceRegistryBuilder builder = null;
        try {
            configuration = new Configuration().configure(); //читает hibernate.cfg.xml
            configuration.addAnnotatedClass(User.class);// читает метаданные из аннотаций в этом классе
            builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            //return new Configuration().configure().buildSessionFactory();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return configuration.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
