package cover;
import java.util.ArrayList;

public class SetFamily
{
    private class SetInFamily
    {
        private ArrayList<Set> set;
        private int index;

        public SetInFamily(int index)
        {
            set = new ArrayList<>();
            this.index = index;
        }

        public void addToSet(Set setToAdd)
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

    }
    ArrayList<SetInFamily> sets;

    public SetFamily()
    {
        sets = new ArrayList<SetInFamily>();
    }
    public void createEmptySet()
    {
        sets.add(new SetInFamily(sets.size()));
    }
    public void addToLast(Set set)
    {
        sets.get(sets.size()-1).addToSet(set);
    }

    public void writeSets()
    {
        String setString = new String();
        for (SetInFamily x: sets)
        {
            for(Set y: x.getSet())
                setString += y.toString() + ", ";
            System.out.println(setString);
        }
    }


}
