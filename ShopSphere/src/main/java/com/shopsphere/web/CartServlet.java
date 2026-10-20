package com.shopsphere.web;

import com.shopsphere.dao.ProductDAO;
import com.shopsphere.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @SuppressWarnings("unchecked")
    private Map<Long,Integer> cart(HttpSession s) {
        Map<Long,Integer> cart=(Map<Long,Integer>)s.getAttribute("cart");
        if(cart==null){cart=new LinkedHashMap<>();s.setAttribute("cart",cart);}
        return cart;
    }
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
        Map<Long,Integer> cart=cart(req.getSession());
        List<Map<String,Object>> lines=new ArrayList<>();
        List<Product> all=new ProductDAO().findActive("");
        double total=0;
        for(Product p:all) if(cart.containsKey(p.getId())) {
            int qty=cart.get(p.getId()); double line=p.getSalePrice().doubleValue()*qty; total+=line;
            Map<String,Object> m=new HashMap<>();m.put("product",p);m.put("quantity",qty);m.put("lineTotal",line);lines.add(m);
        }
        req.setAttribute("lines",lines);req.setAttribute("total",total);
        req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req,resp);
    }
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws IOException {
        Map<Long,Integer> cart=cart(req.getSession());
        String action=req.getParameter("action");
        try {
            long id=Long.parseLong(req.getParameter("id"));
            if("remove".equals(action)) cart.remove(id);
            else cart.merge(id,1,Integer::sum);
        } catch(Exception ignored) {}
        resp.sendRedirect(req.getContextPath()+"/cart");
    }
}
