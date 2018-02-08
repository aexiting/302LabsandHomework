
import java.util.Arrays;
class Lab3 {

    public void RunTestSuite() {
      int[] arrayToSort = {3,2,1};
      MergeSortLab3 mergeSortLab3 = new MergeSortLab3();
      mergeSortLab3.RecursiveMergeSortWithAllocation(arrayToSort, 0, arrayToSort.length-1);
      System.out.println(Arrays.toString(arrayToSort));
    }

    public void RunTimeSuite() {
        System.out.printf("Timing mergesorts...%n");
        System.out.printf("%15s %8s %8s %8s%n", "n", "Aloc.", "NoAloc.", "NoRec.");

        for (int size = 1; size > 0 && size < 2000000000; size=size + (int)((size+1)*0.5)) {
            MergeSortLab3 mergeSortLab3 = new MergeSortLab3();
            int[] arrayToSort = new int[size];

            System.out.printf("%15d", size);

            long startTimeMillis = System.currentTimeMillis();
            mergeSortLab3.RecursiveMergeSortWithAllocation(arrayToSort, 0, arrayToSort.length-1);
            System.out.printf(" %8.2f", (System.currentTimeMillis() - startTimeMillis)/1.0);

            startTimeMillis = System.currentTimeMillis();
            mergeSortLab3.RecursiveMergeSortWithoutAllocation(arrayToSort, 0, arrayToSort.length-1);
            System.out.printf(" %8.2f", (System.currentTimeMillis() - startTimeMillis)/1.0);

            startTimeMillis = System.currentTimeMillis();
            mergeSortLab3.NonRecursiveMergeSortWithoutAllocation(arrayToSort);
            System.out.printf(" %8.2f", (System.currentTimeMillis() - startTimeMillis)/1.0);

            System.out.println();
        }

    }

    public static void main(String args[]) {

        Lab3 lab3 = new Lab3();
        if (args[0].equals("--test")) {
            lab3.RunTestSuite();
        } else if (args[0].equals("--time")) {
            lab3.RunTimeSuite();
        } else {
            System.out.println("Unrecognized command line argument: " + args[0]);
        }
    }
};
