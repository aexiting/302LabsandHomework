
import java.util.Arrays;
class MergeSortLab3
{
    MergeSortLab3() {
    }
    void MergeWithAllocation(int[] integerArray, int startIndex, int midIndex, int endIndex) {
      int[] L = new int[midIndex-startIndex +1];
      int[] R = new int[endIndex- midIndex];
      for(int i = 0; i < L.length; i++){
          L[i] = integerArray[i+startIndex];
      }
      for(int i = 0; i < R.length; i++){
          R[i] = integerArray[midIndex+ 1 + i];
      }
      int valueR = 0;
      int valueL = 0;
      int x = startIndex;
      int compare;
      while(valueR < R.length && valueL < L.length){
          if(R[valueR] <= L[valueL]){
              integerArray[x] = R[valueR];
              valueR++;
          }
          else {
              integerArray[x] = L[valueL];
              valueL++;
          }
          x++;
      }
      if(valueR == R.length){
          for(int i = valueL; i < L.length; i++){
              integerArray[x] = L[i];
              x++;
          }
      }
       if(valueL == L.length){
          for(int i = valueR; i < R.length; i++){
              integerArray[x] = R[i];
              x++;
          }
      }


    }

    void MergeWithoutAllocation(int[] integerArray, int startIndex, int midIndex, int endIndex) {
    }

    void RecursiveMergeSortWithAllocation(int[] integerArray, int startIndex, int endIndex) {
      if(startIndex<endIndex){
      int midIndex =(startIndex+endIndex)/2;
      RecursiveMergeSortWithAllocation(integerArray,startIndex,midIndex);
      RecursiveMergeSortWithAllocation(integerArray,midIndex+1,endIndex);
      MergeWithAllocation(integerArray,startIndex,midIndex,endIndex);
    }
    }

    void RecursiveMergeSortWithoutAllocation(int[] integerArray, int startIndex, int endIndex) {
    }

    void NonRecursiveMergeSortWithoutAllocation(int[] integerArray) {
    }
};
