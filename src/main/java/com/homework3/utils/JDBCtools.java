package com.homework3.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * jdbc 工具类
 */
public class JDBCtools {
    private static String url = null;           //数据库连接地址
    private static String username = null;      //数据库用户名
    private static String password = null;      //数据库登录密码
    private static String driver = null;        //数据库驱动
    private static String stz = null;           //设置时区

    static {
        //初始化配置文件操作对象
        Properties prop = new Properties();
        //将数据库连接配置文件读取到输入流
        InputStream in = JDBCtools.class.getResourceAsStream("/dbconf.prop");

        try {
            //从输入流中读取配置文件内容
            prop.load(in);
            //读取url
            url = prop.getProperty("url");
            //读取用户名
            username = prop.getProperty("username");
            //读取密码
            password = prop.getProperty("password");
            //读取 jdbc 驱动
            driver = prop.getProperty("driver");
            //读取时区
            stz = prop.getProperty("serverTZ");
            //注册 jdbc 驱动
            Class.forName(driver);
        } catch (IOException ioe) {
            System.out.println("????");
            //打印 IO 异常
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("????");
            //打印 找不到类 异常
            cnfe.printStackTrace();
        }
    }

    /**
     * 打开数据库连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConn() throws SQLException {
        //创建数据库连接
        Connection connection = DriverManager.getConnection(url, username, password);
        //返回数据库连接
        return connection;
    }

    /**
     * 关闭数据库连接
     *
     * @param para_st
     * @param para_conn
     */
    public static void closeConn(Statement para_st, Connection para_conn) {
        if (para_st != null) {
            try {
                //关闭数据库操作对象
                para_st.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        if (para_conn != null) {
            try {
                //关闭数据库连接对象
                para_conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    /**
     * 重载关闭数据库连接
     *
     * @param para_priest
     * @param para_conn
     * @param para_res
     */
    public static void closeConn(PreparedStatement para_priest, Connection para_conn, ResultSet para_res) {
        if (para_priest != null) {
            try {
                para_priest.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        if (para_conn != null) {
            try {
                para_conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        if (para_res != null) {
            try {
                //释放数据集
                para_res.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
