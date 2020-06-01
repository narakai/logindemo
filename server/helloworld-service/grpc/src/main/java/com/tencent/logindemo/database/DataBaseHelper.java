package com.tencent.logindemo.database;

import com.tencent.logindemo.HelloWorldServer;

import java.sql.*;
import java.util.Random;
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

    /**
     * 判断指定名称的用户是否存在
     */
    public boolean isUserExist(String name) {
        try {
            String sql = "SELECT * FROM user WHERE name='" + name + "'";
            logger.info("isUserExist executeSql: " + sql);
            ResultSet rs = statement.executeQuery(sql);
            return rs.next();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "isUserExist fail", ex);
        }
        return false;
    }

    /**
     * 判断指定名称用户的token是否存在
     */
    public boolean isTokenExist(String name) {
        try {
            String sql = "SELECT * FROM token WHERE userName='" + name + "'";
            logger.info("isTokenExist executeSql: " + sql);
            ResultSet rs = statement.executeQuery(sql);
            return rs.next();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "isTokenExist fail", ex);
        }
        return false;
    }

    /**
     * TODO：暂不考虑这里多次SQL操作存在可以合并的优化手段
     */
    public boolean isPasswordCorrect(String name, String password) {
        try {
            String sql = "SELECT * FROM user WHERE name='" + name + "'";
            logger.info("isPasswordCorrect executeSql: " + sql);
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                String salt = rs.getString("salt");//TODO: 利用salt对password进行加密并进行对比
                String sql2 = "SELECT name FROM user WHERE name='" + name + "' and password='" + password + "'";
                logger.info("isPasswordCorrect executeSql: " + sql);
                ResultSet rs2 = statement.executeQuery(sql2);
                return rs2.next();
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "isPasswordCorrect fail", ex);
        }
        return false;
    }

    /**
     * 新增用户
     */
    public boolean addNewUser(String name, String password, String salt) {
        try {
            String sql = "INSERT INTO user(name, password, salt) VALUES ('" + name + "', '" + password + "', '" + salt + "')";
            logger.info("addNewUser executeSql: " + sql);
            return statement.executeUpdate(sql) >= 1;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "addNewUser fail", ex);
        }
        return false;
    }

    /**
     * 新增token
     */
    public boolean addNewToken(String name, String device, String token) {
        try {
            String sql = "INSERT INTO token(userName, device, token) VALUES ('" + name + "', '" + device + "', '" + token + "')";
            logger.info("addNewToken executeSql: " + sql);
            return statement.executeUpdate(sql) >= 1;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "addNewToken fail", ex);
        }
        return false;
    }

    /**
     * 更新token
     */
    public boolean updateToken(String name, String device, String token, int status) {
        try {
            String sql = "UPDATE token SET userName='" + name + "', device='" + device + "', token='" + token + "', status=" + status + " WHERE userName='" + name + "'";
            logger.info("updateToken executeSql: " + sql);
            return statement.executeUpdate(sql) >= 1;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "updateToken fail", ex);
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