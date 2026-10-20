package com.shopsphere.web;

import com.shopsphere.dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns={"","/"})
public class HomeServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
        req.setAttribute("products",new ProductDAO().findActive(req.getParameter("q")));
        req.setAttribute("query",req.getParameter("q")==null?"":req.getParameter("q"));
        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req,resp);
    }
}
