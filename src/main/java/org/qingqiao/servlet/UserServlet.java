package org.qingqiao.servlet;

import org.qingqiao.bean.User;
import org.qingqiao.dao.UserDao;
import org.qingqiao.utils.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Administrator
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final UserDao userDao = new UserDao();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("utf-8");
//        resp.setCharacterEncoding("utf-8");
        String m = req.getParameter("m");
        if ("list".equals(m)){
            list(req,resp);
        }else if ("login".equals(m)) {
            login(req,resp);
        }else if ("queryById".equals(m)) {
            queryById(req,resp);
        }else if ("update".equals(m)) {
            update(req,resp);
        }else if ("insert".equals(m)) {
            insert(req,resp);
        }else if ("delete".equals(m)) {
            delete(req,resp);
        }else if ("logout".equals(m)) {
            logout(req,resp);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User login = userDao.login(username, password);
        HttpSession session = req.getSession();
        if (login != null){
            Cookie username1 = new Cookie("username", username);
            Cookie password1 = new Cookie("password", password);
            username1.setMaxAge(60 * 60 * 24 * 7);
            password1.setMaxAge(60 * 60 * 24 * 7);
            resp.addCookie(username1);
            resp.addCookie(password1);

            session.setAttribute("login_info",username);
            session.setAttribute("login_error",null);
            resp.sendRedirect("user?m=list");
        }else {
            session.setAttribute("login_error","账户或密码错误，请重新输入！");
            resp.sendRedirect("index.jsp");
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect("index.jsp");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int delete = userDao.delete(id);
        if (delete > 0) {
            resp.sendRedirect("user?m=list");
        }
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String sex = req.getParameter("sex");
        String birthday = req.getParameter("birthday");
        String address = req.getParameter("address");
        String hobby = "";
        for (String h : req.getParameterValues("hobby")) {
            hobby += "," + h;
        }
        hobby = hobby.substring(1);
        User user = new User(null, username, password, sex, birthday, address, hobby);
        int insert = userDao.insert(user);
        if (insert > 0 ){
            resp.sendRedirect("user?m=list");
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String sex = req.getParameter("sex");
        String birthday = req.getParameter("birthday");
        String address = req.getParameter("address");
        String hobby = "";
        for (String h : req.getParameterValues("hobby")) {
             hobby += "," + h;
        }
        hobby = hobby.substring(1);
        User user = new User(id, username, password, sex, birthday, address, hobby);
        int i = userDao.update(user);
        if (i > 0){
            resp.sendRedirect("user?m=list");
        }

    }

    private void queryById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = new User();
        user = userDao.queryById(id);
        req.setAttribute("user",user);
        req.getRequestDispatcher("user/update.jsp").forward(req,resp);
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<User> l;
        String currentPage = req.getParameter("currentPage");
        String mohu = req.getParameter("mohu");
//        if (mohu == null ){
//            mohu = "";
//        }
        mohu = mohu == null ? "" : mohu;
        int count = userDao.count(mohu);
        PageUtil pageUtil = new PageUtil(currentPage,count,3);
        l = userDao.queryAll(pageUtil,mohu);
        req.setAttribute("list",l);
        req.setAttribute("page", pageUtil);
        req.setAttribute("mohu",mohu);
        req.getRequestDispatcher("user/list.jsp").forward(req,resp);
    }
}
