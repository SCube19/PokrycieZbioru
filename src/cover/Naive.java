package cover;
import java.awt.*;
import java.util.ArrayList;

public class Naive extends Solution
{
    public ArrayList<Integer> solve(FiniteSet target, SetFamily family)
    {
        if(family.getSets().size() == 0)
        {
            ArrayList<Integer> r = new ArrayList<>();
            r.add(0);
            return  r;
        }
       //System.out.println("Naive solution in progress");
        boolean[] covered = new boolean[target.getLimit()];
        ArrayList<Integer> chosenSets = new ArrayList<Integer>();

        for(SetFamily.SetInFamily x: family.getSets())
        {
            boolean cover = false;

            for(int i = 0; i < covered.length; i++)
            {
                if(!covered[i])
                    if(x.checkForExistence(target.getElement(i)))
                    {
                        covered[i] = true;
                        cover = true;
                    }

            }
            if(cover)
                chosenSets.add(x.getIndex());

        }

        for(boolean x: covered)
            if(!x)
            {   chosenSets.clear();
                chosenSets.add(0);
            }

        return chosenSets;
    }
}
