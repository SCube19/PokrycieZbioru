package cover;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Greedy extends Solution
{
    public ArrayList<Integer> solve(FiniteSet target, SetFamily family)
    {
        if(family.getSets().size() == 0)
        {
            ArrayList<Integer> r = new ArrayList<>();
            r.add(0);
            return  r;
        }
       //System.out.println("Doing greedy solution");
        boolean[] covered = new boolean[target.getLimit()];
        Arrays.fill(covered, Boolean.FALSE);

        ArrayList<Integer> chosenSets = new ArrayList<Integer>();
        ArrayList<Integer> excludedSets = new ArrayList<Integer>();

        for(SetFamily.SetInFamily x: family.getSets())
        {
            boolean found = false;
            int i = 0;
           // System.out.println("Limit is: " + target.getLimit());
            while(!found && i < target.getLimit())
            {
                if (x.checkForExistence(target.getElement(i)))
                    found = true;
                i++;
            }

            if(!found)
                excludedSets.add(x.getIndex());

        }

       // String tmpstr = new String();
       // for(Integer x: excludedSets)
         ///   tmpstr += x + "";
      //  System.out.println("Excluded are: " + tmpstr);
        boolean noProgress;
        do
        {
            noProgress = true;
            int max = 0;
            int max_set = 1;

            for (SetFamily.SetInFamily x : family.getSets())
            {
                int curr = 0;
                if(!excluded(excludedSets, x))
                {
                   // System.out.println("We are in set: " + x.getIndex());
                    for (int i = 0; i < covered.length; i++)
                    {
                       // System.out.println("Checking the existence of " + target.getElement(i));
                        if (!covered[i])
                            if (x.checkForExistence(target.getElement(i)))
                            {
                              //  System.out.println("Found");
                                curr++;
                            }
                    }
                    //System.out.println("We found: " + curr);
                    if (curr > max) {
                        max = curr;
                        max_set = x.getIndex();
                    }
                }
                if(curr != 0)
                    noProgress = false;

            }

            //System.out.println("Max set is: " + max_set);
            chosenSets.add(max_set);
            cover(covered, family.getSets().get(max_set - 1));
            excludedSets.add(max_set);

        }while(!fullyCovered(covered) && !noProgress);

        if(noProgress)
        {
            chosenSets.clear();
            chosenSets.add(0);
        }

        Collections.sort(chosenSets);

        return chosenSets;
    }

    private boolean fullyCovered(boolean[] covered)
    {
        for(boolean x: covered)
            if(!x)
                return false;
        return true;
    }

    private boolean excluded(ArrayList<Integer> excluded, SetFamily.SetInFamily set)
    {
        for(Integer x: excluded)
            if(set.getIndex() == x)
                return true;
        return false;

    }

    private void cover(boolean[] covered, SetFamily.SetInFamily set)
    {
        for (int i = 0; i < covered.length; i++)
        {
            if (!covered[i])
                if (set.checkForExistence(i + 1))
                    covered[i] = true;

        }
    }
}
