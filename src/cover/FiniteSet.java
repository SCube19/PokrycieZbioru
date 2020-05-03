package cover;
import java.util.ArrayList;

public class FiniteSet extends Set
{
    int first;
    int step;
    int limit;

    public FiniteSet(int first, int step, int limit)
    {
        this.first = first;
        this.step = step;
        this.limit = limit;
    }
    public int getElement(int index)
    {
        int rVal = first + (index - 1) * step;

        if(rVal <= limit)
            return rVal;
        return -1;

    }
    public ArrayList<Integer> makeArrayList()
    {
        ArrayList<Integer> temp = new ArrayList<Integer>();

        for(int i = first; i <= limit; i+=step)
            temp.add(i);

        return temp;
    }

    public String toString()
    {
        String rString = "[ ";
        for(int i = 0; i < 3; i++)
            rString += (first + step*i) + ", ";
        rString += "..., " + limit + " ]";

        return rString;
    }
}
