import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple DAO to connect to MySQL/MariaDB and query products.
 * Update URL, USER, PASSWORD as needed for your environment.
 */
public class ProductDAO {
    // Example MySQL/MariaDB URL (change host/port if needed)
    private static final String URL = "jdbc:mariadb://127.0.0.1:3306/productdb?useUnicode=true&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // set your DB password

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // If using MySQL connector, use com.mysql.cj.jdbc.Driver
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static List<Product> findAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT id, name, brand, price, image, description FROM products";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getDouble("price"),
                        rs.getString("image"),
                        rs.getString("description")
                ));
            }
            return products;
        }
    }

    public static List<Product> findByBrand(String brand) throws SQLException, ClassNotFoundException {
        String sql = "SELECT id, name, brand, price, image, description FROM products WHERE brand = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, brand);
            try (ResultSet rs = stmt.executeQuery()) {
                List<Product> products = new ArrayList<>();
                while (rs.next()) {
                    products.add(new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("brand"),
                            rs.getDouble("price"),
                            rs.getString("image"),
                            rs.getString("description")
                    ));
                }
                return products;
            }
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Danh sách sản phẩm:");
            for (Product p : findAll()) System.out.println(p);

            System.out.println("\nSản phẩm Adidas:");
            for (Product p : findByBrand("Adidas")) System.out.println(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
