/*  COSC 302 Spring 2018, Lab 2: Asymptotics

    Name:
    --

    Collaborators:
    --

*/

class AsymptoticsLab2 {

    AsymptoticsLab2() {
    }

    void TimeLinearSearch() {
        LinearSearch linearSearch = new LinearSearch();
        boolean includeAverageCase = false;
        CaseTimer.Time(linearSearch, includeAverageCase);
    }

    void TimeInsertionSort() {
        InsertionSort insertionSort = new InsertionSort();
        boolean includeAverageCase = true;
        CaseTimer.Time(insertionSort, includeAverageCase);
    }

    void TimeAsymptoticMethods() {
        AsymptoticMethods methods = new AsymptoticMethods();
        AsymptoticTimer.Time(methods);
    }

    public static void main(String[] args)
    {
        AsymptoticsLab2 lab2 = new AsymptoticsLab2();
        if (args.length == 1) {
            if (args[0].equals("--linear-search-timing")) {
                lab2.TimeLinearSearch();
            } else if (args[0].equals("--insertion-sort-timing")) {
                lab2.TimeInsertionSort();
            } else if (args[0].equals("--methods-timing")) {
                lab2.TimeAsymptoticMethods();
            } else {
                System.out.print("Unrecognized command line argument: ");
                System.out.println(args[0]);
            }
        } else {
            System.out.println("usage: java AsymptoticsLab2 (--linear-search-timing | --insertion-sort-timing | --function-timing)");
        }
    }

};
