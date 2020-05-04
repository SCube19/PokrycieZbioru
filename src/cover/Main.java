package cover;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {

        DataWrapper data = new DataWrapper();
        Scanner scanner = new Scanner(System.in);

	    while(Interactive.read(data, scanner))
        {
            //data.getFamily().writeSets();
            //System.out.println("------------------------------");
            //System.out.println("TargetSet is: " + data.getTargetSet().toString());
           // if(data.getSolution() != null)
                Interactive.printSets(data.getSolution().solve(data.getTargetSet(), data.getFamily()));
            //else
               // System.out.println("Upsi");
        }

	    return;
    }
}
