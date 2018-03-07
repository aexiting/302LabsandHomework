/*  COSC 302 Spring 2018, Lab 5: Linear-time Selection

    Name:
    --

    Collaborators:
    --

*/

import java.util.Random;
import java.util.Arrays;

class SelectLab5 {

static Random rand = new Random();

public enum Partitioner {
    RANDOM, BAD, LINEAR
}

static void Swap(int[] A, int index1, int index2) {
	int temp = A[index1];
	A[index1] = A[index2];
	A[index2] = temp;
}

// choose x = A[r] and partition array A
// so that all values < x come before x,
// all values > x come after x.
// return q such that x = A[q]
static int Partition(int A[], int p, int r)
{
    int q = -1;

    // TODO: Choose x = A[r]. Swap elements so they are in order
    // first elements < x, then x, then elements > x

	int x = A[r];

	int endSmallerIndex = p - 1;
	for (int currentIndex = p; currentIndex < r; ++currentIndex) {
		if (A[currentIndex] < x) {
			Swap(A, currentIndex, ++endSmallerIndex);
		} else {

		}
	}

	Swap(A, r, ++endSmallerIndex);

	q = endSmallerIndex;

    return q;
}

// take a random element x = A[i] and swap it with A[r]
static int RandomPartition(int A[], int p, int r)
{
    // a random index between p and r (inclusive)
    int i = p + rand.nextInt(r+1-p);

    // TODO: swap A[i] with A[r]
	Swap(A, i, r);

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
    int numFullGroups = (r - p + 1)/groupSize;
    int temp = 0;

    // TODO: compute medians of groups of 5 and swap to beginning of A[p..r]
    //
    // Recommendation: sort sequences of 5 with insertion sort
    // or use Arrays.sort(A, start, end+1)
    for(int i = 0;i<r;i+=groupSize){
        Arrays.sort(A, i, Math.min(A.length-1,i+groupSize-1));
        temp = A[((i+groupSize)/2)+1];
        A[((i+groupSize)/2)+1] = A[i+groupSize-1];
        A[i+groupSize-1] = temp;
    }


    // TODO: compute median of medians with recursive call on beginning of subarray
    LinearPartition(A,groupSize,r+groupSize-1);

    // TODO: compute index of median of medians in subarray
    for(int i = 0;i<r;i+=groupSize){
        Arrays.sort(A, i, i+groupSize-1);
        temp = A[((i+groupSize)/2)+1];
        A[((i+groupSize)/2)+1] = A[i+groupSize-1];
        A[i+groupSize-1] = temp;
    }
    // TODO: swap median of medians with A[r]

    return Partition(A,p,r);
}

// compute the max element x and swap it with A[r]
static int BadPartition(int[] A, int p, int r)
{
    int x = -1;

    // TODO: select x to be the maximum element in A[p..r] and swap with A[r]

	int maxIndex = p;
	for (int currentIndex = p + 1; currentIndex <= r; currentIndex++) {
		if (A[currentIndex] > A[maxIndex]) {
			maxIndex = currentIndex;
		}
	}

	Swap(A, maxIndex, r);

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

    // TODO: implement recursive Select.
    if (q == i) {
        return A[q];
    } else if (i < q) {
        return Select(A, p, q-1, i, partitioner);
    }

    // q > i
    return Select(A, q+1, r, i, partitioner);
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
    int[] integerArray     = IntegerArrayGenerator.GeneratePermutedIntegerArray(20);
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
	boolean foundPivot = false;
    // TODO: Implement helper to check if a subarray is partitioned around pivot element
	for (int currentIndex = startIndex; currentIndex <= endIndex; ++currentIndex) {
		if (foundPivot && integerArray[currentIndex] == pivotElement) {
			return false;
		} else if (!foundPivot && integerArray[currentIndex] > pivotElement) {
			return false;
		} else if (foundPivot && integerArray[currentIndex] < pivotElement) {
			return false;
		} else if (integerArray[currentIndex] == pivotElement) {
			foundPivot = true;
		}
	}

    return true;
}

static void RunAndCheckPartitionTest(int[] A, int startIndex, int endIndex, String testName) {

	int pivot = A[endIndex];
	int q = Partition(A, startIndex, endIndex);

	boolean passed = true;

	passed = passed && (A[q] == pivot);
	passed = passed && IsPartitionedAroundPivot(A, startIndex, endIndex, pivot);

    System.out.println("Partition test (" + testName + "): " + PassOrFailString(passed));
}

static void MinPartitionTest(int[] A, int startIndex, int endIndex) {

	int minIndex = startIndex;
	for (int currentIndex = startIndex+1; currentIndex <= endIndex; currentIndex++) {
		if (A[minIndex] > A[currentIndex]) {
			minIndex = currentIndex;
		}
	}

	Swap(A, minIndex, endIndex);
	RunAndCheckPartitionTest(A, startIndex, endIndex, "MIN");
}

static void MidPartitionTest(int[] A, int startIndex, int endIndex) {

	int midIndex = (startIndex + endIndex)/2;

	Swap(A, midIndex, endIndex);
	RunAndCheckPartitionTest(A, startIndex, endIndex, "MID");
}

static void MaxPartitionTest(int[] A, int startIndex, int endIndex) {

	int maxIndex = startIndex;
	for (int currentIndex = startIndex+1; currentIndex <= endIndex; currentIndex++) {
		if (A[maxIndex] < A[currentIndex]) {
			maxIndex = currentIndex;
		}
	}

	Swap(A, maxIndex, endIndex);
	RunAndCheckPartitionTest(A, startIndex, endIndex, "MAX");
}

static void RunPartitionTest() {
    // TODO: Implement test for Partition method

    int[] A;
	int[] CopyA  = IntegerArrayGenerator.GeneratePermutedIntegerArray(20);
	int startIndex = 10;
	int endIndex = 17;

	A = Arrays.copyOf(CopyA, CopyA.length);
	MinPartitionTest(A, startIndex, endIndex);

	A = Arrays.copyOf(CopyA, CopyA.length);
	MidPartitionTest(A, startIndex, endIndex);

	A = Arrays.copyOf(CopyA, CopyA.length);
	MaxPartitionTest(A, startIndex, endIndex);

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
        System.out.println("usage: java SelectLab5.java (--test | --time=all | --time=nobad)");
    }
}
};
