
public class Customer extends Thread {

    private String customerName;
    private Order order;
    private long checkoutAmount;

    private boolean usedToilet;

    private final long timeEatEachSkewer = 150;    // millis 
    private final long WCTime = 200;              // millis 

    public Customer() {
    }

    public Customer(String customerName, Order order) {
        this.customerName = customerName;
        this.order = order;
        this.checkoutAmount = 0;
        this.usedToilet = false;
    }

    public void run() {
        if (order.isReady()) {
            eatOrder();
            if (order.getNumberOfSkewers() > 10) {
                goToWC();
            }
            checkout();
        }
    }

    private void eatOrder() {
        try {
            Thread.sleep(timeEatEachSkewer * order.getNumberOfSkewers());
            System.out.println(this.customerName + " finished eating food.");
        } catch (InterruptedException ex) {
            System.err.println("Thread-" + this.customerName + " Interrupted Exception");
        }
    }

    private void goToWC() {
        try {
            System.out.println(this.customerName + " goes to WC!");
            Thread.sleep(WCTime);
            usedToilet = true;
        } catch (InterruptedException ex) {
            System.err.println("Thread-" + this.customerName + " Interrupted Exception");
        }
    }

    private void checkout() {
        if (order.getNumberOfSkewers() % 6 == 0) {
            checkoutAmount += 6000 * order.getNumberOfSkewers();
        } else {
            checkoutAmount += 8000 * order.getNumberOfSkewers();
        }
        if (usedToilet == true) {
            checkoutAmount += 5000;
        }
        System.out.println(this.customerName + " " + this.checkoutAmount + " puts on table.");
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getCheckoutAmount() {
        return checkoutAmount;
    }

    public void setCheckoutAmount(long checkoutAmount) {
        this.checkoutAmount = checkoutAmount;
    }

    public boolean isUsedToilet() {
        return usedToilet;
    }

    public void setUsedToilet(boolean usedToilet) {
        this.usedToilet = usedToilet;
    }
}
