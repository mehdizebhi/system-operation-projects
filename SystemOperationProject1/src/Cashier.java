
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cashier extends Thread {

    private String cashierName = "Cashier";
    private List<Chef> chefs;
    private int numberOfOrders;    // numberOfOrders = N
    private List<Order> orders;   // orders.size() == numberOfOrders
    private String ordersFileName;

    public Cashier() {
    }

    public Cashier(List<Chef> chefs, String ordersFile) {
        this.chefs = chefs;
        this.ordersFileName = ordersFile;
        this.orders = new ArrayList<>();
    }

    public void run() {
        try {
            getOrdersFromOrdersFile();
        } catch (FileNotFoundException ex) {
            System.err.println("file not found");
        } catch (IOException ex) {
            System.err.println("IOException");
        }
        divideOrdersBetweenChefs();
        System.out.println("Cashier completes his task.");
    }

    private void assignOrdersToChef(Chef chef) {
        System.out.println("Chef " + chef.getChefName() + " gets orders");
        chef.start();
    }

    /*
    Divide orders between chefs
     */
    private void divideOrdersBetweenChefs() {
        int turn = numberOfOrders % chefs.size() == 0 ? (int) (numberOfOrders / chefs.size()) : ((int) (numberOfOrders / chefs.size())) + 1;
        for (int i = 0; i < turn; i++) {
            for (Chef chef : this.chefs) {
                if (i == turn - 1) {
                    if (orders.size() == 0) {
                        assignOrdersToChef(chef);
                        continue;
                    }
                    chef.getOrder(orders.remove(0));
                    assignOrdersToChef(chef);
                } else {
                    chef.getOrder(orders.remove(0));
                }

            }
        }
    }

    /*
    This method reads the orders from the file and puts them in the orders list
     */
    private void getOrdersFromOrdersFile() throws FileNotFoundException, IOException {
        RandomAccessFile ordersFile = new RandomAccessFile(ordersFileName, "r");
        this.numberOfOrders = Integer.parseInt(ordersFile.readLine().trim());
        String line = ordersFile.readLine();
        while (line != null) {
            String[] data = line.split(" ");
            orders.add(new Order(data[0], Integer.parseInt(data[1])));
            line = ordersFile.readLine();
        }
        ordersFile.close();
    }

    /*
    Getter & Setter
     */
    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public List<Chef> getChefs() {
        return chefs;
    }

    public void setChefs(List<Chef> chefs) {
        this.chefs = chefs;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getOrdersFileName() {
        return ordersFileName;
    }

    public void setOrdersFileName(String ordersFileName) {
        this.ordersFileName = ordersFileName;
    }

}
