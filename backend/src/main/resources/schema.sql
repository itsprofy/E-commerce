-- Create database if not exists
CREATE DATABASE IF NOT EXISTS ecommerce;
USE ecommerce;

-- Drop tables if they exist
DROP TABLE IF EXISTS product_images;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS carousel_images;

-- Create categories table
CREATE TABLE IF NOT EXISTS categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL
);

-- Create products table
CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    stock_quantity INT NOT NULL,
    featured BOOLEAN DEFAULT FALSE,
    category_id BIGINT NOT NULL,
    main_image_url VARCHAR(1000),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Create product_images table
CREATE TABLE IF NOT EXISTS product_images (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    image_url VARCHAR(255) NOT NULL,
    product_id BIGINT NOT NULL,
    display_order INT,
    active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Create carousel_images table
CREATE TABLE IF NOT EXISTS carousel_images (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    image_url VARCHAR(255) NOT NULL,
    title VARCHAR(100),
    subtitle VARCHAR(200),
    display_order INT,
    active BOOLEAN DEFAULT TRUE
);

-- Insert initial categories
INSERT INTO categories (name, description) VALUES
('Electronics', 'Latest electronic gadgets and devices'),
('Clothing', 'Fashionable clothing for all seasons'),
('Books', 'Best-selling books and literature');

-- Insert initial products
INSERT INTO products (name, description, price, stock_quantity, featured, category_id, main_image_url) VALUES
('Smartphone X', 'Latest smartphone with advanced features', 999.99, 50, true, 1, 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=500'),
('Laptop Pro', 'High-performance laptop for professionals', 1499.99, 30, true, 1, 'https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=500'),
('T-Shirt', 'Comfortable cotton t-shirt', 29.99, 100, false, 2, 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=500'),
('Programming Book', 'Learn programming from scratch', 49.99, 20, true, 3, 'https://images.unsplash.com/photo-1544947950-fa07a98d237f?w=500');

-- Insert initial carousel images
INSERT INTO carousel_images (image_url, title, subtitle, display_order, active) VALUES
('https://images.unsplash.com/photo-1441986300917-64674bd600d8?w=1200', 'Summer Sale', 'Up to 50% off on selected items', 1, true),
('https://images.unsplash.com/photo-1441986300917-64674bd600d8?w=1200', 'New Arrivals', 'Check out our latest products', 2, true); 