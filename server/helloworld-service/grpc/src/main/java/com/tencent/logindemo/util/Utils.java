package com.tencent.logindemo.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 工具类
 */
public class Utils {

    private static final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 生成token
     * TODO：这里简单处理用token#name#加上当前时间戳
     */
    public static String generateToken(String name) {
        return "token#" + name + "#" + System.nanoTime();
    }

    /**
     * 生成salt
     * TODO：这里简单处理，生成一个长度为16的随机字符串
     */
    public static String generateSalt() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < sb.capacity(); i++) {
            sb.append(hex[random.nextInt(16)]);
        }
        return sb.toString();
    }

    /**
     * 计算MD5
     */
    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(str.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
