import java.util.Scanner;
import java.util.Iterator; // Import added for Iterator

public class UserInterface {
    private Warehouse warehouse;
    private Scanner scanner;

    public UserInterface() {
        warehouse = new Warehouse(); // Ensure this constructor is public
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Warehouse System Menu ---");
            System.out.println("1. Add Client");
            System.out.println("2. Display All Clients");
            System.out.println("3. Add Product");
            System.out.println("4. Display All Products");
            System.out.println("5. Add to Wishlist");
            System.out.println("6. Place Order");
            System.out.println("7. Record Payment");
            System.out.println("8. Receive Shipment");
            System.out.println("9. Display Product Waitlist");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addClient();
                    break;
                case 2:
                    displayAllClients();
                    break;
                case 3:
                    addProduct();
                    break;
                case 4:
                    displayAllProducts();
                    break;
                case 5:
                    addToWishlist();
                    break;
                case 6:
                    placeOrder();
                    break;
                case 7:
                    recordPayment();
                    break;
                case 8:
                    receiveShipment();
                    break;
                case 9:
                    displayProductWaitlist();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        System.out.println("Exiting system...");
    }

    private void addClient() {
        System.out.print("Enter client name: ");
        String name = scanner.nextLine();
        Client client = new Client(name); // Adjusted constructor to match existing signature
        warehouse.addClient(client);
        System.out.println("Client added successfully.");
    }

    private void displayAllClients() {
        // Assuming Warehouse maintains a collection of clients
        for (Client client : warehouse.getClientList()) {
            System.out.println(client);
        }
    }

    private void addProduct() {
        System.out.print("Enter product ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        Product product = new Product(name, id, quantity, price); // Use correct constructor
        warehouse.addProduct(product);
        System.out.println("Product added successfully.");
    }

    private void displayAllProducts() {
        for (Product product : warehouse.getProductList()) {
            System.out.println(product);
        }
    }

    private void addToWishlist() {
        System.out.print("Enter client ID: ");
        String clientId = scanner.nextLine();
        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine();
        Product product = warehouse.getProductById(productId);
        if (product != null) {
            warehouse.addToWishlist(clientId, product); // Assuming Warehouse handles wishlist
        } else {
            System.out.println("Product not found.");
        }
    }

    private void placeOrder() {
        System.out.print("Enter client ID: ");
        String clientId = scanner.nextLine();
        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        warehouse.processOrder(clientId, productId, quantity);
    }

    private void recordPayment() {
        System.out.print("Enter client ID: ");
        String clientId = scanner.nextLine();
        System.out.print("Enter payment amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        warehouse.recordPayment(clientId, amount);
        System.out.println("Payment recorded successfully.");
    }

    private void receiveShipment() {
        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter shipment quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        warehouse.receiveShipment(productId, quantity);
        System.out.println("Shipment received and processed.");
    }

    private void displayProductWaitlist() {
        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine();
        Product product = warehouse.getProductById(productId);
        if (product != null) {
            System.out.println("Waitlist for Product " + productId + ":");
            Iterator<WaitlistClient> waitlist = product.getWaitlist();
            while (waitlist.hasNext()) {
                System.out.println(waitlist.next());
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.start();
    }
}
