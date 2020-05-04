package cover;

import java.util.ArrayList;

public class Precise extends Solution
{
    public ArrayList<Integer> solve(FiniteSet target, SetFamily family)
    {

        ArrayList<ArrayList<Integer>> common = determineCommonElements(target, family); //algorytm dziala na zbiorach bedacych przecieciami target i zbiorow z family

        int[] chosenSets = new int[family.getSets().size()]; //tablica wyjsciowa
        boolean[] covered = new boolean[target.getLimit()]; //oznaczenie ktore zbiory sa pokryte
        int[] recursionTrack = new int[family.getSets().size()]; //tablica trzymajaca aktualna sciezke na drzewie rekurencji

        int[] globalMin = new int[1]; //aktualne minimum, wysylane referencja
        globalMin[0] = Integer.MAX_VALUE;

        for(int i = 0; i < common.size(); i++)
        {
            recursionTrack[0] = i + 1;
            recursiveSolve(common, i, 0, covered, chosenSets, recursionTrack, globalMin);
        }

        ArrayList<Integer> rArray = new ArrayList<Integer>();

        if(globalMin[0] != Integer.MAX_VALUE)
            for(int i = 0; i < globalMin[0] + 1; i++) //konwerjsa tablicy na odpowiednia arrayliste
                rArray.add(chosenSets[i]);
        else
            rArray.add(0);

        return rArray;
    }

    private void recursiveSolve(ArrayList<ArrayList<Integer>> common, int setNum, int depth, boolean[] covered, int[] chosenSets, int[] recursionTrack, int[] globalMin)
    {
        if(setNum >= common.size() || depth >= common.size() || depth >= globalMin[0]) //outOfBounds | nie sprawdzamy kombinacji/galezi dajacej zawsze gorszy wynik od minimum
            return;

        if(common.get(setNum).size() == 0)// omijamy zbiory puste
            recursiveSolve(common, setNum + 1, depth, covered, chosenSets, recursionTrack, globalMin);

        recursionTrack[depth] = setNum + 1; //oznaczamy krok na sciezce

        ArrayList<Integer> alreadyCovered = coverUncoveredCommon(covered, common.get(setNum)); //zaznaczamy odpowiednie elementy zbioru docelowego | zapamietujemy ktore byly juz zaznaczone

        if(fullyCovered(covered))
        {
            globalMin[0] = depth;
            System.arraycopy(recursionTrack, 0, chosenSets, 0, depth + 1);

            uncoverRecent(covered, common.get(setNum), alreadyCovered); //cofamy  pokrycie wprowadzone na tej glebokosci
            return;
        }
        else if(setNum == common.size() - 1) //nie udalo sie pokryc zbioru ta kombinacja
        {
            uncoverRecent(covered, common.get(setNum), alreadyCovered); //cofamy
            return;
        }

        for(int i = setNum + 1; i < common.size(); i++)
             recursiveSolve(common, i, depth + 1, covered, chosenSets, recursionTrack, globalMin); //rekurencja pozwalajaca wybrac kazda kombinacje zbiorow

        uncoverRecent(covered, common.get(setNum), alreadyCovered); //cofamy pokrycie
        return;
    }

    private ArrayList<ArrayList<Integer>> determineCommonElements(FiniteSet target, SetFamily family)
    {
        ArrayList<ArrayList<Integer>> commonElements = new ArrayList<ArrayList<Integer>>();

        for(int i = 0; i < family.getSets().size(); i++)
           commonElements.add(family.getSets().get(i).commonElements(target));

        return commonElements;
    }

    private  ArrayList<Integer> coverUncoveredCommon(boolean[] covered, ArrayList<Integer> common)
    {
        ArrayList<Integer> alreadyCovered = new ArrayList<Integer>();

        for(Integer x: common)
            if(!covered[x - 1]) //pokrywamy
                covered[x - 1] = true;
            else //dodajemy do listy juz wczesniej pokrytych
                alreadyCovered.add(x);

        return alreadyCovered;
    }

    private void uncoverRecent(boolean[] covered, ArrayList<Integer> common, ArrayList<Integer> wereCovered)
    {
        int i = 0, j = 0;

        while(i < common.size())
        {
            if(j < wereCovered.size())
            {
                if(wereCovered.get(j) == common.get(i)) //byl pokryty wczesniej
                {
                    i++;
                    j++;
                }
                else
                {
                    covered[common.get(i) - 1] = false;
                    i++;
                }

            }
            else //skonczyly sie elementy wereCovered
            {
                covered[common.get(i) - 1] = false;
                i++;
            }

        }
    }

    private boolean fullyCovered(boolean[] covered)
    {
        for(boolean x: covered)
            if(!x)
                return false;
        return true;
    }

}
