
interface TimedAlgorithm {

    public abstract void  Run(int[] integerArray);
    public abstract int[] GetBestCaseInput(int arraySize);
    public abstract int[] GetAverageCaseInput(int arraySize);
    public abstract int[] GetWorstCaseInput(int arraySize);
    public abstract String GetName();
}
