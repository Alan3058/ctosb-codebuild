package com.ctosb.codebuild.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {

    /**
     * 获取数据库连接
     * 
     * @param jdbcDriver
     * @param jdbcUrl
     * @param jdbcUserName
     * @param jdbcPassword
     * @return
     * @author Alan
     * @time 2015-11-20 下午11:04:24
     */
    public static Connection getConnection(String jdbcDriver, String jdbcUrl, String jdbcUserName, String jdbcPassword) {
        try {
            // 加载驱动，这一句也可写为：Class.forName("com.mysql.jdbc.Driver");
            Class.forName(jdbcDriver).newInstance();
            // 建立到MySQL的连接
            DriverManager.setLoginTimeout(1);
            return DriverManager.getConnection(jdbcUrl, jdbcUserName, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验数据库是否连接
     * 
     * @param jdbcDriver
     * @param jdbcUrl
     * @param jdbcUserName
     * @param jdbcPassword
     * @return
     * @author Alan
     * @time 2015-11-20 下午11:07:18
     */
    public static boolean isConnect(String jdbcDriver, String jdbcUrl, String jdbcUserName, String jdbcPassword) {
        Connection connection = getConnection(jdbcDriver, jdbcUrl, jdbcUserName, jdbcPassword);
        if (connection == null) {
            return false;
        }
        return true;
    }

}
