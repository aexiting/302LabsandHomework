/*  Lab 10: Special Delivery

    Name:

    Collaborators:

*/

// file reading
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Exception;
import java.util.ArrayList;


class Delivery {
    public ArrayList<Integer> packageDistances;
    public ArrayList<Integer> packageWeights;
    public Delivery(ArrayList<Integer> packageDistances, ArrayList<Integer> packageWeights)
    {
        this.packageDistances = packageDistances;
        this.packageWeights   = packageWeights;
    }

    // Compute the order to deliver packages in. You are welcome to add whichever arguments you need.
	public void ComputeDeliveryOrder(ArrayList<Integer> bestDeliveryOrder, boolean pruning)
    {
      // update if better than best delivery order

      BruteForce(bestDeliveryOrder,new ArrayList<Integer>(packageWeights),new ArrayList<Integer>(packageWeights),0,-1,pruning);

	}
  public void BruteForce(ArrayList<Integer> bestDeliveryOrder, ArrayList<Integer> currentsolution,ArrayList<Integer> candidates,int currentwork, int bestwork,boolean pruning)
    {
      // update if better than best delivery order
      if(candidates.isEmpty() && currentwork < bestwork || bestwork == -1){
          bestDeliveryOrder = currentsolution;
          bestwork = currentwork;
          return;
      }
      //prune
      if (pruning) {
          boolean prune = false;
          if (prune) return;
      }
      ArrayList<Integer> currentDeliveryOrder = new ArrayList<Integer>();
      // evaluate each candidate; add it to the current solution and make a recursive call
      int location = 0;
      System.out.println(candidates.size());
      for(int i = 0; i < candidates.size(); i++){
        currentDeliveryOrder.add(i);
        candidates.remove(i);

        int weightforrun = 0;
        for(int weight: candidates){
          weightforrun += weight;
        }
        currentwork += (weightforrun*(packageDistances.get(i)+i-location))+packageDistances.get(i)*weightforrun-packageWeights.get(i);
        location = i;
        BruteForce(bestDeliveryOrder,currentsolution, candidates, bestwork, currentwork,pruning);
        currentDeliveryOrder.remove(i);
      }
	}
}
class DeliveryLab10 {

	public static Delivery Read(String filename)
	{
        ArrayList<Integer> packageDistances = new ArrayList<Integer>();
        ArrayList<Integer> packageWeights = new ArrayList<Integer>();

		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;

			// First read the number of bananas, and the number of apes
			while ((line = bufferedReader.readLine()) != null) {
				String[] parts = line.split(" ");
				packageDistances.add(Integer.parseInt(parts[0]));
				packageWeights.add(Integer.parseInt(parts[1]));
			}

            Delivery delivery = new Delivery(packageDistances, packageWeights);
            return delivery;

		} catch(Exception name) {
			System.out.println("Exception (" + name.toString() + ") caught while reading file: " + filename);
		}

		throw new RuntimeException("Unable to continue, file reading failed.");

	}

	static void PrintFinalOrder(ArrayList<Integer> finalOrdering) {

		if (finalOrdering.size() > 0) {
			System.out.print(finalOrdering.get(0));
		}

		for (int i = 1; i < finalOrdering.size(); ++i) {
			System.out.print(" " + finalOrdering.get(i));
		}

		System.out.println();
	}


	public static void main(String args[])
	{
		String filename = "";
		boolean pruning = false;
		if (args.length > 0) {
			for (int i = 0; i < args.length; ++i) {
				if (args[i].startsWith("--file=")) {
					filename = args[i].replace("--file=","");
                } else if (args[i].startsWith("--prune")) {
                    pruning = true;
				} else {
					System.out.print("Unrecognized command: ");
					System.out.println(args[i]);
					System.out.println("Exiting...");
					return;
				}
			}
		}

        Delivery delivery = Read(filename);

        ArrayList<Integer> finalOrdering = new ArrayList<Integer>(delivery.packageDistances.size());
        for (int i = 0; i < delivery.packageDistances.size(); ++i) {
            finalOrdering.add(0);
        }

        System.out.print("Running time");
        if (pruning) {
            System.out.print(" (with    pruning): ");
        } else {
            System.out.print(" (without pruning): ");
        }

        long startMillis = System.currentTimeMillis();
        delivery.ComputeDeliveryOrder(finalOrdering, pruning);
        long totalMillis = (System.currentTimeMillis() - startMillis);
        System.out.printf("%.4fs%n", totalMillis/1000.0);

		PrintFinalOrder(finalOrdering);

	} // main

} // DeliveryLab10
