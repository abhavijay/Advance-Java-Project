package com.shopsphere.demo.serialization;

import java.io.Serializable;

/** Small RTU syllabus demonstration of Java object serialization. */
public class ProductPacket implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int productId;
    private final String name;
    private final double price;
    public ProductPacket(int productId,String name,double price){this.productId=productId;this.name=name;this.price=price;}
    public int getProductId(){return productId;} public String getName(){return name;} public double getPrice(){return price;}
    @Override public String toString(){return "ProductPacket{id="+productId+", name='"+name+"', price="+price+"}";}
}
