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
            ArrayList<Integer> tmp = new ArrayList<>();
            tmp.add(0);
            return tmp;
        }

        boolean[] covered = new boolean[target.getLimit()];
        Arrays.fill(covered, Boolean.FALSE);

        ArrayList<Integer> chosenSets = new ArrayList<Integer>();
        ArrayList<Integer> excludedSets = new ArrayList<Integer>();

        for(SetFamily.SetInFamily x: family.getSets()) //nie sprawdzamy zbiorow ktore nie posiadaja elementow z target
        {
            boolean found = false;
            int i = 0;

            while(!found && i < target.getLimit())
            {
                if (x.checkForExistence(target.getElement(i)))
                    found = true;
                i++;
            }

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

                if(!excluded(excludedSets, x))
                {
                    for (int i = 0; i < covered.length; i++)
                        if (!covered[i] && x.checkForExistence(target.getElement(i)))
                            curr++;

                    if (curr > max)
                    {
                        max = curr;
                        max_set = x.getIndex();
                    }
                }
                if(curr != 0) //udalo nam sie znalezc zbior w ktorym sa jeszcze niepokryte elementy
                    noProgress = false;

            }

            chosenSets.add(max_set); //wybieramy najwiekszy zbior
            cover(covered, family.getSets().get(max_set - 1)); //pokrywamy elementy tym zbiorem
            excludedSets.add(max_set); //dodajemy ten zbior do wylaczanych

        }while(!fullyCovered(covered) && !noProgress);

        if(noProgress) //skonczylismy petle przez brak mozliwosci pokrycia zbioru
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
