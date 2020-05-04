package cover;
import java.util.ArrayList;

public class FiniteSet extends Set
{
    private int first;
    private int step;
    private int limit;

    public FiniteSet(int first, int step, int limit)
    {
        this.first = first;
        this.step = step;
        this.limit = limit;
    }
    public int getElement(int index)
    {
        int rVal = first + index * step;

        if(rVal <= limit)
            return rVal;

        return -1;

    }

    public String toString()
    {
        String rString = "[ ";
        for(int i = 0; i < 3; i++)
            rString += (first + step*i) + ", ";

        rString += "..., " + limit + " ]";

        return rString;
    }

    public boolean checkForExistence(int element)
    {
        return (element >= first && element <= limit &&(element - first)%step == 0);
    }

    public int getLimit()
    {
        return limit;
    }

}
