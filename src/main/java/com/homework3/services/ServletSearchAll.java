package com.homework3.services;

import com.homework3.beans.User;
import com.homework3.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 查询所有的用户信息
 */
@WebServlet(name = "SearchAll")
public class ServletSearchAll extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao ud = new UserDao();
        List<User> userAll = ud.getAllUser();
        for (User qw : userAll
        ) {
            System.out.println(qw);
        }
        request.setAttribute("userAll", userAll);
        request.getRequestDispatcher("/success.jsp").forward(request, response);
    }
}
