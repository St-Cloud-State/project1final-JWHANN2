import java.util.Scanner;

public class UserInterface {
    private Warehouse warehouse;
    private Scanner scanner;

    public UserInterface() {
        warehouse = new Warehouse();
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
            System.out.println("6. Print Client Wishlist");
            System.out.println("7. Place Order");
            System.out.println("8. Record Payment");
            System.out.println("9. Receive Shipment");
            System.out.println("10. Print Product Waitlist");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addClient(); // Adding Client using updated method
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
                    printWishlist();
                    break;
                case 7:
                    placeOrder();
                    break;
                case 8:
                    recordPayment();
                    break;
                case 9:
                    receiveShipment();
                    break;
                case 10:
                    printWaitlist();
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

    // Prompts for client ID and name, then adds to the warehouse
    private void addClient() {
        System.out.print("Enter client ID (e.g., c1): ");
        String id = scanner.nextLine();
        System.out.print("Enter client name: ");
        String name = scanner.nextLine();
        
        Client client = new Client(id, name); // Pass both id and name
        warehouse.addClient(client);
        System.out.println("Client added successfully.");
    }

    private void displayAllClients() {
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
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Product product = warehouse.getProductById(productId);
        if (product != null) {
            warehouse.addToWishlist(clientId, product, quantity);
        } else {
            System.out.println("Product not found.");
        }
    }

    private void printWishlist() {
        System.out.print("Enter client ID: ");
        String clientId = scanner.nextLine();
        warehouse.printClientWishlist(clientId);
    }

    private void placeOrder() {
    System.out.print("Enter client ID: ");
    String clientId = scanner.nextLine();
    warehouse.processOrder(clientId); // Process the entire wishlist
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
    }

    // New method to print the waitlist for a specific product
    private void printWaitlist() {
        System.out.print("Enter product ID to view waitlist: ");
        String productId = scanner.nextLine();
        warehouse.printProductWaitlist(productId); // Corrected call to print waitlist
    }
    


    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.start();
    }
}
