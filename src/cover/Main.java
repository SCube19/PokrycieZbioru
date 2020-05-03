package cover;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class Main {

    public static void main(String[] args)
    {
        SetFamily family = new SetFamily();
        FiniteSet targetSet = null;
        Solution solution = null;

        int[] array = {4,7,5,8,7,6,2,3};
        Interactive.printSets(array);
	    while(Interactive.read(targetSet, family, solution))
        {
            family.writeSets();
            System.out.println("------------------------------");
          //  if(solution != null)
            //    Interactive.printSets(solution.solve(targetSet, family));
        }

	    return;
    }
}
