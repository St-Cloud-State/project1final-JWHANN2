import java.util.Map;
import java.util.HashMap;

public class Client {
    private String id;
    private String name;
    private double balance;
    private Map<Product, Integer> wishlist;

    public Client(String id, String name) {
        this.id = id;
        this.name = name;
        this.balance = 0.0;
        this.wishlist = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public Map<Product, Integer> getWishlist() {
        return wishlist;
    }

    public void addDebit(double amount) {
        balance -= amount;
    }

    public void addCredit(double amount) {
        balance += amount;
    }

    @Override
    public String toString() {
        return "Client ID: " + id + ", Name: " + name + ", Balance: $" + balance;
    }

    public void addToWishlist(Product product, int quantity) {
        wishlist.put(product, wishlist.getOrDefault(product, 0) + quantity);
    }

    public void printWishlist() {
        System.out.println("Wishlist for client " + name + ":");
        wishlist.forEach((product, quantity) -> {
            System.out.println("Product: " + product.getProductId() + ", Name: " + product + ", Quantity: " + quantity);
        });
    }
}
