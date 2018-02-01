/*  COSC 302 Spring 2018, Lab 1: Perfecting Linear Search

    Name:
    --

    Collaborators:
    --

*/

class LinearSearchLab1 {

    LinearSearchLab1() {
    }
    /*Output: returns  true if queryInteger is in the array. Returns false if the array is empty or if queryInteger is not found in the array*/
    boolean ConductLinearSearch(int[] integerArray, int queryInteger) {
        if(integerArray == null){
            return false;
        }
        boolean foundQueryInteger = false;
        for(int i = 0; i< integerArray.length;i++){
            if(integerArray[i] == queryInteger){
                foundQueryInteger = true;
                return foundQueryInteger;
            }
        }
        return foundQueryInteger;
    }

    void RunTestSuite() {
        //Test 1
        if(!ConductLinearSearch(null, 3)){
            System.out.println("Test 1 passed");
        }
         else{
            System.out.println("Test 1 failed");
        }
        //Test 2
        int[] testArray2 = new int[10];
        testArray2[5] = 99;
        if(ConductLinearSearch(testArray2,99)){
            System.out.println("Test 2 passed");
        }
         else{
            System.out.println("Test 2 failed");
        }
        //Test 3
        int[] testArray3 = new int[10];
        testArray3[5] = 99;
        testArray3[6] = 99;
        if(ConductLinearSearch(testArray3,99)){
            System.out.println("Test 3 passed");
        }
         else{
            System.out.println("Test 3 failed");
        }
        //Test 4
        int[] testArray4 = new int[10];
        testArray4[0] = 99;
        if(ConductLinearSearch(testArray4,99)){
            System.out.println("Test 4 passed");
        }
         else{
            System.out.println("Test 4 failed");
        }
        //Test 5
        int[] testArray5 = new int[10];
        testArray5[9] = 99;
        if(ConductLinearSearch(testArray5,99)){
            System.out.println("Test 5 passed");
        }
         else{
            System.out.println("Test 5 failed");
        }
        //Test 6
        int[] testArray6 = new int[50000];
        testArray6[49990] = 99;
        if(ConductLinearSearch(testArray6,99)){
            System.out.println("Test 6 passed");
        }
        else{
            System.out.println("Test 6 failed");
        }
        //Test 7
        int[] testArray7 = new int[10];
        //99 is not in the array so the function should return false
         if(!ConductLinearSearch(testArray7,99)){
            System.out.println("Test 7 passed");
        }
        else{
            System.out.println("Test 7 failed");
        }
    }

    public static void main(String[] args)
    {
        LinearSearchLab1 lab1 = new LinearSearchLab1();
        lab1.RunTestSuite();
    }

};
