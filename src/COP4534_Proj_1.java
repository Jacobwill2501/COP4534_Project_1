import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

/*
Name: Jacob Williams
Section: COP4534 U01 1211
Term: Spring 2021
Panther ID: 6182345
*/
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

        String outputFilename = "COP4534_Proj_1_blocks_large.csv";
        PrintWriter output = null;

        try {
            output = new PrintWriter(new FileWriter(outputFilename));
        } catch (IOException ex) {
            System.exit(1);
        }

        // setting the value we will count in the different algorithms
        int x = rnd.nextInt(10);

        // Titles for each column in the csv file
        output.println("Value of N" + "," + "Comparisons of O(n)" + "," + "Comparisons of O(M + log n)" + ","
                + "Comparisons of O(log n)");

        System.out.println("Large blocks is starting");

        for (int i = 0; i < numberOfExcelLines; i++) {
            int[] list = new int[valueOfN]; // initiating array with N length
            fillArrayBlocksLarge(list); // filling the array with random values ranging from 0-10
            Arrays.sort(list); // sorting the array

            // O(n)
            findIntegerCount1(list, x);
            int countOf1 = comparisonCount;

            // O(M + log n)
            findIntegerCount2(list, x);
            int countOf2 = comparisonCount;

            // O(log n)
            findIntegerCount3(list, x);
            int countOf3 = comparisonCount;

            // putting values in the csv file
            output.println(valueOfN + "," + countOf1 + "," + countOf2 + "," + countOf3);

            if(i == 2500){
                System.out.println("Large blocks is 25% complete");
            } else if(i == 5000){
                System.out.println("Large blocks is 50% complete");
            }else if(i == 7500){
                System.out.println("Large blocks is 75% complete");
            }

            // increasing the length of array for next run
            valueOfN += 100;
        }


        output.close();
        System.out.println("Large blocks sheet has finished");
        System.out.println("------------------------------------\n");

        // -----------------------------------------------------------------------------------
        // Changing to Short Blocks
        // -----------------------------------------------------------------------------------
        valueOfN = 100;
        outputFilename = "COP4534_Proj_1_blocks_short.csv";
        PrintWriter output2 = null;

        try {
            output2 = new PrintWriter(new FileWriter(outputFilename));
        } catch (IOException ex) {
            System.exit(1);
        }

        // setting the value we will count in the different algorithms
        x = rnd.nextInt(100);

        // Titles for each column in the csv file
        output2.println("Value of N" + "," + " Comparisons of O(n)" + "," + " Comparisons of O(M + log n)" + ","
                + " Comparisons of O(log n)");

        System.out.println("Small blocks is starting");

        for (int i = 0; i < numberOfExcelLines; i++) {
            int[] list = new int[valueOfN]; // initiating array with N length
            fillArrayBlocksShort(list); // filling the array with random values ranging from 0-10
            Arrays.sort(list); // sorting the array

            // O(n)
            findIntegerCount1(list, x);
            int countOf1 = comparisonCount;

            // O(M + log n)
            findIntegerCount2(list, x);
            int countOf2 = comparisonCount;

            // O(log n)
            findIntegerCount3(list, x);
            int countOf3 = comparisonCount;

            // putting values in the csv file
            output2.println(valueOfN + "," + countOf1 + "," + countOf2 + "," + countOf3);

            if(i == 2500){
                System.out.println("Small blocks is 25% complete");
            } else if(i == 5000){
                System.out.println("Small blocks is 50% complete");
            }else if(i == 7500){
                System.out.println("Small blocks is 75% complete");
            }

            // increasing the length of array for next run
            valueOfN += 100;
        }

        output2.close();
        System.out.println("Small blocks sheet has finished");
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

    private void fillArrayBlocksLarge(int[] list) {
        Random rnd = new Random();
        for (int i = 0; i < list.length; i++) {
            list[i] = rnd.nextInt(10);
        }
    }

    private void fillArrayBlocksShort(int[] list) {
        Random rnd = new Random();
        for (int i = 0; i < list.length; i++) {
            list[i] = rnd.nextInt(100);
        }
    }

    // O(n) run time
    private int findIntegerCount1(int[] list, int k) {
        comparisonCount = 0; // resetting to 0 for new count
        int numberOfAppearances = 0;
        for (int i = 0; i < list.length; i++) {
            comparisonCount++;

            // Once the element at index i == value, count up
            if (list[i] == k) {
                numberOfAppearances++;
            } else if (list[i] > k) { // once the elements are greater than k, array is sorted so break the loop
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
