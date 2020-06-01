package com.tencent.logindemo.util;

/**
 * 工具类
 */
public class Utils {

    /**
     * 生成token
     * TODO：这里简单处理用token#name#加上当前时间戳
     */
    public static final String generateToken(String name) {
        return "token#" + name + "#" + System.nanoTime();
    }

    /**
     * 生成salt
     * TODO：这里简单处理用当前时间戳表示
     */
    public static final String generateSalt() {
        return String.valueOf(System.nanoTime());
    }


}
