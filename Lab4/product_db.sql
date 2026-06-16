-- Lab4: Database schema for product storage (MySQL)
CREATE DATABASE IF NOT EXISTS productdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE productdb;

CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    brand VARCHAR(100),
    price DECIMAL(10,2) NOT NULL,
    image VARCHAR(255),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Sample data
INSERT INTO products (name, brand, price, image, description) VALUES
('4DFWD PULSE SHOES', 'Adidas', 160.00, 'img1.png', 'This product is excluded from all promotional discounts and offers.'),
('FORUM MID SHOES', 'Adidas', 100.00, 'img2.png', 'Classic mid-top design for everyday wear.'),
('SUPERNOVA SHOES', 'Adidas', 150.00, 'img3.png', 'High-performance running sneakers with responsive cushioning.'),
('Adidas NMD', 'Adidas', 160.00, 'img4.png', 'Street-ready design with lightweight comfort.'),
('Adidas NMD Black', 'Adidas', 120.00, 'img5.png', 'Black edition with modern styling.'),
('4DFWD PULSE SHOES', 'Adidas', 160.00, 'img6.png', 'This product is excluded from all promotional discounts and offers.');

-- Example queries:
-- SELECT * FROM products;
-- SELECT name, price FROM products WHERE price BETWEEN 100 AND 160;
-- SELECT * FROM products WHERE brand = 'Adidas';
