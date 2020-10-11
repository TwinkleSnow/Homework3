package com.homework3.dao;

import com.homework3.beans.User;
import com.homework3.utils.JDBCtools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * user -- DAO
 */
public class UserDao {
    /**
     * 获取所有用户信息
     *
     * @return
     */
    public List<User> getAllUser() {
        //初始化列表
        List<User> list = new ArrayList<User>();
        String sql = "SELECT * FROM user";
        try {
            //打开数据库连接
            Connection conn = JDBCtools.getConn();
            //初始化数据库操作执行接口
            PreparedStatement pst = conn.prepareStatement(sql);
            //初始化结果集
            ResultSet rs = pst.executeQuery();
            //遍历结果集到列表
            while (rs.next()) {
                User user = new User();
                user.setUserid(rs.getInt("userid"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                list.add(user);
            }
            //关闭连接，释放结果集，释放数据库操作对象
            JDBCtools.closeConn(pst, conn, rs);
        } catch (Exception e) {
            System.out.println("== getAllUser() ==");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 添加用户（用户注册）
     * @param user
     * @return
     */
    public boolean addUser(User user) {
        String sql = "INSERT INTO user(userid, username, password) VALUES(default, ?, ?)";
        try {
            Connection connection = JDBCtools.getConn();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            int count = pst.executeUpdate();
            JDBCtools.closeConn(pst, connection);
            return count > 0 ? true : false;
        } catch (Exception e) {
            System.out.println("== addUser(User user) ==");
            //在命令行打印异常信息在程序中出错的位置及原因
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE user SET username=?, password=? WHERE userid=?";
        try {
            Connection connection = JDBCtools.getConn();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setInt(3, user.getUserid());
            int count = pst.executeUpdate();
            JDBCtools.closeConn(pst, connection);
            return count > 0 ? true : false;
        } catch (Exception e) {
            System.out.println("== updateUser(User user, String username) ==");
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int userid) {
        String sql = "DELETE FROM user WHERE userid = ?";
        try {
            Connection connection = JDBCtools.getConn();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, userid);
            int count = 0;
            count = pst.executeUpdate();
            JDBCtools.closeConn(pst, connection);
            return count > 0 ? true : false;
        } catch (Exception e) {
            System.out.println("== deleteUser(String userid) ==");
            e.printStackTrace();
        }
        return false;
    }

    public User selectUserByUname(String username) {
        String sql = "SELECT * FROM user WHERE username = " + username;
        User user = null;
        try {
            Connection connection = JDBCtools.getConn();
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setUserid(resultSet.getInt("userid"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
            JDBCtools.closeConn(pst, connection, resultSet);
        } catch (Exception e) {
            System.out.println("== selectUserByUname(String username) ==");
            e.printStackTrace();
        }
        return user;
    }

    public User login(String username, String password) {
        User u = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCtools.getConn();
            String sql = "select * from user where username=? and password=?";
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                u = new User();
                u.setUsername(resultSet.getString("username"));
                u.setPassword(resultSet.getString("password"));
                System.out.println("登录成功");
            } else {
                System.out.println("用户名或者密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCtools.closeConn(preparedStatement, connection);
        }
        return u;
    }
}