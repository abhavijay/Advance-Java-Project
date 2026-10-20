package com.shopsphere.dao;

import com.shopsphere.model.Product;
import com.shopsphere.util.DB;
import java.sql.*;
import java.math.BigDecimal;
import java.util.*;

public class ProductDAO {
    public List<Product> findActive(String query) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.product_id,p.name,p.brand,p.description,p.price,p.discount,p.stock,p.image_url,COALESCE(c.name,'General') category " +
                     "FROM products p LEFT JOIN categories c ON p.category_id=c.category_id WHERE p.status=1 " +
                     "AND (p.name LIKE ? OR p.brand LIKE ? OR c.name LIKE ?) ORDER BY p.product_id DESC";
        try (Connection con=DB.getConnection(); PreparedStatement ps=con.prepareStatement(sql)) {
            String q = "%" + (query == null ? "" : query.trim()) + "%";
            ps.setString(1,q); ps.setString(2,q); ps.setString(3,q);
            try(ResultSet rs=ps.executeQuery()) {
                while(rs.next()) products.add(new Product(rs.getLong("product_id"),rs.getString("name"),
                    rs.getString("brand"),rs.getString("description"),rs.getBigDecimal("price"),
                    rs.getBigDecimal("discount"),rs.getInt("stock"),rs.getString("image_url"),rs.getString("category")));
            }
        } catch (SQLException e) {
            // Storefront remains demo-friendly before local MySQL has been configured.
            products.addAll(sampleProducts(query));
        }
        return products;
    }
    private List<Product> sampleProducts(String query) {
        List<Product> all = List.of(
          new Product(1,"Cloud Runner Sneakers","Northstar","Lightweight everyday sneakers.",new BigDecimal("2499"),new BigDecimal("15"),25,"https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=800","Footwear"),
          new Product(2,"Everyday Tote Bag","UrbanNest","Minimal tote for daily essentials.",new BigDecimal("899"),new BigDecimal("10"),40,"https://images.unsplash.com/photo-1548036328-c9fa89d128fa?w=800","Fashion"),
          new Product(3,"Wireless Headphones","SoundPeak","Comfortable over-ear headphones.",new BigDecimal("3299"),new BigDecimal("12"),18,"https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=800","Tech"),
          new Product(4,"Classic White Sneakers","Northstar","Clean design for everyday outfits.",new BigDecimal("1899"),new BigDecimal("8"),14,"https://images.unsplash.com/photo-1495555961986-6d4c1ecb7be3?w=800","Footwear"),
          new Product(5,"Minimal Watch","Forma","Simple everyday timepiece.",new BigDecimal("1599"),new BigDecimal("20"),12,"https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=800","Fashion"),
          new Product(6,"Desk Lamp","Brightly","Warm light for study and work.",new BigDecimal("1199"),new BigDecimal("5"),20,"https://images.unsplash.com/photo-1507473885765-e6ed057f782c?w=800","Tech")
        );
        String q=(query==null?"":query.toLowerCase().trim());
        if(q.isEmpty()) return all;
        List<Product> filtered=new ArrayList<>();
        for(Product p:all) if(p.getName().toLowerCase().contains(q)||p.getBrand().toLowerCase().contains(q)||p.getCategory().toLowerCase().contains(q)) filtered.add(p);
        return filtered;
    }
}
