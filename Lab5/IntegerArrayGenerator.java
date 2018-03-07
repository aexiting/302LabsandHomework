import java.util.Random;

class IntegerArrayGenerator {

    static Random rand = new Random();

    static int[] GeneratePermutedIntegerArray(int numberofIntegers) {
        int[] integerArray = GenerateIntegerArray(numberofIntegers);
        RandomlyPermute(integerArray);
        return integerArray;
    }

    static void RandomlyPermute(int[] integerArray) {
        for (int i = 0; i < integerArray.length; i++) {
            int randInt = rand.nextInt(integerArray.length-i) + i;
            int tmp = integerArray[i];
            integerArray[i] = integerArray[randInt];
            integerArray[randInt] = tmp;
        }
    }

    static int[] GenerateIntegerArray(int n)
    {
        int[] integerArray = new int[n];

        for (int i = 0; i < n; i++) {
            integerArray[i] = i;
        }

        return integerArray;
    }
};
