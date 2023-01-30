
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FirstFitAlgorithm {

    private int ramSize;
    private List<String> processInfo;
    private String[] ram;
    private int numberOfCompaction;

    public FirstFitAlgorithm() {
        this.processInfo = new ArrayList<>();
        this.numberOfCompaction = 0;
    }

    public void run() {
        input();
        ram = new String[ramSize];
        for (String str : processInfo) {
            String[] info = str.split(" ");
            if (info[0].equals("REQUEST")) {
                request(info[1], Integer.valueOf(info[2]));
            } else if (info[0].equals("RELEASE")) {
                release(info[1]);
            }
        }
        output();
    }

    private void request(String process, int space) {
        int baseIndex = findFirstEmptySpace(space);
        if (baseIndex != -1) {
            for (int i = baseIndex; i < baseIndex + space; i++) {
                ram[i] = process;
            }
        } else {
            compaction();
            numberOfCompaction++;
            request(process, space);
        }
    }

    private void release(String process) {
        for (int i = 0; i < ramSize; i++) {
            if (ram[i] != null && ram[i].equals(process)) {
                ram[i] = null;
            }
        }
    }

    /*
    Apply compaction
     */
    private void compaction() {
        String[] ram2 = new String[ramSize];
        int index = 0;
        for (int i = 0; i < ramSize; i++) {
            if (ram[i] != null) {
                ram2[index] = ram[i];
                index++;
            }
        }
        ram = ram2;
    }

    /*
    Find the first suitable space for the desired process
     */
    private int findFirstEmptySpace(int space) {
        int count = 0;
        for (int i = 0; i < ramSize; i++) {
            if (ram[i] == null) {
                count++;
                if (count == space) {
                    return (i - count + 1);
                }
            } else {
                count = 0;
            }
        }
        return -1;
    }
    
    //----------------------------------------------------------------------------------------------
    
    private void input() {
        Scanner cin = new Scanner(System.in);
        ramSize = Integer.valueOf(cin.nextLine().trim());
        while (cin.hasNextLine()) {
            String line = cin.nextLine();
            if (line.length() != 0) {
                processInfo.add(line);
            } else {
                break;
            }
        }
    }

    /*
    Displays the status of the RAM array in the output
    ram = {P1 P1 P1 null null P3 P3 P3 P3 P3 null null null}
    output : P1 2 P3 3
     */
    private void output() {
        String firstOutput = "";
        System.out.println(numberOfCompaction);
        for (String info : ram) {
            if (info == null) {
                firstOutput += "null ";
            } else {
                firstOutput += info + " ";
            }
        }
        //System.out.println(firstOutput);

        String[] output = firstOutput.split(" ");
        String p = output[0];
        String s = p + " ";
        for (int i = 1; i < output.length; i++) {
            if (!p.equals(output[i])) {
                p = output[i];
                s += p + " ";
            } else if (p.equals("null")) {
                s += p + " ";
            }
        }

        String o2[] = s.split(" ");
        int count = 0;
        String finalOutput = "";
        int i = 0;
        for (String str : o2) {
            if (str.equals("null")) {
                count++;
                if (i == o2.length - 1) {
                    finalOutput += String.valueOf(count);
                }
            } else {
                if (count != 0) {
                    finalOutput += String.valueOf(count) + " " + str + " ";
                    count = 0;
                } else {
                    finalOutput += str + " ";
                }
            }
            i++;
        }
        System.out.println(finalOutput.trim());

    }

//    public static void main(String[] args) {
//        FirstFitAlgorithm test = new FirstFitAlgorithm();
//        test.run();
//    }
}
