
public class Order {

    private String customerName;
    private int numberOfSkewers;
    private boolean ready;

    public Order() {
    }

    public Order(String customerName, int numberOfSkewers) {
        this.customerName = customerName;
        this.numberOfSkewers = numberOfSkewers;
        this.ready = false;
    }

    /*
    Getter & Setter
     */
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getNumberOfSkewers() {
        return numberOfSkewers;
    }

    public void setNumberOfSkewers(int numberOfSkewers) {
        this.numberOfSkewers = numberOfSkewers;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}
