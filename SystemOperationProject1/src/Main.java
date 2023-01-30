
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.print("Chefs Number: ");
        int numberOfchefs = input.nextInt();
        String OrdersfileName = "Orders.txt";
        
        List<Chef> chefs = createChefs(numberOfchefs);
        Cashier cashier = new Cashier(chefs, OrdersfileName);
        cashier.start();
    }

    public static List<Chef> createChefs(int numberOfchefs) throws FileNotFoundException, IOException {
        Random random = new Random();
        RandomAccessFile namesFile = new RandomAccessFile("Names.txt", "r");
        List<Chef> chefs = new ArrayList<>();
        for (int i = 0; i < numberOfchefs; i++) {
            int indexName = random.nextInt(1195);
            for (int j = 0; j <= indexName; j++) {
                String name = "\"" + namesFile.readLine().trim() + "\"";
                if(j == indexName){
                    chefs.add(new Chef(name));
                    namesFile.seek(0);
                    break;
                }
            }
        }
        namesFile.close();
        return chefs;
    }
}
