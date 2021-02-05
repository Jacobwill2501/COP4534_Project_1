import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class COP4534_Proj_1 {
    private int comparisonCount;

    public static void main(String[] args) {
        new COP4534_Proj_1();
    }

    public COP4534_Proj_1() {
        Random rnd = new Random();
        int numberOfExcelLines = 10000;
        int valueOfN = 100;
        comparisonCount = 0;

        String outputFilename = "output.csv";
        PrintWriter output = null;

        /*
         * fillArray(list); printArray(list); Arrays.sort(list); printArray(list);
         * 
         * 
         * System.out.println(); System.out.println(); System.out.println(x +
         * " is in the array " + findIntegerCount1(list, x) + " times");
         * System.out.println("Number of comparisons for O(n): " + comparisonCount);
         * System.out.println(); System.out.println(x + " is in the array " +
         * findIntegerCount2(list, x) + " times");
         * System.out.println("Number of comparisons for O(m + log n): " +
         * comparisonCount); System.out.println(); System.out.println();
         * System.out.println();
         */

        try {
            output = new PrintWriter(new FileWriter(outputFilename));
        } catch (IOException ex) {
            System.exit(1);
        }

        int x = rnd.nextInt(10);
        output.println("Value of N" + "," + "Comparisons of O(n)" + "," + "Comparisons of O(M + log n)" + ","
                + "Comparisons of O(log n)");

        for (int i = 0; i < numberOfExcelLines; i++) {
            int[] list = new int[valueOfN];
            fillArray(list);
            Arrays.sort(list);

            findIntegerCount1(list, x);
            int countOf1 = comparisonCount;
            findIntegerCount2(list, x);
            int countOf2 = comparisonCount;
            findIntegerCount3(list, x);
            int countOf3 = comparisonCount;

            output.println(valueOfN + "," + countOf1 + "," + countOf2 + "," + countOf3);
            valueOfN += 100;
        }

        output.close();
    }

    private int binarySearch(int[] list, int k) {
        int start = 0;
        int end = list.length;

        while (start < end) {
            int midpoint = (start + end) / 2;
            comparisonCount++;
            if (list[midpoint] == k) {
                return midpoint;
            } else if (list[midpoint] < k) {
                start = midpoint + 1;
            } else {
                end = midpoint;
            }
        }

        return -1;
    }

    private void printArray(int[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i] + " ");
        }

        System.out.println();
    }

    private void fillArray(int[] list) {
        Random rnd = new Random();
        for (int i = 0; i < list.length; i++) {
            list[i] = rnd.nextInt(10);
        }
    }

    // O(n) run time
    private int findIntegerCount1(int[] list, int k) {
        comparisonCount = 0; // resetting to 0 for new count
        int numberOfAppearances = 0;
        for (int i = 0; i < list.length; i++) {
            comparisonCount++;
            if (list[i] == k) {
                numberOfAppearances++;
            } else if (list[i] > k) {
                break;
            }
        }
        return numberOfAppearances;
    }

    // O(m + log n)
    private int findIntegerCount2(int[] list, int k) {
        comparisonCount = 0; // resetting comparison count to 0 for a new count
        int numberOfAppearances = 1;
        int loc = binarySearch(list, k); // finding loc first
        if (loc == -1) {
            return 0;
        }
        for (int i = loc + 1; i < list.length; i++) { // counting up
            comparisonCount++;
            if (list[i] != k) {
                break;
            }
            numberOfAppearances++;
        }
        for (int i = loc - 1; i > 0; i--) { // counting down
            comparisonCount++;
            if (list[i] != k) {
                break;
            }
            numberOfAppearances++;
        }

        return numberOfAppearances;
    }

    // O(log n)
    private int findIntegerCount3(int[] list, int k) {
        int numberOfAppearances = 0;
        comparisonCount = 0;

        return numberOfAppearances;
    }
}
