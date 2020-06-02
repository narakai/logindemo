package com.tencent.logindemo.database;

import com.tencent.logindemo.HelloWorldServer;

import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 数据库操作类
 * <p>
 * TODO: 1、数据库中有两个表user和token表，暂时没有考虑建立索引来提高查询效率
 */
public class DataBaseHelper {

    private static final Logger logger = Logger.getLogger(HelloWorldServer.class.getName());

    private static final int PORT = 3306;
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String DATABASE = "logindemo";
    private static final String DB_URL = String.format("jdbc:mysql://localhost:%s/%s?useUnicode=true&characterEncoding=UTF-8", PORT, DATABASE);

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
            boolean result = rs.next();
            rs.close();
            return result;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "isUserExist fail", ex);
        }
        return false;
    }

    /**
     * 判断指定名称用户的token是否存在
     */
    public boolean isTokenExistByUserName(String name) {
        try {
            String sql = "SELECT * FROM token WHERE userName='" + name + "'";
            logger.info("isTokenExistByUserName executeSql: " + sql);
            ResultSet rs = statement.executeQuery(sql);
            boolean result = rs.next();
            rs.close();
            return result;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "isTokenExistByUserName fail", ex);
        }
        return false;
    }

    /**
     * 判断指定的token是否存在
     */
    public boolean isTokenExistByToken(String token) {
        try {
            String sql = "SELECT * FROM token WHERE token='" + token + "'";
            logger.info("isTokenExistByToken executeSql: " + sql);
            ResultSet rs = statement.executeQuery(sql);
            boolean result = rs.next();
            rs.close();
            return result;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "isTokenExistByToken fail", ex);
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
                boolean result = rs2.next();
                rs2.close();
                rs.close();
                return result;
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

    /**
     * 使token失效
     */
    public boolean invalidToken(String token) {
        try {
            String sql = "UPDATE token SET status=0 WHERE token='" + token + "'";
            logger.info("invalidToken executeSql: " + sql);
            return statement.executeUpdate(sql) >= 1;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "invalidToken fail", ex);
        }
        return false;
    }

    /**
     * 判断token是否有效，如果status=0或者device不一致都说明token失效了
     */
    public boolean isTokenValid(String token, String device) {
        try {
            String sql = "SELECT * FROM token WHERE token='" + token + "'";
            logger.info("isTokenValid executeSql: " + sql);
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                int status = rs.getInt("status");
                if (status == 0) {
                    logger.info("isTokenValid status invalid");
                    return false;
                }

                String savedDevice = rs.getString("device");
                if (null == savedDevice || !savedDevice.equalsIgnoreCase(device)) {
                    logger.info("isTokenValid device incorrect");
                    return false;
                }
                return true;
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "isTokenValid fail", ex);
        }
        return false;
    }

}