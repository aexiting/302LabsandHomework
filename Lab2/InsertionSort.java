
class InsertionSort implements TimedAlgorithm {

    InsertionSort() {
    }

    public String GetName() {
        return "InsertionSort";
    }

    public void Run(int[] integerArray) {
        RunInsertionSort(integerArray);
    }

    void RunInsertionSort(int[] integerArray) {
        for (int keyIndex = 1; keyIndex < integerArray.length; keyIndex++) {
            int key = integerArray[keyIndex];
            int iteratingIndex = keyIndex-1;
            while (iteratingIndex >= 0 && key < integerArray[iteratingIndex]) {
                integerArray[iteratingIndex+1] = integerArray[iteratingIndex];
                iteratingIndex--;
            }
            integerArray[iteratingIndex+1] = key;
        }
    }

    public int[] GetBestCaseInput(int arraySize) {
        int[] integerArray= new int[arraySize];
          for(int i = 0; i<arraySize;i++){
          integerArray[i] = i;
        }
        return integerArray;
    }

    public int[] GetWorstCaseInput(int arraySize) {
        int[] integerArray= new int[arraySize];
        for(int i = 0; i<arraySize;i++){
          integerArray[i] = arraySize-i;
        }
        return integerArray;
    }

    public int[] GetAverageCaseInput(int arraySize) {
        return IntegerArrayGenerator.GeneratePermutedIntegerArray(arraySize);
    }
};
