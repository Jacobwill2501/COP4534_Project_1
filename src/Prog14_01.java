import java.util.Arrays;
import java.util.Random;

public class Prog14_01 {
    private int comparisonCount;

    public static void main(String[] args) {
        new Prog14_01();
    }

    public Prog14_01() {
        Random rnd = new Random();
        int[] list = new int[10000];
        // int[] list = { 6, 0, 0, 4, 1, 2, 6, 6 };
        comparisonCount = 0;

        fillArray(list);
        // printArray(list);
        Arrays.sort(list);
        // printArray(list);

        int x = rnd.nextInt(10);
        // int x = 9;
        System.out.println();
        System.out.println();
        System.out.println(x + " is in the array " + findIntegerCount1(list, x) + " times");
        System.out.println("Number of comparisons for O(n): " + comparisonCount);
        System.out.println();
        System.out.println(x + " is in the array " + findIntegerCount2(list, x) + " times");
        System.out.println("Number of comparisons for O(m + log n): " + comparisonCount);
        System.out.println();
        System.out.println();
        System.out.println();
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

        return numberOfAppearances;
    }
}
