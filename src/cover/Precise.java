package cover;

import java.util.ArrayList;

public class Precise extends Solution
{
    public ArrayList<Integer> solve(FiniteSet target, SetFamily family)
    {

        if(family.getSets().size() == 0)
        {
            ArrayList<Integer> r = new ArrayList<>();
            r.add(0);
            return  r;
        }

        ArrayList<ArrayList<Integer>> common = determineCommonElements(target, family);

        int[] chosenSets = new int[family.getSets().size()];
        boolean[] covered = new boolean[target.getLimit()];
        int[] recursionTrack = new int[family.getSets().size()];

        int[] globalMin = new int[1];
        globalMin[0] = Integer.MAX_VALUE;

        for(int i = 0; i < common.size(); i++)
        {
            recursionTrack[0] = i + 1;
            recursiveSolve(common, i, 0, covered, chosenSets, recursionTrack, globalMin);
        }

        ArrayList<Integer> rArray = new ArrayList<Integer>();

        if(globalMin[0] != Integer.MAX_VALUE)
            for(int i = 0; i < globalMin[0] + 1; i++)
                rArray.add(chosenSets[i]);
        else
            rArray.add(0);

        return rArray;
    }

    private void recursiveSolve(ArrayList<ArrayList<Integer>> common, int setNum, int depth, boolean[] covered, int[] chosenSets, int[] recursionTrack, int[] globalMin)
    {
        if(setNum >= common.size() || depth >= common.size() || depth >= globalMin[0])
            return;

        if(common.get(setNum).size() == 0)
            recursiveSolve(common, setNum + 1, depth, covered, chosenSets, recursionTrack, globalMin);

        recursionTrack[depth] = setNum + 1;

        ArrayList<Integer> alreadyCovered = coverUncoveredCommon(covered, common.get(setNum));

        if(fullyCovered(covered))
        {
            globalMin[0] = depth;
            copyArray(chosenSets, recursionTrack);

            uncoverRecent(covered, common.get(setNum), alreadyCovered);
            return;
        }
        else if(setNum == common.size() - 1)
        {
            uncoverRecent(covered, common.get(setNum), alreadyCovered);
            return;
        }

        for(int i = setNum + 1; i < common.size(); i++)
        {
             recursiveSolve(common, i, depth + 1, covered, chosenSets, recursionTrack, globalMin);
        }

        uncoverRecent(covered, common.get(setNum), alreadyCovered);
        return;
    }






    private ArrayList<ArrayList<Integer>> determineCommonElements(FiniteSet target, SetFamily family)
    {
        ArrayList<ArrayList<Integer>> commonElements = new ArrayList<ArrayList<Integer>>();

        for(int i = 0; i < family.getSets().size(); i++)
        {
           commonElements.add(family.getSets().get(i).commonElements(target));

        }

        return commonElements;
    }

    private  ArrayList<Integer> coverUncoveredCommon(boolean[] covered, ArrayList<Integer> common)
    {
        ArrayList<Integer> alreadyCovered = new ArrayList<Integer>();

        for(Integer x: common)
            if(!covered[x - 1])
                covered[x - 1] = true;
            else
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
                if(wereCovered.get(j) == common.get(i))
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
            else
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

    private void copyArray(int[] arr1, int[] arr2)
    {
        for(int i = 0; i < arr1.length; i++)
        {
            arr1[i] = arr2[i];
        }
    }
}
