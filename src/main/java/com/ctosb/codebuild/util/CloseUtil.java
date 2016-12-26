package com.ctosb.codebuild.util;

import java.io.Closeable;
import java.sql.Connection;

public class CloseUtil {

    /**
     * 关闭流
     * 
     * @param closeable
     * @author Alan
     * @time 2015-11-20 下午10:50:09
     */
    public static void close(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     * 
     * @param connection
     * @author Alan
     * @time 2015-11-20 下午10:50:09
     */
    public static void close(Connection connection) {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
