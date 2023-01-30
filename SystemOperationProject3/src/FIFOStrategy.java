
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FIFOStrategy {

    private int processSize;
    private int ramSize;
    private int pageSize;
    private List<Integer> commandNumber;

    private Integer[] ramStatus;
    private int pointer;

    private int NumFrames;
    private int NumProcessPages;

    private Map<Integer, Integer> pageTable;    // key = page number , value = frame number

    private int pageFaultNum;

    public FIFOStrategy() {
        this.commandNumber = new ArrayList<>();
        this.pageTable = new HashMap<>();
        this.pointer = 0;
        this.pageFaultNum = 0;
    }

    public void run() {
        input();
        creatVoidPageTable();
        for (int instruction : commandNumber) {
            fetchPage(instruction);
        }
        output();
    }

    private void fetchPage(int instructionNumber) {
        int pageNumber = instructionNumber / pageSize;
        if (!isExistPage(pageNumber)) {
            if (ramStatus[pointer] != null) {
                takeOutPage(ramStatus[pointer], pointer);
            }
            ramStatus[pointer] = pageNumber;
            updatePageTable(pageNumber, pointer);
            increasePointer();
        }
    }

    private void increasePointer() {
        if (pointer >= NumFrames - 1) {
            pointer = 0;
        } else {
            pointer++;
        }
    }

    private void takeOutPage(int pageNumber, int frameNumber) {
        ramStatus[frameNumber] = null;
        pageTable.replace(pageNumber, null);
    }

    private void updatePageTable(int pageNumber, int frameNumber) {
        pageTable.replace(pageNumber, frameNumber);
    }

    private boolean isExistPage(int pageNumber) {
        if (pageTable.get(pageNumber) == null) {
            pageFaultNum++;
            return false;
        }
        return true;
    }

    private void creatVoidPageTable() {
        NumFrames = (int) Math.ceil(ramSize * 1.0 / pageSize);
        NumProcessPages = (int) Math.ceil(processSize * 1.0 / pageSize);
        this.ramStatus = new Integer[NumFrames];
        for (int i = 0; i < NumProcessPages; i++) {
            pageTable.put(i, null);
        }
    }

    //--------------------------------------------------------------------------
    private void input() {
        Scanner cin = new Scanner(System.in);

        processSize = Integer.valueOf(cin.nextLine().trim());
        ramSize = Integer.valueOf(cin.nextLine().trim());
        pageSize = Integer.valueOf(cin.nextLine().trim());
        while (cin.hasNextLine()) {
            String line = cin.nextLine();
            if (line.length() != 0) {
                commandNumber.add(Integer.valueOf(line));
            } else {
                break;
            }
        }
    }

    private void output() {
        System.out.println(pageFaultNum);
    }

//    public static void main(String[] args) {
//        FIFOStrategy test = new FIFOStrategy();
//        test.run();
//    }

}
