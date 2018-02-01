
class AsymptoticTimer {
    AsymptoticTimer() {
    }

    public static void Time(AsymptoticMethods methods) {
        System.out.printf("Timing Methods...%n");
        System.out.printf("%15s %8s %8s %8s %8s %8s %8s %8s %8s %8s%n", "n", "Const.", "LogN", "Linear", "NLogN", "Quad.", "Cube.", "Exp.", "Fact.", "Myst.");

        for (int size = 1; size > 0 && size < 2000000000; size=size*2) {
            System.out.printf("%15d", size);

            long startTimeMillis = System.currentTimeMillis();
            methods.Constant(size);
            System.out.printf(" %8.2f", (System.currentTimeMillis() - startTimeMillis)/1000.0); 

            startTimeMillis = System.currentTimeMillis();
            methods.Logarithmic(size);
            System.out.printf(" %8.2f", (System.currentTimeMillis() - startTimeMillis)/1000.0); 

            startTimeMillis = System.currentTimeMillis();
            methods.Linear(size);
            System.out.printf(" %8.2f", (System.currentTimeMillis() - startTimeMillis)/1000.0); 

            startTimeMillis = System.currentTimeMillis();
            methods.LinearLog(size);
            System.out.printf(" %8.2f", (System.currentTimeMillis() - startTimeMillis)/1000.0); 

            startTimeMillis = System.currentTimeMillis();
            methods.Quadratic(size);
            System.out.printf(" %8.2f", (System.currentTimeMillis() - startTimeMillis)/1000.0); 

            startTimeMillis = System.currentTimeMillis();
            methods.Cubic(size);
            System.out.printf(" %8.2f", (System.currentTimeMillis() - startTimeMillis)/1000.0); 

            startTimeMillis = System.currentTimeMillis();
            methods.Mystery(size);
            System.out.printf(" %8.2f", (System.currentTimeMillis() - startTimeMillis)/1000.0); 

            startTimeMillis = System.currentTimeMillis();
            methods.Exponential(size);
            System.out.printf(" %8.2f", (System.currentTimeMillis() - startTimeMillis)/1000.0); 

            startTimeMillis = System.currentTimeMillis();
            methods.Factorial(size);
            System.out.printf(" %8.2f", (System.currentTimeMillis() - startTimeMillis)/1000.0); 

            System.out.println();
        }
    }

};
