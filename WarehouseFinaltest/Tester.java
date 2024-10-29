import java.io.*;
import java.util.*;

public class Tester {
    public static void main(String[] args) {
        Warehouse warehouse = Warehouse.instance();

        Client client1 = new Client("John Doe");
        Client client2 = new Client("Jane Smith");
        Client client3 = new Client("Jerry Johnson");

        warehouse.addClient(client1);
        warehouse.addClient(client2);
        warehouse.addClient(client3);

        System.out.println("Printing all clients:");
        Iterator<Client> clients = warehouse.getClients();

        while(clients.hasNext()) {
            System.out.println(clients.next());
        }


        Product p1 = new Product("Laptop", "P1", 1, 150.0);
        Product p2 = new Product("Apple", "P2", 1, 5.0);
        Product p3 = new Product("Fridge", "P3", 1, 2000.0);

        warehouse.addProduct(p1);
        warehouse.addProduct(p2);
        warehouse.addProduct(p3);
        Iterator products = warehouse.getProducts();

        System.out.println();
        // Prints all the products in the system
        System.out.println("Printing all products:");
        while(products.hasNext()) {
            System.out.println(products.next());
        }

        warehouse.addToWishlist("C1", "P1", 5);
        warehouse.addToWishlist("C1", "P2", 2);
        warehouse.addToWishlist("C1", "P3", 1);
        System.out.println();
        System.out.println(warehouse.searchForClient("C1"));
        System.out.println("Client's Wishlist:");
        Iterator wishlist = warehouse.getClientWishlist("C1");
        while(wishlist.hasNext()){
            System.out.println(wishlist.next());
        }

        warehouse.placeOrder("C1");

        System.out.println();
        System.out.println(warehouse.searchForClient("C1"));
        System.out.println("Client's Wishlist:");
        Iterator wishlist1 = warehouse.getClientWishlist("C1");
        while(wishlist1.hasNext()){
            System.out.println(wishlist1.next());
        }

        // Print invoices
        System.out.println();
        Iterator invoices = warehouse.getClientInvoices("C1");
        while(invoices.hasNext()) {
            System.out.println(invoices.next());
        }

        System.out.println();
        Iterator waitlist = warehouse.getProductWaitlist("P1");
        while(waitlist.hasNext()) {
            System.out.println(waitlist.next());
        }

        // Recieve shipment
        System.out.println();
        warehouse.receiveShipment("P1", 20);
        Iterator products1 = warehouse.getProducts();
        System.out.println();
        // Prints all the products in the system
        System.out.println("Printing all products:");
        while(products1.hasNext()) {
            System.out.println(products1.next());
        }

        // Print waitlist afte shipment
        System.out.println();
        Iterator waitlist1 = warehouse.getProductWaitlist("P1");
        while(waitlist1.hasNext()) {
            System.out.println(waitlist1.next());
        }

        // Print invoices after shipment recieved
        System.out.println();
        Iterator invoices1 = warehouse.getClientInvoices("C1");
        while(invoices1.hasNext()) {
            System.out.println(invoices1.next());
        }

        System.out.println(warehouse.searchForClient("C1"));
        warehouse.makePayment("C1", 2000.0);
        System.out.println(warehouse.searchForClient("C1"));
    }
}