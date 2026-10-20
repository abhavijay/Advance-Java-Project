package com.shopsphere.web;

import com.shopsphere.util.DB;
import org.mindrot.jbcrypt.BCrypt;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
        req.getRequestDispatcher("/WEB-INF/views/auth.jsp").forward(req,resp);
    }
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
        String action=req.getParameter("action"), email=req.getParameter("email"), password=req.getParameter("password");
        String message="Something went wrong. Check database configuration.";
        try(Connection c=DB.getConnection()) {
            if("register".equals(action)) {
                String name=req.getParameter("name");
                try(PreparedStatement p=c.prepareStatement("INSERT INTO users(name,email,password_hash) VALUES(?,?,?)")) {
                    p.setString(1,name);p.setString(2,email);p.setString(3,BCrypt.hashpw(password,BCrypt.gensalt()));
                    p.executeUpdate();message="Registration successful. You can now log in.";
                }
            } else {
                try(PreparedStatement p=c.prepareStatement("SELECT user_id,name,password_hash,role FROM users WHERE email=? AND status='ACTIVE'")) {
                    p.setString(1,email);
                    try(ResultSet r=p.executeQuery()) {
                        if(r.next()&&BCrypt.checkpw(password,r.getString("password_hash"))) {
                            HttpSession old=req.getSession(false);if(old!=null)old.invalidate();
                            HttpSession s=req.getSession(true);s.setAttribute("userId",r.getLong("user_id"));s.setAttribute("userName",r.getString("name"));s.setAttribute("role",r.getString("role"));
                            resp.sendRedirect(req.getContextPath()+"/");return;
                        } else message="Email or password is incorrect.";
                    }
                }
            }
        } catch(SQLException e) { message="Database connection failed. Fix MySQL password in DB.java and run database/shopsphere.sql."; }
        req.setAttribute("message",message);req.getRequestDispatcher("/WEB-INF/views/auth.jsp").forward(req,resp);
    }
}
