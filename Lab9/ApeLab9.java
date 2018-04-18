/*  Lab 9: There's an Ape for That

    Name:

    Collaborators:

*/

// file reading
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Exception;

class ApeCarry {

	int   bananaLoad;
	int   numApes;
	int[] capacityPerApe;
	int[] snackLossPerApe;

	public ApeCarry(int bananaLoad, int numApes, int[] capacityPerApe, int[] snackLossPerApe) {
		this.bananaLoad      = bananaLoad;
		this.numApes         = numApes;
		this.capacityPerApe  = capacityPerApe;
		this.snackLossPerApe = snackLossPerApe;
	}

	public int[] ComputeCarriers() {

		int apeCarryTable[][] = new int[numApes+1][bananaLoad+1];

		// TODO: Fill in base cases


		// TODO: fill in table


		// 1 if ape carries, 0 if ape does not carry
		int[] carrierArray = new int[numApes+1];


		// TODO: construct carrier array from value table


		return carrierArray;
	}
}

class ApeLab9 {

	public static ApeCarry Read(String filename)
	{

		int bananaLoad = 0;
		int numApes    = 0;

		int[] capacityPerApe;
		int[] snackLossPerApe;


		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;

			// First read the number of bananas, and the number of apes
			if ((line = bufferedReader.readLine()) != null) {
				String[] parts = line.split(" ");
				bananaLoad  = Integer.parseInt(parts[0]);
				numApes = Integer.parseInt(parts[1]);
			}

			capacityPerApe  = new int[numApes+1];
			snackLossPerApe = new int[numApes+1];

			capacityPerApe[0]  = 0;
			snackLossPerApe[0] = 0;

			// Then read each ape's capacity and snack loss (one pair per line) until reaching the end of the file

			int apeId = 1;
			while ((line = bufferedReader.readLine()) != null) {
				String[] parts = line.split(" ");
				int capacity  = Integer.parseInt(parts[0]);
				int snackLoss = Integer.parseInt(parts[1]);

				capacityPerApe[apeId] = capacity;
				snackLossPerApe[apeId] = snackLoss;
				apeId++;
			}
			ApeCarry apeCarry = new ApeCarry(bananaLoad, numApes, capacityPerApe, snackLossPerApe);
			return apeCarry;

		} catch(Exception name) {
			System.out.println("Exception (" + name.toString() + ") caught while reading file: " + filename);
		}

		throw new RuntimeException("Unable to continue, file reading failed.");

	}

	static void PrintCarriers(int[] apeCarryArray) {

		if (apeCarryArray.length > 1) {
			System.out.print(apeCarryArray[1]);
		}

		for (int i = 2; i < apeCarryArray.length; ++i) {
			System.out.print(" " + apeCarryArray[i]);
		}

		System.out.println();
	}


	public static void main(String args[])
	{
		String filename = "";
		boolean verifyOnly = false;
		if (args.length > 0) {
			for (int i = 0; i < args.length; ++i) {
				if (args[i].startsWith("--file=")) {
					filename = args[i].replace("--file=","");
				} else {
					System.out.print("Unrecognized command: ");
					System.out.println(args[i]);
					System.out.println("Exiting...");
					return;
				}
			}
		}

		ApeCarry apeCarry = Read(filename);

		int[] apeCarryArray = apeCarry.ComputeCarriers();

		PrintCarriers(apeCarryArray);

	} // main

} // ApeLab9
