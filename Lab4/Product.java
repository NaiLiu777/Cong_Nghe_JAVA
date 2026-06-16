public class Product {
    private int id;
    private String name;
    private String brand;
    private double price;
    private String image;
    private String description;

    public Product(int id, String name, String brand, double price, String image, String description) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.image = image;
        this.description = description;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getBrand() { return brand; }
    public double getPrice() { return price; }
    public String getImage() { return image; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return String.format("Product{id=%d, name='%s', brand='%s', price=%.2f, image='%s', description='%s'}", id, name, brand, price, image, description);
    }
}
