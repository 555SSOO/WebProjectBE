package rest.repository;

import org.sqlite.SQLiteConfig;
import rest.entities.CouponEntity;
import rest.entities.ShopEntity;
import rest.models.Coupon;

import java.sql.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Repository {

    private static final String DB_URL = "C:\\Users\\01\\Desktop\\RAF\\sem6\\web\\projekat\\rest-js\\server.db";

    private static Connection connection = null;
    private static Statement statement = null;

    private static AtomicBoolean isConnected = new AtomicBoolean(false);

    public static void initDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_URL, config.toProperties());
            isConnected.set(true);
            statement = connection.createStatement();
            initTables();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initTables() throws SQLException {

        String createUsersTable = "CREATE TABLE IF NOT EXISTS USERS (USER_ID INTEGER PRIMARY KEY NOT NULL, VERSION INT, FIRST_NAME CHAR(50), LAST_NAME CHAR(50), PRIVILEGE_LEVEL INT, USERNAME CHAR(50), PASSWORD CHAR(50))";
        statement.executeUpdate(createUsersTable);
        String createShopsTable = "CREATE TABLE IF NOT EXISTS SHOPS (SHOP_ID INTEGER PRIMARY KEY NOT NULL, VERSION INT, NAME CHAR(50));";
        statement.executeUpdate(createShopsTable);
        String createCouponsTable = "CREATE TABLE IF NOT EXISTS COUPONS (COUPON_ID INTEGER PRIMARY KEY NOT NULL, VERSION INT, NAME CHAR(50), DISCOUNTED_PRICE FLOAT, ORIGINAL_PRICE FLOAT, VALID_FROM DATE, VALID_TO DATE, SHOP INT, FOREIGN KEY (SHOP) REFERENCES SHOPS(SHOP_ID));";
        statement.executeUpdate(createCouponsTable);

    }

    public void endConnection() throws SQLException {
        connection.close();
        statement.close();
    }

    public static Connection getConnection() {
        return connection;
    }

    public static AtomicBoolean getIsConnected() {
        return isConnected;
    }
}
