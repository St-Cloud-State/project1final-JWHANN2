import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    // Print the wishlist for a specific client
    public void printClientWishlist(String clientId) {
        Client client = findClientById(clientId);
        if (client != null) {
            client.printWishlist();
        } else {
            System.out.println("Client not found.");
        }
    }

    // Record a payment for a specific client and update their balance
    public void recordPayment(String clientId, double amount) {
        Client client = findClientById(clientId);
        if (client != null) {
            client.addCredit(amount);
            System.out.println("Payment of $" + amount + " recorded for client " + clientId);
        } else {
            System.out.println("Client not found.");
        }
    }

    // Print the waitlist for a specific product
    public void printProductWaitlist(String productId) {
        Product product = getProductById(productId);
        if (product != null) {
            product.getWaitlist().printWaitlist();
        } else {
            System.out.println("Product not found.");
        }
    }

    // Method to add a product to a client's wishlist
    public void addToWishlist(String clientId, Product product, int quantity) {
        Client client = findClientById(clientId);
        if (client != null) {
            client.addToWishlist(product, quantity);
            product.getWaitlist().addWaitlistClient(clientId, quantity);
            System.out.println("Added client " + clientId + " to waitlist for product " + product.getProductId());
        } else {
            System.out.println("Client not found.");
        }
    }

    // Method to receive a shipment for a product and update its stock
    public void receiveShipment(String productId, int quantity) {
        Product product = getProductById(productId);
        if (product != null) {
            product.setQuantity(product.getQuantity() + quantity);
            System.out.println("Shipment received for product " + productId + ". Processing waitlist if applicable.");
            product.getWaitlist().processWaitlist(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    // Process an order for a client by fulfilling all items on their wishlist if stock is available
    public void processOrder(String clientId) {
        Client client = findClientById(clientId);
        if (client == null) {
            System.out.println("Client not found.");
            return;
        }

        Map<Product, Integer> wishlist = client.getWishlist();
        double totalCost = 0.0;  // Accumulate total cost here

        for (Map.Entry<Product, Integer> entry : wishlist.entrySet()) {
            Product product = entry.getKey();
            int requestedQuantity = entry.getValue();

            if (product.getQuantity() >= requestedQuantity) {
                // Calculate the cost for this product
                double cost = product.getPrice() * requestedQuantity;
                totalCost += cost;

                // Reduce product stock
                product.setQuantity(product.getQuantity() - requestedQuantity);
                System.out.println("Fulfilled " + requestedQuantity + " of " + product.getProductId() + " for client " + clientId);
            } else {
                // If stock is insufficient, add to waitlist
                int remainingQuantity = requestedQuantity - product.getQuantity();
                client.addToWishlist(product, remainingQuantity);
                product.getWaitlist().addWaitlistClient(clientId, remainingQuantity);
                System.out.println("Added " + remainingQuantity + " of " + product.getProductId() + " to waitlist for client " + clientId);
                product.setQuantity(0);
            }
        }

        // Deduct the total cost from the client's balance
        if (totalCost > 0) {
            client.addDebit(totalCost);
            System.out.println("Total cost of $" + totalCost + " deducted from client " + clientId + "'s balance.");
        } else {
            System.out.println("No items available in stock to fulfill the order for client " + clientId);
        }
    }

    public Client findClientById(String clientId) {
        for (Client client : clients) {
            if (client.getId().equals(clientId)) {
                return client;
            }
        }
        return null;
    }
}
