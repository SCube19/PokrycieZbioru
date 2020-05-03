package cover;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args)
    {

        DataWrapper data = new DataWrapper();

	    while(Interactive.read(data))
        {
            data.getFamily().writeSets();
            System.out.println("------------------------------");
            System.out.println("TargetSet is: " + data.getTargetSet().toString());
            if(data.getSolution() != null)
                Interactive.printSets(data.getSolution().solve(data.getTargetSet(), data.getFamily()));
            else
                System.out.println("Upsi");
        }

	    return;
    }
}
