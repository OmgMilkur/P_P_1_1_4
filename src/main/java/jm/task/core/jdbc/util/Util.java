package jm.task.core.jdbc.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final Properties properties = new Properties();


    public static Connection getConnection() {
        Connection connection = null;

        try (InputStream input = new FileInputStream("src/main/resources/sql.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(
                    properties.getProperty("database.url"),
                    properties.getProperty("database.login"),
                    properties.getProperty("database.pass"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
