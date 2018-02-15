/*  COSC 302 Spring 2018, Lab 4: Selection

Name:
--

Collaborators:
--

*/

import java.util.Random;
import java.util.Arrays;

class SelectLab4 {

  static Random rand = new Random();

  public enum Partitioner {
    RANDOM, BAD, LINEAR
  }

  // choose x = A[r] and partition array A
  // so that all values < x come before x,
  // all values > x come after x.
  // return q such that x = A[q]
  //note a and b are indices
  private static void swap(int A[], int a, int b){
    int temp = A[a];
    A[a] = A[b];
    A[b] = temp;

  }
  static int Partition(int A[], int p, int r)
  {
    int q = -1;
    int j= p;
    int i = p -1 ;
    // TODO: Choose x = A[r]. Swap elements so they are in order
    // first elements < x, then x, then elements > x
    for(j = p; j < r; j++){
      if(A[j]<A[r]){
        i++;
        swap(A,j,i);
      }
    }
    q = i + 1;
    swap(A,r,q);

    return q;
  }

  // take a random element x = A[i] and swap it with A[r]
  static int RandomPartition(int A[], int p, int r)
  {
    // a random index between p and r (inclusive)
    int i = p + rand.nextInt(r+1-p);

    // TODO: swap A[i] with A[r]
    swap(A,i,r);
    return Partition(A,p,r);
  }

  // Compute the median of medians and swap it with A[r]
  static int LinearPartition(int[] A, int p, int r)
  {
    // base case, subarray is small, sort and return median
    if (r - p < 20) {
      Arrays.sort(A, p, r+1);
      return A[(p + r)/2];
    }

    int groupSize = 5;
    int numFullGroups = (p - p + 1)/groupSize;


    // TODO: compute medians of groups of 5 and swap to beginning of A[p..r]
    //
    // Recommendation: sort sequences of 5 with insertion sort
    // or use Arrays.sort(A, start, end+1)


    // TODO: compute median of medians with recursive call on beginning of subarray


    // TODO: compute index of median of medians in subarray


    // TODO: swap median of medians with A[r]

    return Partition(A,p,r);
  }
  static int getMax(int[] A){
    int max = 0;
    for(int i = 1; i < A.length;i++){
      if(A[max]<A[i]){
        max = i;
      }
    }
    return max;
  }
  // compute the max element x and swap it with A[r]
  static int BadPartition(int[] A, int p, int r)
  {
    int x = getMax(A);

    // TODO: select x to be the maximum element in A[p..r] and swap with A[r]
    swap(A,x,r);
    return Partition(A,p,r);
  }

  // Returns the i-th smallest element in array A,
  // between p and r. That is, if A were sorted,
  // this function would return A[a+i]

  // This algorithm runs in O(n) expected time, where
  // n is the number of integers in A.
  static int Select(int[] A, int p, int r, int i, Partitioner partitioner)
  {
    int q = -1;
    if (partitioner == Partitioner.BAD) {
      q = BadPartition(A, p, r);
    } else if (partitioner == Partitioner.RANDOM) {
      q = RandomPartition(A, p, r);
    } else { // LINEAR
      q = LinearPartition(A, p, r);
    }
    q = r;
    if(q == i)
    return A[q];
    else if( q > i) return Select(A,p,q-1,i,partitioner);
    else return Select(A,q+1,p,i,partitioner);

  }


  ////////////////////////////////
  ////    Helper Functions    ////
  ////////////////////////////////

  static String PassOrFailString(boolean passed) {
    return passed ? "PASSED" : "FAILED";
  }

  static String PartitionerToString(Partitioner partitioner) {
    switch (partitioner) {
      case RANDOM:
      return "RANDOM";
      case BAD:
      return "BAD";
      case LINEAR:
      return "LINEAR";
      default:
      return "UNKNOWN";
    }

  }

  //////////////////////////
  ////    Unit Tests    ////
  //////////////////////////


  // Check that correct result is returned on random array, for specified partitioner
  static void RunRandomSelectionTest(Partitioner partitioner)
  {
    int[] integerArray     = IntegerArrayGenerator.GeneratePermutedIntegerArray(3);
    int   ithSmallestIndex = rand.nextInt(integerArray.length);
    int   expectedInteger  = ithSmallestIndex;
    int   selectedInteger  = Select(integerArray, 0, integerArray.length-1, ithSmallestIndex, partitioner);

    boolean passed = (expectedInteger == selectedInteger);
    System.out.println("Random selection test (" + PartitionerToString(partitioner) + "): " + PassOrFailString(passed));
  }

  // Check that partitioners return expected results on random arrays
  static void RunRandomSelectionTest()
  {
    RunRandomSelectionTest(Partitioner.RANDOM);
    RunRandomSelectionTest(Partitioner.BAD);
    RunRandomSelectionTest(Partitioner.LINEAR);
  }

  // Check that a subarray integerArray[startIndex..endIndex] is correctly partitioned around value pivot
  static boolean IsPartitionedAroundPivot(int[] integerArray, int startIndex, int endIndex, int pivotElement)
  {

    // TODO: Implement helper to check if a subarray is partitioned around pivot element
    boolean greaterthanpivot = false;
    for(int i =startIndex; i < endIndex; i++){
      if(integerArray[i]> pivotElement){
        greaterthanpivot = true;
      }
      if(greaterthanpivot && integerArray[i] < pivotElement){
        return false;
      }
    }
    return true;
  }

  static void RunPartitionTest() {
    int pivot = 3;
    int startIndex = 0;
    int[] integerArray = {6,1,4,5,3};
    int endIndex = integerArray.length - 1;
    int q = Partition(integerArray,startIndex,endIndex);
    boolean passed =  IsPartitionedAroundPivot(integerArray, startIndex, endIndex, pivot);
    System.out.println("Partition test: " + PassOrFailString(passed));
  }

  static void RunTestSuite()
  {
    RunPartitionTest();
    RunRandomSelectionTest();
  }

  ////////////////////////////
  ////    Timing Code     ////
  ////////////////////////////

  static void RunSelectOnce(int[] A, Partitioner partitioner)
  {
    int i = rand.nextInt(A.length);
    int ithSmallest = Select(A, 0 , A.length-1, i, partitioner);
  }

  static double SelectionExperiment(int[] A, Partitioner partitioner)
  {
    int numRuns = 5;
    long totalMillis = 0;
    int [] CopyA = new int[A.length];
    for (int i = 0; i < numRuns; ++i)
    {
      System.arraycopy(A, 0, CopyA, 0, A.length);
      long startMillis = System.currentTimeMillis();
      RunSelectOnce(CopyA, partitioner);
      totalMillis += (System.currentTimeMillis() - startMillis);
    }

    return (((float)totalMillis)/numRuns)/1000.0;
  }

  static void RunTimeSelect(boolean noBad)
  {
    System.out.println("Timing Select");
    if (!noBad) {
      System.out.printf("%15s %8s %8s %8s%n", "n", "Random", "Linear", "Bad");
    } else {
      System.out.printf("%15s %8s %8s%n", "n", "Random", "Linear");
    }
    for (int i = 1; i > 0 && i < 200000000; i=i*2) {
      int[] A = IntegerArrayGenerator.GeneratePermutedIntegerArray(i);
      System.out.printf("%15d ", i);
      double timeRandom = SelectionExperiment(A, Partitioner.RANDOM);
      double timeLinear = SelectionExperiment(A, Partitioner.LINEAR);
      System.out.printf("%8.2f %8.2f ", timeRandom, timeLinear);
      if (!noBad) {
        double timeBad = SelectionExperiment(A, Partitioner.BAD);
        System.out.printf("%8.2f%n", timeBad);
      } else {
        System.out.printf("%n");
      }
    }
  }

  public static void main(String[] args)
  {
    boolean runRandomizedSelect = true;

    if (args.length > 0) {
      if (args[0].equals("--test")) {
        runRandomizedSelect = false;
        RunTestSuite();
      } else if (args[0].equals("--time=all")) {
        RunTimeSelect(false /* include bad partition in experiments */);
        runRandomizedSelect = false;
      } else if (args[0].equals("--time=nobad")) {
        RunTimeSelect(true /* exclude bad partition from experiments */);
        runRandomizedSelect = false;
      } else {
        System.out.print("Unrecognized command: ");
        System.out.println(args[0]);
      }
    } else {
      System.out.println("usage: java SelectLab4.java (--test | --time=all | --time=nobad)");
    }
  }
};
