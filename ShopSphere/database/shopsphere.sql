CREATE DATABASE IF NOT EXISTS shopsphere CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE shopsphere;

CREATE TABLE IF NOT EXISTS users (
 user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(100) NOT NULL,
 email VARCHAR(160) NOT NULL UNIQUE,
 password_hash VARCHAR(100) NOT NULL,
 mobile VARCHAR(20),
 role ENUM('CUSTOMER','ADMIN') NOT NULL DEFAULT 'CUSTOMER',
 status ENUM('ACTIVE','INACTIVE') NOT NULL DEFAULT 'ACTIVE',
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS categories (
 category_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(80) NOT NULL UNIQUE,
 description VARCHAR(255),
 status TINYINT NOT NULL DEFAULT 1
);
CREATE TABLE IF NOT EXISTS products (
 product_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 category_id BIGINT,
 name VARCHAR(160) NOT NULL,
 brand VARCHAR(100),
 description TEXT,
 price DECIMAL(10,2) NOT NULL,
 discount DECIMAL(5,2) NOT NULL DEFAULT 0,
 stock INT NOT NULL DEFAULT 0,
 image_url VARCHAR(500),
 status TINYINT NOT NULL DEFAULT 1,
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 CONSTRAINT fk_products_category FOREIGN KEY (category_id) REFERENCES categories(category_id)
);
CREATE TABLE IF NOT EXISTS cart (
 cart_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 user_id BIGINT NOT NULL,
 product_id BIGINT NOT NULL,
 quantity INT NOT NULL DEFAULT 1,
 UNIQUE KEY uq_cart_user_product (user_id, product_id),
 FOREIGN KEY (user_id) REFERENCES users(user_id),
 FOREIGN KEY (product_id) REFERENCES products(product_id)
);
CREATE TABLE IF NOT EXISTS wishlist (
 wishlist_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 user_id BIGINT NOT NULL,
 product_id BIGINT NOT NULL,
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 UNIQUE KEY uq_wishlist_user_product (user_id, product_id),
 FOREIGN KEY (user_id) REFERENCES users(user_id),
 FOREIGN KEY (product_id) REFERENCES products(product_id)
);
CREATE TABLE IF NOT EXISTS orders (
 order_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 user_id BIGINT NOT NULL,
 total_amount DECIMAL(10,2) NOT NULL,
 discount DECIMAL(10,2) NOT NULL DEFAULT 0,
 payment_method VARCHAR(40) NOT NULL DEFAULT 'MOCK',
 payment_status VARCHAR(30) NOT NULL DEFAULT 'PENDING',
 order_status VARCHAR(30) NOT NULL DEFAULT 'PLACED',
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE IF NOT EXISTS order_items (
 order_item_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 order_id BIGINT NOT NULL,
 product_id BIGINT NOT NULL,
 quantity INT NOT NULL,
 price DECIMAL(10,2) NOT NULL,
 FOREIGN KEY (order_id) REFERENCES orders(order_id),
 FOREIGN KEY (product_id) REFERENCES products(product_id)
);
CREATE TABLE IF NOT EXISTS reviews (
 review_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 user_id BIGINT NOT NULL,
 product_id BIGINT NOT NULL,
 rating INT NOT NULL,
 review_text VARCHAR(1000),
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY (user_id) REFERENCES users(user_id),
 FOREIGN KEY (product_id) REFERENCES products(product_id)
);

INSERT IGNORE INTO categories(category_id,name,description) VALUES
(1,'Footwear','Everyday footwear'),(2,'Fashion','Clothing and accessories'),(3,'Tech','Useful gadgets');

INSERT INTO products(category_id,name,brand,description,price,discount,stock,image_url)
SELECT 1,'Cloud Runner Sneakers','Northstar','Lightweight everyday sneakers.',2499,15,25,'https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=800'
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name='Cloud Runner Sneakers');
INSERT INTO products(category_id,name,brand,description,price,discount,stock,image_url)
SELECT 2,'Everyday Tote Bag','UrbanNest','Minimal tote for daily essentials.',899,10,40,'https://images.unsplash.com/photo-1548036328-c9fa89d128fa?w=800'
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name='Everyday Tote Bag');
INSERT INTO products(category_id,name,brand,description,price,discount,stock,image_url)
SELECT 3,'Wireless Headphones','SoundPeak','Comfortable over-ear headphones.',3299,12,18,'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=800'
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name='Wireless Headphones');
