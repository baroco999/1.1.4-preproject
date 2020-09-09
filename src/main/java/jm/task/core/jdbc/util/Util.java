package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.Properties;

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
        Properties props = new Properties();

        props.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        props.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mybase");
        props.setProperty("hibernate.connection.username", "root");
        props.setProperty("hibernate.connection.password", "root");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.setProperty("hibernate.show_sql", "true");

        try {
            configuration = new Configuration().addProperties(props);
            configuration.addAnnotatedClass(User.class);
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
