package cover;
import java.util.ArrayList;

public class Naive extends Solution
{
    public ArrayList<Integer> solve(FiniteSet target, SetFamily family)
    {

        boolean[] covered = new boolean[target.getLimit()]; //oznaczamy elementy juz pokryte
        ArrayList<Integer> chosenSets = new ArrayList<Integer>(); //lista zwracana

        for(SetFamily.SetInFamily x: family.getSets())
        {
            boolean cover = false;

            for(int i = 0; i < covered.length; i++)
            {
                if(!covered[i]) //uznajemy pokryte elementy za nieistniejace
                    if(x.checkForExistence(target.getElement(i))) //pokrywamy niepokryte jeszcze elementy
                    {
                        covered[i] = true;
                        cover = true;
                    }
            }

            if(cover) //uzylismy przynajmniej jednego elementu ze zbioru x
                chosenSets.add(x.getIndex());
        }

        for(boolean x: covered)
            if(!x) // nie udalo sie pokryc zbioru cala rodzina
            {   chosenSets.clear();
                chosenSets.add(0);
                break;
            }

        return chosenSets;
    }
}
