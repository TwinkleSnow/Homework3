package com.homework3.services;

import com.homework3.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 执行用户信息删除操作的 servlet
 */
@WebServlet(name = "ServletDelete")
public class ServletDelete extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.valueOf(request.getParameter("userid"));
        UserDao userDao = new UserDao();
        boolean status = userDao.deleteUser(id);
        if (status) {
            System.out.println("==== 删除成功！ ====");
        } else
            System.out.println("==== 删除失败！ ====");
    }
}
