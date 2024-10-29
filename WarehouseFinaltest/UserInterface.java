import java.util.Scanner;

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
            System.out.println("6. Print Wishlist");
            System.out.println("7. Place Order");
            System.out.println("8. Record Payment");
            System.out.println("9. Receive Shipment");
            System.out.println("10. Print Invoices");
            System.out.println("11. Print Product Waitlist");
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
                    printInvoices();
                    break;
                case 11:
                    printProductWaitlist();
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
        Client client = new Client(name);
        warehouse.addClient(client);
        System.out.println("Client added successfully.");
    }

    private void displayAllClients() {
        for (Client client : warehouse.getClients()) { // Assumes Warehouse has getClients()
            System.out.println(client); // This should call the Client class's toString() method
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
        Product product = new Product(id, name, quantity, price);
        warehouse.addProduct(product);
        System.out.println("Product added successfully.");
    }

    private void displayAllProducts() {
        for (Product product : warehouse.getProducts()) { // Assumes Warehouse has getProducts()
            System.out.println(product); // This should call the Product class's toString() method
        }
    }

    private void addToWishlist() {
        System.out.print("Enter client name: ");
        String clientName = scanner.nextLine();
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        warehouse.addToWishlist(clientName, productName, quantity);
    }

    private void printWishlist() {
        System.out.print("Enter client name: ");
        String clientName = scanner.nextLine();
        Client client = warehouse.findClientByName(clientName); // Assumes Warehouse has this method
        if (client != null) {
            client.printWishlist(); // Assuming Client class has a printWishlist() method
        } else {
            System.out.println("Client not found.");
        }
    }

    private void placeOrder() {
        System.out.print("Enter client name: ");
        String clientName = scanner.nextLine();
        warehouse.placeOrder(clientName); // Assuming Warehouse correctly handles order placement
    }

    private void recordPayment() {
        System.out.print("Enter client name: ");
        String clientName = scanner.nextLine();
        System.out.print("Enter payment amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        Client client = warehouse.findClientByName(clientName);
        if (client != null) {
            client.recordPayment(amount); // Assuming Client class has recordPayment()
            System.out.println("Payment recorded successfully.");
        } else {
            System.out.println("Client not found.");
        }
    }

    private void receiveShipment() {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter shipment quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        warehouse.receiveShipment(productName, quantity); // Assuming this handles it correctly
        System.out.println("Shipment received and processed.");
    }

    private void printInvoices() {
        System.out.print("Enter client name: ");
        String clientName = scanner.nextLine();
        Client client = warehouse.findClientByName(clientName);
        if (client != null) {
            client.printInvoices(); // Assuming Client class has a printInvoices() method
        } else {
            System.out.println("Client not found.");
        }
    }

    private void printProductWaitlist() {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        Product product = warehouse.findProductByName(productName); // Assumes this method exists
        if (product != null) {
            product.printWaitlist(); // Assuming Product has a method to print its waitlist
        } else {
            System.out.println("Product not found.");
        }
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.start();
    }
}
