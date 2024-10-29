public class WaitlistClient {
    private String clientId;
    private int quantity;

    public WaitlistClient(String clientId, int quantity) {
        this.clientId = clientId;
        this.quantity = quantity;
    }

    public String getClientId() {
        return clientId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
