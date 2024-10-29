public class Product {
    private String name;
    private String productId;
    private int quantity;
    private double price;
    private Waitlist waitlist;

    public Product(String name, String productId, int quantity, double price) {
        this.name = name;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.waitlist = new Waitlist(); // Initialize waitlist here
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public Waitlist getWaitlist() {
        return waitlist;
    }

    // Override toString to display product information
    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + name + ", Quantity: " + quantity + ", Price: $" + price;
    }
}
