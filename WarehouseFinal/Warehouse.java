import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Warehouse {
    private List<Client> clients;
    private List<Product> products;

    public Warehouse() {
        clients = new ArrayList<>();
        products = new ArrayList<>();
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public List<Client> getClientList() {
        return clients;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProductList() {
        return products;
    }

    public Product getProductById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public void processOrder(String clientId, String productId, int quantity) {
        Client client = findClientById(clientId);
        Product product = getProductById(productId);

        if (client == null || product == null) {
            System.out.println("Client or Product not found.");
            return;
        }

        if (product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
            double cost = product.getPrice() * quantity;
            client.addDebit(cost);
            System.out.println("Order processed for client: " + clientId);
        } else {
            client.addToWishlist(product); // Adjust to match existing Client methods
            product.setQuantity(0);
            System.out.println("Insufficient stock. Added to wishlist.");
        }
    }

    public void recordPayment(String clientId, double amount) {
        Client client = findClientById(clientId);
        if (client != null) {
            client.addCredit(amount);
        } else {
            System.out.println("Client not found.");
        }
    }

    public void receiveShipment(String productId, int quantity) {
        Product product = getProductById(productId);
        if (product != null) {
            product.setQuantity(product.getQuantity() + quantity);
            System.out.println("Shipment received.");
        }
    }

    public Client findClientById(String clientId) {
        for (Client client : clients) {
            if (client.getClientId().equals(clientId)) { // Adjusted based on Client's method
                return client;
            }
        }
        return null;
    }

    public void addToWishlist(String clientId, Product product) {
        Client client = findClientById(clientId);
        if (client != null) {
            client.addToWishlist(product); // Call Client's method
        } else {
            System.out.println("Client not found.");
        }
    }
}

