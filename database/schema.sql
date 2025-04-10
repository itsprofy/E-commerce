-- Create database if it doesn't exist
CREATE DATABASE IF NOT EXISTS ecommerce;
USE ecommerce;

-- Create categories table
CREATE TABLE IF NOT EXISTS categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT
);

-- Create products table
CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    image_url VARCHAR(255),
    stock_quantity INT,
    category_id BIGINT,
    featured BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Insert sample categories
INSERT INTO categories (name, description) VALUES
('Electronics', 'Electronic devices and accessories'),
('Clothing', 'Fashion and apparel'),
('Books', 'Books and publications'),
('Home & Garden', 'Home decor and garden supplies');

-- Insert sample products
INSERT INTO products (name, description, price, image_url, stock_quantity, category_id, featured) VALUES
('Smartphone X', 'Latest smartphone with advanced features', 999.99, 'https://example.com/smartphone.jpg', 50, 1, true),
('Laptop Pro', 'Professional laptop for developers', 1499.99, 'https://example.com/laptop.jpg', 30, 1, true),
('Cotton T-Shirt', 'Comfortable cotton t-shirt', 29.99, 'https://example.com/tshirt.jpg', 100, 2, false),
('Garden Tool Set', 'Complete set of garden tools', 79.99, 'https://example.com/garden-tools.jpg', 25, 4, true); 