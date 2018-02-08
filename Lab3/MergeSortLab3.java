
import java.util.Arrays;
class MergeSortLab3
{
    int[] globalintegerArray;
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
      for(int i = 0; i <= midIndex; i++){
          globalintegerArray[i] = integerArray[i+startIndex];
      }
      for(int i = 0; i <= endIndex; i++){
          globalintegerArray[i] = integerArray[midIndex+ 1 + i];
      }
      int valueR = midIndex;
      int valueL = startIndex;
      int x = startIndex;
      while(valueR <= endIndex && valueL <= midIndex){ //iterate until you meet the correct index in either the "left" or "right" side
          if(globalintegerArray[valueR] <= globalintegerArray[valueL]){
              integerArray[x] = globalintegerArray[valueR];
              valueR++;
          }
          else {
              integerArray[x] = globalintegerArray[valueL];
              valueL++;
          }
          x++;
      }
      if(valueR == endIndex){
          for(int i = valueL; i <= midIndex; i++){
              integerArray[x] = globalintegerArray[i];
              x++;
          }
      }
       if(valueL == midIndex){
          for(int i = valueR; i <= endIndex; i++){
              integerArray[x] = globalintegerArray[i];
              x++;
          }
      }


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
      globalintegerArray = new int[endIndex+1];
      if(startIndex<endIndex){
      int midIndex =(startIndex+endIndex)/2;
      RecursiveMergeSortWithAllocation(integerArray,startIndex,midIndex);
      RecursiveMergeSortWithAllocation(integerArray,midIndex+1,endIndex);
      MergeWithAllocation(integerArray,startIndex,midIndex,endIndex);
    }
    }

    void NonRecursiveMergeSortWithoutAllocation(int[] integerArray) {
    }
};
