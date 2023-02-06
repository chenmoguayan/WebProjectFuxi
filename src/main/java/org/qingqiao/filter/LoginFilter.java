package org.qingqiao.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("login_info");

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String m = request.getParameter("m");
        if ("login".equals(m) || requestURI.contains("index") || (contextPath + "/").equals(requestURI)){
            filterChain.doFilter(request,response);
        }else {
            if (user != null){
                filterChain.doFilter(request,response);
            }else {
                session.setAttribute("login_error","请先登录");
                response.sendRedirect("index.jsp");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
