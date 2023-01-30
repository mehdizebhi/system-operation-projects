
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chef extends Thread {

    private String chefName;
    private List<Order> orders;

    private final long timePrepareEachSkewer = 200;       // millis

    public Chef() {
    }

    public Chef(String chefName) {
        this.chefName = chefName;
        this.orders = new ArrayList<>();
    }

    public Chef(String chefName, List<Order> orders) {
        this.chefName = chefName;
        this.orders = orders;
    }

    public void run() {
        for (Order order : this.orders) {
            prepareOrder(order);
            System.out.println("Chef " + this.chefName + " prepares order of " + order.getCustomerName());
            Customer customer = new Customer(order.getCustomerName(), order);
            customer.start();
        }
        System.out.println("Chef " + this.chefName + " completes his task.");
    }

    private void prepareOrder(Order order) {
        try {
            Thread.sleep(timePrepareEachSkewer * order.getNumberOfSkewers());
            order.setReady(true);
        } catch (InterruptedException ex) {
            System.err.println("Thread-" + this.chefName + " Interrupted Exception");
        }
    }

    /*
    Getter & Setter
     */
    public String getChefName() {
        return chefName;
    }

    public void setChefName(String chefName) {
        this.chefName = chefName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void getOrder(Order order) {
        this.orders.add(order);
    }
}
