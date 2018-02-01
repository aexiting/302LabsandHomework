
class CaseTimer
{

    CaseTimer() {
    }

    static void Time(TimedAlgorithm algo, boolean includeAvgCase) {

        System.out.printf("Timing %s...%n", algo.GetName());
        System.out.printf("%15s %8s %8s %8s%n", "n", "Best", "Average", "Worst");

        int numIterations = 2;
        for (int size = 1; size > 0 && size < 2000000000; size=size*2) {
            System.out.printf("%15d", size);

            int[] inputArray = algo.GetBestCaseInput(size);
            double bestTimeInSeconds  = MultiRun(algo, inputArray, numIterations);
            System.out.printf(" %8.2f", bestTimeInSeconds);

            inputArray = algo.GetAverageCaseInput(size);
            double avgTimeInSeconds  = MultiRun(algo, inputArray, numIterations);
            System.out.printf(" %8.2f", avgTimeInSeconds);

            inputArray = algo.GetWorstCaseInput(size);
            double worstTimeInSeconds  = MultiRun(algo, inputArray, numIterations);
            System.out.printf(" %8.2f", worstTimeInSeconds);

            System.out.println();
        }
    }

    static double MultiRun(TimedAlgorithm algo, int[] inputArray, int numIterations) {
        long totalTimeMillis = 0;
        int [] arrayCopy = new int[inputArray.length];

        for (int iteration = 0; iteration < numIterations; iteration++) {
            System.arraycopy(inputArray, 0, arrayCopy, 0, inputArray.length);
            long startTimeMillis = System.currentTimeMillis();
            algo.Run(inputArray);
            totalTimeMillis += (System.currentTimeMillis() - startTimeMillis); 
        }

        return (totalTimeMillis/1000.0)/numIterations;
    }

};
