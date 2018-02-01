import java.lang.Math;
import java.util.Arrays;
import java.util.Math;
class AsymptoticMethods {
    AsymptoticMethods() {
    }
    //checks if inputsize is positive
    private int[] makeArray(inputsize){

    }
    void Constant(int inputSize) {
        if(inputSize > 0){
            return
        }
    }
    /*Uses Quick Sort which finishes half the work every iteration which lead log n */
    void Logarithmic(int inputSize) {
        Array.sort(makeArray(inputsize));
    }

    void Linear(int inputSize) {
    }

    void LinearLog(int inputSize) {
    }

    void Quadratic(int inputSize) {
    }

    void Cubic(int inputSize) {
    }

    void Exponential(int inputSize) {
    }

    void Factorial(int inputSize) {
    }

    void Mystery(int inputSize) {
        if (inputSize <= 1) {
            return;
        }
        int logInputSize = (int) (Math.log(inputSize)/Math.log(2));
        Mystery(logInputSize);
    }
};
