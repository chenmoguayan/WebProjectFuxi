package org.qingqiao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/*
    Math
    全都是静态方法
 */
public class JDBCUtil {
    static String DRIVERNAME;
    static String USERNAME;
    static String PASSWORD;
    static String URL;
    static Connection conn = null;

    // 读取properties
    static {
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        DRIVERNAME = bundle.getString("driverName");
        USERNAME = bundle.getString("username");
        PASSWORD = bundle.getString("password");
        URL = bundle.getString("url");
    }

    // 获取连接
    public static Connection getConn() {
        try {
            Class.forName(DRIVERNAME);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 关流
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 关流
    public static void close(Connection conn, PreparedStatement ps) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
