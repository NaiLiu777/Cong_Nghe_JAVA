# Lab4 — Lưu & truy vấn sản phẩm từ CSDL

Yêu cầu: Dựa trên bài thực hành 03, xây dựng cơ sở dữ liệu để lưu thông tin sản phẩm và truy vấn sản phẩm từ CSDL.

Chuẩn bị
- MySQL hoặc MariaDB (Laragon) đang chạy
- JDK 21+
- MariaDB JDBC driver hoặc MySQL Connector/J (đặt file jar vào thư mục `Lab4` nếu chạy từ terminal)

Các file trong thư mục `Lab4`
- `product_db.sql` — script tạo CSDL `productdb`, bảng `products` và dữ liệu mẫu
- `Product.java` — lớp model sản phẩm
- `ProductDAO.java` — DAO dùng JDBC để truy vấn `products` (gồm `findAll()` và `findByBrand()`)

Hướng dẫn nhanh
1. Tạo CSDL bằng script (ví dụ dùng HeidiSQL hoặc mysql client):
   - Mở `product_db.sql` và chạy trên server MySQL/MariaDB của bạn.

2. Nếu dùng MariaDB driver, tải `mariadb-java-client-<version>.jar` và đặt vào `Lab4`.

3. Biên dịch và chạy demo:
```powershell
cd "path\to\Lab4"
javac Product.java ProductDAO.java
java -cp ".;mariadb-java-client-3.0.8.jar" ProductDAO
```

4. Nếu dùng MySQL Connector/J, chỉnh `ProductDAO.java` driver class (`com.mysql.cj.jdbc.Driver`) và chạy tương tự với `mysql-connector-java-<version>.jar`.

Ghi chú
- Thay `USER` và `PASSWORD` trong `ProductDAO.java` nếu cần.
- Nếu muốn chuyển sang SQLite, bạn có thể dùng `sqlite-jdbc` và sửa `ProductDAO` hoặc dùng file `src/Main.java` mẫu từ Lab3/Repo để dùng JavaFX + SQLite.
