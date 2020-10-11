package com.homework3.services;

import com.homework3.beans.User;
import com.homework3.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 执行用户登录操作的 servlet
 */
@WebServlet(name = "RegisServlet")
public class ServletRegis extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPut(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        int userid = Integer.valueOf(request.getParameter("userid"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUserid(userid);
        user.setUsername(username);
        user.setPassword(password);
        UserDao userDao = new UserDao();
        boolean status = userDao.addUser(user);
        if (status)
            System.out.println("==== 注册成功！ ====");
        else
            System.out.println("==== 注册失败！ ====");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
