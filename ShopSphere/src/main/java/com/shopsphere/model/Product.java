package com.shopsphere.model;

import java.math.BigDecimal;

public class Product {
    private long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private BigDecimal discount;
    private int stock;
    private String imageUrl;
    private String category;

    public Product(long id, String name, String brand, String description, BigDecimal price,
                   BigDecimal discount, int stock, String imageUrl, String category) {
        this.id=id; this.name=name; this.brand=brand; this.description=description;
        this.price=price; this.discount=discount; this.stock=stock; this.imageUrl=imageUrl; this.category=category;
    }
    public long getId(){return id;} public String getName(){return name;}
    public String getBrand(){return brand;} public String getDescription(){return description;}
    public BigDecimal getPrice(){return price;} public BigDecimal getDiscount(){return discount;}
    public int getStock(){return stock;} public String getImageUrl(){return imageUrl;}
    public String getCategory(){return category;}
    public BigDecimal getSalePrice(){return price.multiply(BigDecimal.ONE.subtract(discount.divide(new BigDecimal("100"))));}
}
