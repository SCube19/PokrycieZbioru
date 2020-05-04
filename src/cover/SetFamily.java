package cover;
import java.util.ArrayList;

public class SetFamily
{
    //klasa rodziny zbiorow
        /////////////////////////////////////////////
        //klasa wewnetrzna bedaca suma zbiorow i posiadajaca indeksowanie
    protected class SetInFamily
    {
        private ArrayList<Set> set;
        private int index;

        public SetInFamily(int index)
        {
            set = new ArrayList<Set>();
            this.index = index;
        }

        public void addToSet(Set setToAdd) //suma zbiorow
        {
            set.add(setToAdd);
        }

        public int getIndex()
        {
            return index;
        }

        public ArrayList<Set> getSet()
        {
            return set;
        }

        public boolean checkForExistence(int n)
        {
            for(Set x: set)
                if(x.checkForExistence(n))
                    return true;

            return false;
        }

        public ArrayList<Integer> commonElements(FiniteSet set) //daje Arrayliste bedaca przecieciem set i this
        {
            ArrayList<Integer> commonElements = new ArrayList<Integer>();

            for(int j = 1; j <= set.getLimit(); j++)
                if(checkForExistence(j))
                    commonElements.add(j);

            return commonElements;
        }

        public boolean empty()
        {
            return (set.size() == 0);
        }

    }
    ////////////////////////////////////////////////////////////

    private ArrayList<SetInFamily> sets;

    public SetFamily()
    {
        sets = new ArrayList<SetInFamily>();
    }

    public void createEmptySet()
    {
        sets.add(new SetInFamily(sets.size() + 1));
    }

    public void addToLast(Set set)
    {
        sets.get(sets.size() - 1).addToSet(set);
    }

    public ArrayList<SetInFamily> getSets()
    {
        return sets;
    }

}
