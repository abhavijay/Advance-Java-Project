package com.shopsphere.web;
import com.shopsphere.dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
 @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
  HttpSession s=req.getSession(false);
  if(s==null || !"ADMIN".equals(s.getAttribute("role"))) { resp.sendError(403,"Admin access only. Set a user's role to ADMIN in MySQL after registering."); return; }
  req.setAttribute("products",new ProductDAO().findActive(""));
  req.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req,resp);
 }
}
