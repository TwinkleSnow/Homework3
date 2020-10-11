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
 * 修改用户信息的 servlet
 */
@WebServlet(name = "ServletUpdate")
public class ServletUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPut(request, response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        User user = new User();
        user.setUserid(Integer.valueOf(request.getParameter("userid")));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        UserDao userDao = new UserDao();
        boolean status = userDao.updateUser(user);
        if (status) {
            System.out.println("==== 修改成功！ ====");
        } else
            System.out.println("==== 修改失败！ ====");
    }
}
