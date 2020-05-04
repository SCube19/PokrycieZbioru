package cover;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {

        DataWrapper data = new DataWrapper();
        Scanner scanner = new Scanner(System.in);

	    while(Interactive.read(data, scanner))
	        Interactive.printSets(data.getSolution().solve(data.getTargetSet(), data.getFamily()));



	    return;
    }
}
