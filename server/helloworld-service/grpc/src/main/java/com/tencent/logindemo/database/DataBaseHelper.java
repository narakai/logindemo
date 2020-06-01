package com.tencent.logindemo.database;

import com.tencent.logindemo.HelloWorldServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 数据库操作类
 */
public class DataBaseHelper {

    private static final Logger logger = Logger.getLogger(HelloWorldServer.class.getName());
    private final String DB_URL = "jdbc:mysql://localhost:3306/logindemo?useUnicode=true&characterEncoding=UTF-8";

    private static final String USER = "root";
    private static final String PASS = "root";

    private Connection connection = null;
    private Statement statement = null;

    private DataBaseHelper() {
    }

    private static class DataBaseHelperHolder {
        static DataBaseHelper instance = new DataBaseHelper();
    }

    public static DataBaseHelper getInstance() {
        return DataBaseHelperHolder.instance;
    }

    public void open() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "open fail", ex);
        }
    }

    public void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "close fail", ex);
        }
    }

    public ResultSet executeSql(String sql) {
        try {
            logger.info("executeSql: " + sql);
            return statement.executeQuery(sql);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "executeSql fail", ex);
        }
        return null;
    }

    public boolean isUserExist(String name) {
        try {
            String sql = "SELECT * FROM user WHERE name=" + name;
            ResultSet rs = executeSql(sql);
            return rs.next();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "isUserExist fail", ex);
        }
        return false;
    }

    public boolean addNewUser(String name, String password, String device) {
        try {
            String sql = "INSERT user(name, password, salt) VALUES ('"+ name+"', '"+password+"', '"+device+"')";
            ResultSet rs = executeSql(sql);
            return rs.next();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "addNewUser fail", ex);
        }
        return false;
    }

    public void test() {
        try {
            String sql;
            sql = "SELECT * FROM table_name";
            ResultSet rs = statement.executeQuery(sql); // DML
            // stmt.executeUpdate(sql); // DDL

            while (rs.next()) {
                System.out.print(rs.getString(1));
                System.out.print(rs.getString(2));
            }
            rs.close();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}