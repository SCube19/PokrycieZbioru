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

        ArrayList<Integer> rArray = new ArrayList<Integer>(); 

        int[] globalMin = new int[1]; //aktualne minimum, wysylane referencja
        globalMin[0] = Integer.MAX_VALUE;

            for (int i = 0; i < common.size(); i++)
            {
                recursionTrack[0] = i + 1;
                recursiveSolve(common, i, 0, covered, chosenSets, recursionTrack, globalMin);
            }

            if(globalMin[0]!=Integer.MAX_VALUE) //znalezlismy jakies kombinacje        
                for (int i = 0; i < globalMin[0] + 1; i++) //konwerjsa tablicy na odpowiednia arrayliste
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

        ArrayList<Integer> toUncover = coverUncoveredCommon(covered, common.get(setNum)); //zaznaczamy odpowiednie elementy zbioru docelowego | zapamietujemy ktore zaznaczylismy

        if(toUncover.size() == 0) //taka galaz nic nam nie daje
            return;

        if(fullyCovered(covered))
        {
            globalMin[0] = depth;
            System.arraycopy(recursionTrack, 0, chosenSets, 0, depth + 1);

            uncoverRecent(covered, toUncover); //cofamy pokrycie wprowadzone na tej glebokosci
            return;
        }
        else if(setNum == common.size() - 1) //nie udalo sie pokryc zbioru ta kombinacja
        {
            uncoverRecent(covered, toUncover); //cofamy
            return;
        }

        for(int i = setNum + 1; i < common.size(); i++)
             recursiveSolve(common, i, depth + 1, covered, chosenSets, recursionTrack, globalMin); //rekurencja pozwalajaca wybrac kazda kombinacje zbiorow

        uncoverRecent(covered, toUncover); //cofamy pokrycie
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
        ArrayList<Integer> toUncover = new ArrayList<Integer>();

        for(Integer x: common)
            if(!covered[x - 1])
            {
                covered[x - 1] = true;
                toUncover.add(x);
            }

        return toUncover;
    }

    private void uncoverRecent(boolean[] covered, ArrayList<Integer> toUncover)
    {
       for(Integer x: toUncover)
           covered[x - 1] = false;
    }

    private boolean fullyCovered(boolean[] covered)
    {
        for(boolean x: covered)
            if(!x)
                return false;
        return true;
    }

}
