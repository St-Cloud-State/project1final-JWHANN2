import java.util.LinkedList;
import java.util.Queue;

public class Waitlist {
    private Queue<WaitlistClient> waitlistQueue;

    public Waitlist() {
        waitlistQueue = new LinkedList<>();
    }

    // Adds a client to the waitlist for a specific product quantity
    public void addWaitlistClient(String clientId, int quantity) {
        WaitlistClient waitlistClient = new WaitlistClient(clientId, quantity);
        waitlistQueue.add(waitlistClient);
        System.out.println("Added client " + clientId + " to waitlist for quantity: " + quantity);
    }

    // Processes waitlist based on available stock, in FIFO order
    public void processWaitlist(Product product) {
        int availableQuantity = product.getQuantity();
        while (!waitlistQueue.isEmpty() && availableQuantity > 0) {
            WaitlistClient waitlistClient = waitlistQueue.peek();
            int requestedQuantity = waitlistClient.getQuantity();

            if (availableQuantity >= requestedQuantity) {
                availableQuantity -= requestedQuantity;
                System.out.println("Fulfilling waitlist for client " + waitlistClient.getClientId() +
                                   " with quantity: " + requestedQuantity);
                waitlistQueue.poll(); // Remove client from waitlist as their request is fulfilled
            } else {
                waitlistClient.setQuantity(requestedQuantity - availableQuantity);
                System.out.println("Partially fulfilling waitlist for client " + waitlistClient.getClientId() +
                                   " with available quantity: " + availableQuantity);
                availableQuantity = 0; // All stock is used up
            }
        }
        product.setQuantity(availableQuantity); // Update the product's stock after processing waitlist
    }

    // Prints the current waitlist
    public void printWaitlist() {
        if (waitlistQueue.isEmpty()) {
            System.out.println("The waitlist is currently empty.");
        } else {
            System.out.println("Current waitlist:");
            for (WaitlistClient client : waitlistQueue) {
                System.out.println("Client ID: " + client.getClientId() + ", Requested Quantity: " + client.getQuantity());
            }
        }
    }
}
