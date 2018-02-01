
class LinearSearch implements TimedAlgorithm {

    LinearSearch() {
    }

    public String GetName() {
        return "LinearSearch";
    }

    public void Run(int[] integerArray) {
        ConductLinearSearch(integerArray, 0);
    }

    public boolean ConductLinearSearch(int[] integerArray, int queryInteger) {
        for (int integerInArray : integerArray) {
            if (integerInArray == queryInteger)
                return true;
        }
        return false;
    }

    public int[] GetBestCaseInput(int arraySize) {
        int[] integerArray = new int[arraySize];
        for(int i = 1; i<arraySize;i++){
          integerArray[i] = 1;
        }
        return integerArray;
    }

    public int[] GetWorstCaseInput(int arraySize) {
        int[] integerArray = new int[arraySize];
        for(int i = 0; i<arraySize;i++){
          integerArray[i] = 1;
        }
        return integerArray;
    }

    // not implemented
    public int[] GetAverageCaseInput(int arraySize) {
        int[] integerArray = new int[arraySize];
         for(int i = 0; i<arraySize;i++){
          integerArray[i] = 1;
        }
        integerArray[arraySize/2] = 0;
        return integerArray;
    }

};
