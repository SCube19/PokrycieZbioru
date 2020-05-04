package cover;
import java.util.ArrayList;
import java.util.Collections;

public class Greedy extends Solution
{
    public ArrayList<Integer> solve(FiniteSet target, SetFamily family)
    {
        System.out.println("Doing naive solution");
        boolean[] covered = new boolean[target.getLimit()];
        ArrayList<Integer> chosenSets = new ArrayList<Integer>();
        ArrayList<Integer> excludedSets = new ArrayList<Integer>();

        for(SetFamily.SetInFamily x: family.getSets())
        {
            boolean found = false;
            for(int i = 0; i < covered.length && !found; i++)
                if(x.checkForExistence(target.getElement(i)))
                    found = true;

            if(!found)
                excludedSets.add(x.getIndex());

        }

        boolean noProgress;
        do
        {
            noProgress = true;
            int max = 0;
            int max_set = 1;

            for (SetFamily.SetInFamily x : family.getSets())
            {
                int curr = 0;
                if(!exclued(excludedSets, x))
                {
                    for (int i = 0; i < covered.length; i++) {
                        if (!covered[i])
                            if (x.checkForExistence(target.getElement(i))) {
                                curr++;
                                covered[i] = true;
                            }
                    }
                    if (curr > max) {
                        max = curr;
                        max_set = x.getIndex();
                    }
                }
                if(curr != 0)
                    noProgress = false;

            }

            System.out.println("Max set is: " + max_set);
            chosenSets.add(max_set);
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

    private boolean exclued(ArrayList<Integer> excluded, SetFamily.SetInFamily set)
    {
        for(Integer x: excluded)
            if(set.getIndex() == x)
                return true;
        return false;

    }
}
