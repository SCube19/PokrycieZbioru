package cover;
import javax.swing.*;
import java.util.ArrayList;

public class Precise extends Solution
{
    public ArrayList<Integer> solve(FiniteSet target, SetFamily family)
    {

       // System.out.println("Precise solution");
        ArrayList<ArrayList<Integer>> common = determineCommonElements(target, family);
        int[] chosenSets = new int[family.getSets().size()];
        boolean[] covered = new boolean[target.getLimit()];

        int minDepth = Integer.MAX_VALUE;
        int minSetNum = -1;

        for(int i = 0; i < common.size(); i++)
        {
            //System.out.println("Checking set " + (i+1) + " as start point");
            int tmpDepth = recursiveSolve(common, i, 1, covered, chosenSets);

           // System.out.println("Depth is " + tmpDepth);
            if(tmpDepth != -1 && minDepth > tmpDepth)
            {
                minDepth = tmpDepth;
                minSetNum = i;
            }
            String str = new String();
            for(boolean x: covered)
                str += x + " ";
            //System.out.println("Coverage: " + str);
        }

        chosenSets[0] = minSetNum + 1;
        ArrayList<Integer> rArray = new ArrayList<Integer>();

        //String str = new String();
       // for(int x: chosenSets)
          //  str += x + " ";
        //System.out.println("We chose: " + str);
        //System.out.println("min sets are: " + minDepth);
        if(minDepth != Integer.MAX_VALUE)
            for(int i = 0; i < minDepth ; i++)
                rArray.add(chosenSets[i]);
        else
            rArray.add(0);

        return rArray;
    }

    private int recursiveSolve(ArrayList<ArrayList<Integer>> common, int setNum, int depth, boolean[] covered, int[] chosenSets)
    {
        if(common.get(setNum).size() == 0)
            return -1;

       /// System.out.println("setNum is: :" + (setNum+1));
        //System.out.println("depth is: :" + depth);

        //String str = new String();
        //for(boolean x: covered)
        //    str += x + " ";
       // System.out.println("Coverage before: " + str);
        ArrayList<Integer> alreadyCovered = coverUncoveredCommon(covered, common.get(setNum));

        //str = new String();
        ///for(boolean x: covered)
        //    str += x + " ";
       // System.out.println("Coverage after: " + str);

        if(fullyCovered(covered))
        {
           // System.out.println("Full cover");
            uncoverRecent(covered, common.get(setNum), alreadyCovered);
           // str = new String();
            //for(boolean x: covered)
            //   str += x + " ";
            //System.out.println("Coverage reversal: " + str);
            return depth;
        }
        else if(setNum == common.size() - 1)
        {
           // System.out.println("Cant cover");
            uncoverRecent(covered, common.get(setNum), alreadyCovered);
           // str = new String();
          //  for(boolean x: covered)
           //     str += x + " ";
            //System.out.println("Coverage reversal: " + str);
           // uncoverRecent(covered, common.get(setNum), alreadyCovered);
            return -1;
        }

        int minDepth = Integer.MAX_VALUE;
        int minSetNum = -1;

        for(int i = setNum + 1; i < common.size(); i++)
        {
            int tmpDepth = recursiveSolve(common, i, depth + 1, covered, chosenSets);

            if(tmpDepth != -1 && tmpDepth < minDepth)
            {
                minDepth = tmpDepth;
                minSetNum = i;
            }
        }

        uncoverRecent(covered, common.get(setNum), alreadyCovered);

        if(minSetNum != -1)
            chosenSets[depth] = minSetNum + 1;

        if(minDepth != Integer.MAX_VALUE)
            return minDepth;
        return -1;
    }

    private ArrayList<ArrayList<Integer>> determineCommonElements(FiniteSet target, SetFamily family)
    {
        ArrayList<ArrayList<Integer>> commonElements = new ArrayList<ArrayList<Integer>>();

        for(int i = 0; i < family.getSets().size(); i++)
        {
           commonElements.add(family.getSets().get(i).commonElements(target));

          //  String tmp = new String();
           // for(Integer x: commonElements.get(commonElements.size()-1))
           //     tmp += x + " ";
           // System.out.println("Common for " + (i+1) + " are: " + tmp);
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

}
